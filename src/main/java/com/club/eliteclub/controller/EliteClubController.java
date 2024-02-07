package com.club.eliteclub.controller;

import com.club.eliteclub.model.ClubDto;
import com.club.eliteclub.service.EliteClubService;
import com.club.eliteclub.verify.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/clubs")

public class EliteClubController {
    @Autowired
    EliteClubService eliteClubService;
    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<ClubDto> clubs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "2") int size
    ){
        return eliteClubService.getAll(page,size);

    }
    @GetMapping(path = "/search",produces = MediaType.APPLICATION_JSON_VALUE)
    public  List<ClubDto> searchClub(
            @RequestParam String searchTerm,
            @RequestParam Short rating){
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setClubName(searchTerm);
        searchCriteria.setRating(rating);
        return eliteClubService.searchClub(searchCriteria);

    }

    @PostMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createNewClub(@RequestBody ClubDto clubDto){
        eliteClubService.addClub(clubDto.getClubName());
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ClubDto clubWithId(@PathVariable long id){
        return eliteClubService.getById(id);
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ClubDto updateClub(@PathVariable long id, @RequestBody ClubDto clubDto){
        return eliteClubService.updateClub(id, clubDto);
    }

    @DeleteMapping(path = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteById(@PathVariable("id") long id) throws Exception
    {eliteClubService.deleteClub(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);

    }
}
