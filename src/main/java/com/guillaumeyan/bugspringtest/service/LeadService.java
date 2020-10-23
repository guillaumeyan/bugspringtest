package com.guillaumeyan.bugspringtest.service;

import com.guillaumeyan.bugspringtest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import com.guillaumeyan.bugspringtest.domain.Lead;
import com.guillaumeyan.bugspringtest.model.LeadDTO;
import com.guillaumeyan.bugspringtest.repos.LeadRepository;


@Service
public class LeadService {

    private final LeadRepository leadRepository;
    private final User user;

    @Autowired
    public LeadService(final LeadRepository leadRepository, final User user) {
        this.leadRepository = leadRepository;
        this.user = user;
    }

    public List<LeadDTO> findAll() {
        return leadRepository.findAll()
                .stream()
                .map(lead -> mapToDTO(lead, new LeadDTO()))
                .collect(Collectors.toList());
    }

    public LeadDTO get(final Long id) {
        return leadRepository.findById(id)
                .map(lead -> mapToDTO(lead, new LeadDTO()))
                .orElseThrow(RuntimeException::new);
    }

    public Long create(final LeadDTO leadDTO) {
        user.setName(leadDTO.getName());
        final Lead lead = new Lead();
        mapToEntity(leadDTO, lead);
        return leadRepository.save(lead).getId();
    }

    public void update(final Long id, final LeadDTO leadDTO) {
        final Lead lead = leadRepository.findById(id)
                .orElseThrow(RuntimeException::new);
        mapToEntity(leadDTO, lead);
        leadRepository.save(lead);
    }

    public void delete(final Long id) {
        leadRepository.deleteById(id);
    }

    private LeadDTO mapToDTO(final Lead lead, final LeadDTO leadDTO) {
        leadDTO.setId(lead.getId());
        leadDTO.setName(lead.getName());
        leadDTO.setBirthDate(lead.getBirthDate());
        return leadDTO;
    }

    private Lead mapToEntity(final LeadDTO leadDTO, final Lead lead) {
        lead.setName(leadDTO.getName());
        lead.setBirthDate(leadDTO.getBirthDate());
        return lead;
    }

}
