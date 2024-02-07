package com.club.eliteclub.repository;

import com.club.eliteclub.entity.EliteClub;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EliteClubRepository extends PagingAndSortingRepository<EliteClub, Long> {

    List<EliteClub> getById(Long id);
    List<EliteClub> findByClubName(String clubName);

    @Query("SELECT x from EliteClub x WHERE lower(x.clubName) LIKE :searchTerm and x.rating > :rating order by x.clubName asc")
    List<EliteClub> findClubs(@Param("searchTerm") String searchTerm,@Param("rating") Short rating);


    Page<EliteClub> findAll(Pageable pageable);

    EliteClub getOne(long id);
}
