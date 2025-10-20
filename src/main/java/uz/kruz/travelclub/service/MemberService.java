package uz.kruz.travelclub.service;

import uz.kruz.travelclub.dto.MemberDto;
import uz.kruz.travelclub.util.InvalidEmailException;

import java.util.List;

public interface MemberService {
    //
    void register(MemberDto memberDto) throws InvalidEmailException;
    MemberDto find(Long memberId);
    List<MemberDto> findByName(String memberName);
    void modify(MemberDto memberDto) throws InvalidEmailException;
    void remove(Long memberId);
}
