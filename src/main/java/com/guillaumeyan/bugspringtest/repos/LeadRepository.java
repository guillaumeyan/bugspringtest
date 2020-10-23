package com.guillaumeyan.bugspringtest.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.guillaumeyan.bugspringtest.domain.Lead;


public interface LeadRepository extends JpaRepository<Lead, Long> {
    // add custom queries here
}
