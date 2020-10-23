package com.guillaumeyan.bugspringtest.rest;

import com.guillaumeyan.bugspringtest.service.LeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.guillaumeyan.bugspringtest.model.LeadDTO;

import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;


@RestController
@RequestMapping(value = "/api/leads", produces = MediaType.APPLICATION_JSON_VALUE)
public class LeadController {

    private final LeadService leadService;

    @Autowired
    public LeadController(final LeadService leadService) {
        this.leadService = leadService;
    }

    @GetMapping
    public List<LeadDTO> getAllLeads() {
        return leadService.findAll();
    }

    @GetMapping("/{id}")
    public LeadDTO getLead(@PathVariable final Long id) {
        return leadService.get(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long createLead(@RequestBody @Valid final LeadDTO leadDTO) {
        return leadService.create(leadDTO);
    }

    @PutMapping("/{id}")
    public void updateLead(@PathVariable final Long id, @RequestBody @Valid final LeadDTO leadDTO) {
        leadService.update(id, leadDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLead(@PathVariable final Long id) {
        leadService.delete(id);
    }

}
