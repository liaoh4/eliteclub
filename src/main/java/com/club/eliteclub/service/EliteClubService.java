package com.club.eliteclub.service;

import com.club.eliteclub.entity.EliteClub;
import com.club.eliteclub.model.ClubDto;
import com.club.eliteclub.repository.EliteClubRepository;
import com.club.eliteclub.verify.SearchCriteria;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;

import java.util.Optional;
import java.util.stream.Collectors;



@Service
public class EliteClubService {
    private static final Logger LOG = LoggerFactory.getLogger(EliteClubService.class);

    @Autowired
    EliteClubRepository eliteClubRepository;



public Page<ClubDto> getAll(int page, int size){
    PageRequest pageable = PageRequest.of(page,size);
    Page<EliteClub> eliteClubPage = eliteClubRepository.findAll(pageable);
    List<ClubDto> clubDtoList = eliteClubPage.getContent().stream().map(c -> new ClubDto(c.getClubName(),c.getRating())).collect(Collectors.toList());
    return new PageImpl<>(clubDtoList,pageable,eliteClubPage.getTotalElements());
}

    public void addClub(String... clubNames) {
        List<EliteClub> eliteClubs = new ArrayList<>();

        for (String clubName : clubNames) {
            EliteClub eliteClub = new EliteClub();
            eliteClub.setClubName(clubName);
            eliteClubs.add(eliteClub);
        }

        eliteClubRepository.saveAll(eliteClubs);
    }

    public List<ClubDto> searchClub(SearchCriteria searchCriteria){
    String searchTerm = searchCriteria.getClubName();
    Short rating = searchCriteria.getRating();
    List<ClubDto> res = new ArrayList<>();
    List<EliteClub> clubs = eliteClubRepository.findClubs(buildLikePattern(searchTerm),rating);
    for(EliteClub club: clubs){
        ClubDto clubDto = new ClubDto(club.getClubName(),club.getRating());
        res.add(clubDto);

    }
        LOG.info("Search Result: {} ", res);
    return res;

    }
    private String buildLikePattern(String searchTerm){
    return searchTerm.toLowerCase() + "%";
    }

    public ClubDto getById(long id) {
        EliteClub eliteClub = eliteClubRepository.getOne(id);
        return new ClubDto(eliteClub.getClubName(),eliteClub.getRating());
    }

    public ClubDto updateClub(long id, ClubDto clubDto){
    EliteClub eliteClub = eliteClubRepository.getOne(id);
    eliteClub.setClubName(clubDto.getClubName());
    eliteClub.setRating(clubDto.getRating());
    eliteClubRepository .save(eliteClub);
    return  new ClubDto(eliteClub.getClubName(),eliteClub.getRating());
    }

    public void deleteClub(long id) throws Exception{

        Optional<EliteClub> optionalEliteClub = eliteClubRepository.findById(id);
        if(!optionalEliteClub.isPresent()) throw new Exception();
        eliteClubRepository.deleteById(id);

    }
}
