package com.club.eliteclub.entity;

//import jakarta.persistence.*;

import javax.persistence.*;
import javax.persistence.Table;


import java.io.Serializable;

@Entity
@Table(name = "elite_club")
public class EliteClub implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "club_name")
    private String clubName;

    @Column(name = "rating")
    private Short rating;

    public EliteClub(){}

    public EliteClub(String clubName){
        this.clubName = clubName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Short getRating() {
        return rating;
    }

    public void setRating(Short rating) {
        this.rating = rating;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }
}
