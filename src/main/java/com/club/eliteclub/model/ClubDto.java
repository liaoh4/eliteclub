package com.club.eliteclub.model;

import java.io.Serializable;

public class ClubDto implements Serializable {
    private final String clubName;
    private final Short rating;

    public ClubDto(String clubName, Short rating){

        this.clubName = clubName;
        this.rating = rating;
    }

    public Short getRating() {
        return rating;
    }

    public String getClubName(){
        return clubName;
    }
}
