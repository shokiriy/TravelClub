package uz.kruz.travelclub.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.kruz.travelclub.dto.PostingDto;
import uz.kruz.travelclub.service.PostingService;

import java.util.List;

@RestController
@RequestMapping("api/posts")
@RequiredArgsConstructor
public class PostingController {
    //
    private final PostingService postingService;

    @PostMapping("/board/{boardId}")
    public ResponseEntity<String> create(@PathVariable Long boardId, @Valid @RequestBody PostingDto postingDto) {
        //
        postingDto.setBoardId(boardId);
        postingService.register(boardId, postingDto);

        return ResponseEntity.status(HttpStatus.CREATED).body("Post created successfully");
    }

    @GetMapping("/{postingId}")
    public ResponseEntity<PostingDto> find(@PathVariable Long postingId) {
        //
        return ResponseEntity.ok(postingService.find(postingId));
    }

    @GetMapping("/board/{boardId}")
    public List<PostingDto> findByBoardId(@PathVariable Long boardId) {
        //
        return postingService.findByBoardId(boardId);
    }

    @PutMapping("/{postingId}")
    public ResponseEntity<String> update(@PathVariable Long postingId, @Valid @RequestBody PostingDto postingDto) {
        //
        postingDto.setId(postingId);
        postingService.modify(postingDto);

        return ResponseEntity.ok("Post updated successfully");
    }

    @DeleteMapping("/{postingId}")
    public ResponseEntity<String> delete(@PathVariable Long postingId) {
        //
        postingService.remove(postingId);

        return ResponseEntity.ok("Post deleted successfully");
    }

    @DeleteMapping("/board/{boardId}")
    public ResponseEntity<String> deleteAllIn(@PathVariable Long boardId) {
        //
        postingService.removeAllIn(boardId);

        return ResponseEntity.ok("All posts in the Board deleted successfully");
    }
}