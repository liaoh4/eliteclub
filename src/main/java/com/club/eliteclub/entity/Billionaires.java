package com.club.eliteclub.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "billionaires")
public class Billionaires implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "person_name")
    private String personName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "company")
    private String company;

    @Column(name = "wealth_rank")
    private Integer wealthRank;
    public Billionaires(Long id){
        this.id = id;
    }
    public Billionaires(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public Integer getWealthRank() {
        return wealthRank;
    }

    public void setWealthRank(Integer wealthRank) {
        this.wealthRank = wealthRank;
    }
}
