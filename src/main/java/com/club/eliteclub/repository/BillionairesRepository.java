package com.club.eliteclub.repository;

import com.club.eliteclub.entity.Billionaires;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface BillionairesRepository extends JpaRepository<Billionaires,Long> {
}
