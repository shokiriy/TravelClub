package uz.kruz.travelclub.service;

import uz.kruz.travelclub.dto.PostingDto;

import java.util.List;

public interface PostingService {
    //
    String register(Long boardId, PostingDto postingDto);
    PostingDto find(Long postingId);
    List<PostingDto> findByBoardId(Long boardId);
    void modify(PostingDto postingDto);
    void remove(Long postingId);
    void removeAllIn(Long boardId);
}