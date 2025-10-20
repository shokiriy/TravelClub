package uz.kruz.travelclub.service.logic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.kruz.travelclub.domain.CommunityMember;
import uz.kruz.travelclub.domain.SocialBoard;
import uz.kruz.travelclub.dto.MemberDto;
import uz.kruz.travelclub.dto.MembershipDto;
import uz.kruz.travelclub.repository.BoardRepository;
import uz.kruz.travelclub.repository.MemberRepository;
import uz.kruz.travelclub.service.MemberService;
import uz.kruz.travelclub.service.MembershipService;
import uz.kruz.travelclub.service.mapper.MemberMapper;
import uz.kruz.travelclub.util.InvalidEmailException;
import uz.kruz.travelclub.util.MemberDuplicationException;
import uz.kruz.travelclub.util.NoSuchMemberException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceLogic implements MemberService {
    //
    private final MemberRepository memberRepository;
    private final MembershipService membershipService;
    private final BoardRepository boardRepository;
    private final MemberMapper memberMapper;

    @Override
    public void register(MemberDto memberDto) throws InvalidEmailException {
        //
        String email = memberDto.getEmail();
        memberRepository.findByEmail(email)
                .ifPresent(member -> {
                    throw new MemberDuplicationException("Member already exists with email --> " + email);
                });
        memberRepository.save(CommunityMember.builder()
                        .name(memberDto.getName())
                        .nickName(memberDto.getNickName())
                        .email(memberDto.getEmail())
                        .phoneNumber(memberDto.getPhoneNumber())
                        .birthDay(memberDto.getBirthDay())
                .build());
    }

    @Override
    public MemberDto find(Long memberId) {
        //
        CommunityMember member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NoSuchMemberException("No such member with id --> " + memberId));

        return memberMapper.toDto(member);
    }

    @Override
    public List<MemberDto> findByName(String memberName) {
        //
        List<CommunityMember> members = memberRepository.findByName(memberName);
        if (members.isEmpty()) {
            throw new NoSuchMemberException("No such member with name --> " + memberName);
        }

        return members.stream()
                .map(member -> MemberDto.builder()
                        .id(member.getId())
                        .email(member.getEmail())
                        .name(member.getName())
                        .nickName(member.getNickName())
                        .phoneNumber(member.getPhoneNumber())
                        .birthDay(member.getBirthDay())
                        .build())
                .toList();
    }

    @Override
    public void modify(MemberDto memberDto) throws InvalidEmailException {
        //
        CommunityMember member = memberRepository.findByEmail(memberDto.getEmail())
                .orElseThrow(() -> new NoSuchMemberException("No such member with email --> " + memberDto.getEmail()));

        if (memberDto.getName() != null && !memberDto.getName().isEmpty()) {
            member.setName(memberDto.getName());
        }
        if (memberDto.getNickName() != null && !memberDto.getNickName().isEmpty()) {
            member.setNickName(memberDto.getNickName());
        }
        if (memberDto.getPhoneNumber() != null && !memberDto.getPhoneNumber().isEmpty()) {
            member.setPhoneNumber(memberDto.getPhoneNumber());
        }
        if (memberDto.getBirthDay() != null) {
            member.setBirthDay(memberDto.getBirthDay());
        }

        memberRepository.save(member);
    }

    @Override
    public void remove(Long memberId) {
        //
        CommunityMember member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NoSuchMemberException("No such member with id --> " + memberId));

        List<SocialBoard> boards = boardRepository.findByAdminEmail(member.getEmail());
        for (SocialBoard board : boards) {
            board.setAdminEmail(null);
            boardRepository.save(board);
        }

        List<MembershipDto> membershipList = membershipService.findMembershipsByMember(memberId);
        for (MembershipDto membership : membershipList) {
            membershipService.removeMembership(membership.getClubId(), member.getEmail());
        }

        memberRepository.deleteById(memberId);
    }
}