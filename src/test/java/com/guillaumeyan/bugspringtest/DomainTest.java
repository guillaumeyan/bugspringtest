package com.guillaumeyan.bugspringtest;

import com.guillaumeyan.bugspringtest.repos.LeadRepository;
import com.guillaumeyan.bugspringtest.domain.Lead;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class DomainTest {

    @Test
    public void test(@Autowired LeadRepository leadRepository) {
        Lead lead = new Lead();
        Lead leadSaved = leadRepository.save(lead);
        assertNull(leadSaved.getUser());
        assertNotNull(leadSaved);
    }
}
