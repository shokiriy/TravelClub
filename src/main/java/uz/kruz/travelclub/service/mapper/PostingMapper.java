package uz.kruz.travelclub.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uz.kruz.travelclub.domain.Posting;
import uz.kruz.travelclub.domain.SocialBoard;
import uz.kruz.travelclub.dto.PostingDto;

@Mapper(componentModel = "spring")
public interface PostingMapper {
    //
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "board", source = "board")
    Posting toEntity(PostingDto postingDto, SocialBoard board);

    @Mapping(target = "boardId", source = "board.id")
    PostingDto toDto(Posting posting);
//        //
//        return Posting.builder()
//                .title(postingDto.getTitle())
//                .contents(postingDto.getContents())
//                .writerEmail(postingDto.getWriterEmail())
//                .board(board)
//                .build();

//    }
        //
//        return PostingDto.builder()
//                .id(posting.getId())
//                .title(posting.getTitle())
//                .contents(posting.getContents())
//                .writerEmail(posting.getWriterEmail())
//                .writtenDate(posting.getWrittenDate())
//                .boardId(posting.getBoard().getId())
//                .readCount(posting.getReadCount())
//                .build();
//    }
}
