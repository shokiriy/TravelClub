package uz.kruz.travelclub.service;

import uz.kruz.travelclub.dto.BoardDto;

import java.util.List;

public interface BoardService {
    //
    String register(BoardDto boardDto);
    BoardDto find(Long boardId);
    List<BoardDto> findByName(String boardName);
    BoardDto findByClubName(String clubName);
    void modify(BoardDto boardDto);
    void remove(Long boardId);
}
