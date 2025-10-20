package uz.kruz.travelclub.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uz.kruz.travelclub.domain.ClubMembership;
import uz.kruz.travelclub.dto.MembershipDto;

@Mapper(componentModel = "spring")
public interface MembershipMapper {
    //
    @Mapping(target = "memberId", source = "member.id")
    @Mapping(target = "clubId", source = "club.id")
    MembershipDto toDto(ClubMembership membership);

    @Mapping(target = "member", ignore = true)
    @Mapping(target = "club", ignore = true)
    ClubMembership toEntity(MembershipDto membershipDto);
//    public MembershipDto toDto(ClubMembership membership) {
//        if (membership == null) {
//            return null;
//        }
//
//        return MembershipDto.builder()
//                .id(membership.getId())
//                .clubId(membership.getClub().getId())
//                .memberEmail(membership.getMemberEmail())
//                .memberName(membership.getMember().getName())
//                .role(membership.getRole())
//                .joinDate(membership.getJoinDate())
//                .memberId(membership.getMember() != null ? membership.getMember().getId() : null)
//                .build();
//    }
}