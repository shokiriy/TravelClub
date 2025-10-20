package uz.kruz.travelclub.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.kruz.travelclub.dto.TravelClubDto;
import uz.kruz.travelclub.service.ClubService;

@RestController
@RequestMapping("/api/clubs")
@RequiredArgsConstructor

public class ClubController {
    //
    private final ClubService clubService;

    @PostMapping
    public ResponseEntity<String> create(@Valid @RequestBody TravelClubDto clubDto) {
        //
        clubService.register(clubDto);

        return ResponseEntity.status(HttpStatus.CREATED).body("Club created successfully");
    }

    @GetMapping("/{clubId}")
    public ResponseEntity<TravelClubDto> findClub(@PathVariable Long clubId) {
        //
        return ResponseEntity.ok(clubService.findClub(clubId));
    }

    @GetMapping("/byName/{clubName}")
    public ResponseEntity<TravelClubDto> findClub(@PathVariable String clubName) {
        //
        return ResponseEntity.ok(clubService.findClubByName(clubName));
    }

    @PutMapping("/{clubId}")
    public ResponseEntity<String> update(@PathVariable Long clubId, @Valid @RequestBody TravelClubDto clubDto) {
        //
        clubDto.setId(clubId);
        clubService.modify(clubDto);

        return ResponseEntity.ok("Club updated successfully");
    }

    @DeleteMapping("/{clubId}")
    public ResponseEntity<String> delete(@PathVariable Long clubId) {
        //
        clubService.remove(clubId);

        return ResponseEntity.ok("Club deleted successfully");
    }
}