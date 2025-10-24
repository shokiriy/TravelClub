package uz.kruz.travelclub.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.kruz.travelclub.dto.MembershipDto;
import uz.kruz.travelclub.service.MembershipService;

import java.util.List;

@RestController
@RequestMapping("/api/memberships")
@RequiredArgsConstructor

public class MembershipController {
    //
    private final MembershipService membershipService;

    @PostMapping("/{clubId}")
    public ResponseEntity<String> addMembership(@PathVariable Long clubId, @Valid @RequestBody MembershipDto membershipDto) {
        //
        membershipDto.setClubId(clubId);
        membershipService.addMembership(membershipDto);

        return ResponseEntity.status(HttpStatus.CREATED).body("Membership created successfully");
    }

    @GetMapping("/{clubId}/members")
    public ResponseEntity<List<MembershipDto>> findAllMemberships(@PathVariable Long clubId) {
        //
        return ResponseEntity.ok(membershipService.findMembershipsByClub(clubId));
    }

    @PutMapping("/{membershipId}")
    public ResponseEntity<String> modifyMembership(@PathVariable Long membershipId, @Valid @RequestBody MembershipDto membership) {
        //
        Long clubId = membership.getClubId();
        membershipService.modifyMembership(clubId, membership);
        return ResponseEntity.status(HttpStatus.CREATED).body("Membership updated successfully");
    }

    @DeleteMapping("/{clubId}/{memberEmail}")
    public ResponseEntity<String> removeMembership(@PathVariable Long clubId, @PathVariable String memberEmail) {
        //
        membershipService.removeMembership(clubId, memberEmail);

        return ResponseEntity.ok("Membership removed successfully");
    }
}
