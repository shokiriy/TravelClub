package uz.kruz.travelclub.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.kruz.travelclub.dto.BoardDto;
import uz.kruz.travelclub.service.BoardService;

import java.util.List;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class BoardController {
    //
    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<String> createBoard(@Valid @RequestBody BoardDto boardDto) {
        //
        boardService.register(boardDto);

        return ResponseEntity.status(HttpStatus.CREATED).body("Board created successfully");
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<BoardDto> getBoardById(@PathVariable Long boardId) {
        //
        return ResponseEntity.ok(boardService.find(boardId));
    }

    @GetMapping("/name/{boardName}")
    public ResponseEntity<List<BoardDto>> getBoardsByName(@PathVariable String boardName) {
        //
        List<BoardDto> boards = boardService.findByName(boardName);

        return ResponseEntity.ok(boards);
    }

    @GetMapping("/club/{clubName}")
    public ResponseEntity<BoardDto> getBoardByClubName(@PathVariable String clubName) {
        //
        BoardDto board = boardService.findByClubName(clubName);

        return ResponseEntity.ok(board);
    }

    @PutMapping("/{boardId}")
    public ResponseEntity<String> updateBoard(@PathVariable Long boardId, @Valid @RequestBody BoardDto boardDto) {
        //
        boardDto.setId(boardId);
        boardService.modify(boardDto);

        return ResponseEntity.ok("Board updated successfully");
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity<String> deleteBoard(@PathVariable Long boardId) {
        //
        boardService.remove(boardId);

        return ResponseEntity.ok("Board deleted successfully");
    }
}