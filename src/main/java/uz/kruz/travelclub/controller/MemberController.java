package uz.kruz.travelclub.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.kruz.travelclub.dto.MemberDto;
import uz.kruz.travelclub.service.MemberService;

import java.util.List;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {
    //
    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody MemberDto memberDto) {
        //
        memberService.register(memberDto);

        return ResponseEntity.status(HttpStatus.CREATED).body("Member created successfully");
    }

    @GetMapping("/{memberId}")
    ResponseEntity<MemberDto> find(@PathVariable Long memberId) {
        //
        return ResponseEntity.ok(memberService.find(memberId));
    }

    @GetMapping("/byName/{memberName}")
    List<MemberDto> findByName(@PathVariable String memberName) {
        //
        return memberService.findByName(memberName);
    }

    @PutMapping("/{memberId}")
    ResponseEntity<String> update(@PathVariable Long memberId, @RequestBody MemberDto memberDto) {
        //
        memberDto.setId(memberId);
        memberService.modify(memberDto);

        return ResponseEntity.ok("Member updated successfully");
    }

    @DeleteMapping("/{memberId}")
    ResponseEntity<String> delete(@PathVariable Long memberId) {
        //
        memberService.remove(memberId);

        return ResponseEntity.ok("Member deleted successfully");
    }
}