package uz.kruz.travelclub.service;

import uz.kruz.travelclub.dto.TravelClubDto;

public interface ClubService {
    //
    void register(TravelClubDto clubDto);
    TravelClubDto findClub(Long clubId);
    TravelClubDto findClubByName(String name);
    void modify(TravelClubDto clubDto);
    void remove(Long clubId);
}
