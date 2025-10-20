package uz.kruz.travelclub.service;

import uz.kruz.travelclub.dto.MembershipDto;

import java.util.List;

public interface MembershipService {
    //
    void addMembership(MembershipDto membershipDto);
    MembershipDto findMembershipIn(Long clubId, String memberEmail);
    List<MembershipDto> findMembershipsByClub(Long clubId);
    List<MembershipDto> findMembershipsByMember(Long memberId);
    void modifyMembership(Long clubId, MembershipDto member);
    void removeMembership(Long clubId, String memberEmail);
}