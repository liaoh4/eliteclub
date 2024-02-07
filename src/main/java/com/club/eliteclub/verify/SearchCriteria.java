package com.club.eliteclub.verify;

import javax.validation.constraints.Pattern;

public class SearchCriteria {
    @Pattern(regexp = "(\\w+)",message = "只允许包含字母、数字和下划线")
    private String clubName;
    @Pattern(regexp = "/[1-4]/",message = "评分只能是1到4之间的整数")
    private short rating;

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public short getRating() {
        return rating;
    }

    public void setRating(short rating) {
        this.rating = rating;
    }

}
