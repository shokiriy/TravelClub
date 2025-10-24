package uz.kruz.travelclub.service.logic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.kruz.travelclub.domain.ClubMembership;
import uz.kruz.travelclub.domain.CommunityMember;
import uz.kruz.travelclub.domain.RoleInClub;
import uz.kruz.travelclub.domain.TravelClub;
import uz.kruz.travelclub.dto.MembershipDto;
import uz.kruz.travelclub.repository.MemberRepository;
import uz.kruz.travelclub.repository.MembershipRepository;
import uz.kruz.travelclub.repository.ClubRepository;
import uz.kruz.travelclub.service.MembershipService;
import uz.kruz.travelclub.service.mapper.MembershipMapper;
import uz.kruz.travelclub.util.MemberDuplicationException;
import uz.kruz.travelclub.util.NoSuchClubException;
import uz.kruz.travelclub.util.NoSuchMemberException;
import uz.kruz.travelclub.util.NoSuchMembershipException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MembershipServiceLogic implements MembershipService {
    //
    private final ClubRepository clubRepository;
    private final MemberRepository memberRepository;
    private final MembershipRepository membershipRepository;
    private final MembershipMapper membershipMapper;

    @Override
    public void addMembership(MembershipDto membershipDto) {
        //
        String memberEmail = membershipDto.getMemberEmail();

        CommunityMember member = memberRepository.findByEmail(memberEmail)
                .orElseThrow(() -> new NoSuchMemberException("No such member with email --> " + memberEmail));

        TravelClub club = clubRepository.findById(membershipDto.getClubId())
                .orElseThrow(() -> new NoSuchClubException("No such club with id --> " + membershipDto.getClubId()));

        for (ClubMembership membership : club.getMembershipList()) {
            if (memberEmail.equals(membership.getMemberEmail())) {
                throw new MemberDuplicationException("Member already exists in this club --> " + memberEmail);
            }

            if (membershipDto.getRole() == RoleInClub.President) {
                throw new MemberDuplicationException("Member already exists with role --> " + membershipDto.getRole().name());
            }
        }

        ClubMembership clubMembership = ClubMembership.builder()
                .club(club)
                .member(member)
                .memberEmail(memberEmail)
                .role(membershipDto.getRole())
                .joinDate(membershipDto.getJoinDate())
                .build();

        club.getMembershipList().add(clubMembership);

        clubRepository.save(club);
    }

    @Override
    public MembershipDto findMembershipIn(Long clubId, String memberEmail) {
        //
        TravelClub club = clubRepository.findById(clubId)
                .orElseThrow(() -> new NoSuchClubException("No such club with id --> " + clubId));

        ClubMembership membership = getMembershipOfClub(club, memberEmail);

        return MembershipDto.builder()
                .memberName(membership.getMember().getName())
                .memberEmail(membership.getMemberEmail())
                .joinDate(membership.getJoinDate())
                .role(membership.getRole())
                .build();
    }

    @Override
    public List<MembershipDto> findMembershipsByClub(Long clubId) {
        //
        TravelClub club = clubRepository.findById(clubId)
                .orElseThrow(() -> new NoSuchClubException("No such club with id --> " + clubId));

        return club.getMembershipList().stream()
                .map(membership -> MembershipDto.builder()
                        .id(membership.getId())
                        .memberId(membership.getMember().getId())
                        .clubId(membership.getClub().getId())
                        .memberName(membership.getMember().getName())
                        .memberEmail(membership.getMemberEmail())
                        .role(membership.getRole())
                        .joinDate(membership.getJoinDate())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<MembershipDto> findMembershipsByMember(Long memberId) {
        //
        CommunityMember member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NoSuchMemberException("No such member with id --> " + memberId));

        return member.getMembershipList().stream()
                .map(membership -> membershipMapper.toDto(membership))
                .toList();
    }

    @Override
    public void modifyMembership(Long clubId, MembershipDto member) {
        //
        String targetEmail = member.getMemberEmail();
        RoleInClub newRole = member.getRole();

        TravelClub targetClub = clubRepository.findById(clubId)
                .orElseThrow(() -> new NoSuchClubException("No such club with id --> " + clubId));

        ClubMembership membershipOfClub = getMembershipOfClub(targetClub, targetEmail);

        membershipOfClub.setRole(newRole);
        clubRepository.save(targetClub);

        CommunityMember targetMember = memberRepository.findByEmail(targetEmail)
                .orElseThrow(() -> new NoSuchMemberException("No such member in this club --> " + member.getMemberEmail()));
        targetMember.getMembershipList().forEach(membershipOfMember -> {
            if (membershipOfMember.getClub().getId().equals(clubId)) {
                membershipOfMember.setRole(newRole);
            }
        });

        memberRepository.save(targetMember);
    }

    @Override
    public void removeMembership(Long clubId, String memberEmail) {
        //
        TravelClub foundClub = clubRepository.findById(clubId)
                .orElseThrow(() -> new NoSuchClubException("No such club with id --> " + clubId));

        CommunityMember foundMember = memberRepository.findByEmail(memberEmail)
                .orElseThrow(() -> new NoSuchMemberException("No such member with email --> " + memberEmail));

        ClubMembership clubMembership = membershipRepository.findByClubIdAndMemberEmail(clubId, memberEmail)
                .orElseThrow(() -> new NoSuchMembershipException("No membership found for clubId --> " + clubId + " and memberEmail --> " + memberEmail));

        foundClub.getMembershipList().remove(clubMembership);
        foundMember.getMembershipList().remove(clubMembership);

        membershipRepository.delete(clubMembership);

        clubRepository.save(foundClub);
        memberRepository.save(foundMember);
    }

    private ClubMembership getMembershipOfClub(TravelClub club, String memberEmail) {
        //
        for (ClubMembership membership : club.getMembershipList()) {
            if (memberEmail.equals(membership.getMemberEmail())) {
                return membership;
            }
        }

        String message = String.format("No such member[email:%s] in club[name:%s]", memberEmail, club.getName());
        throw new NoSuchMemberException(message);
    }

    public void getMembershipBy(Long clubId, String email) {
        clubRepository.findById(clubId)
                .flatMap(club -> club.getMembershipList().stream().
                        filter(clubMembership -> clubMembership.getMemberEmail().equals(email))
                        .findFirst())
                .orElseThrow(() -> new NoSuchMemberException("In the club, No such member with email --> " + email));
    }
}
