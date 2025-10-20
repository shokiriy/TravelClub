package uz.kruz.travelclub.service.mapper;

import org.mapstruct.Mapper;
import uz.kruz.travelclub.domain.CommunityMember;
import uz.kruz.travelclub.dto.MemberDto;

@Mapper(componentModel = "spring", uses = {AddressMapper.class, MembershipMapper.class})
public interface MemberMapper {
    //
    CommunityMember toEntity(MemberDto memberDto);
    MemberDto toDto(CommunityMember member);
}

//MemberDto.builder()
//                .id(member.getId())
//        .email(member.getEmail())
//        .name(member.getName())
//        .nickName(member.getNickName())
//        .phoneNumber(member.getPhoneNumber())
//        .birthDay(member.getBirthDay())
//        .build();

