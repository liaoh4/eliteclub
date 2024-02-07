package com.club.eliteclub.controller;


import com.club.eliteclub.entity.Billionaires;
import com.club.eliteclub.service.BillionairesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/forbes400")
public class BillionaireController {
    private static final Logger logger = LoggerFactory.getLogger(BillionaireController.class
    );

    @Autowired
    BillionairesService billionairesService;

    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Billionaires> findAll() {
        logger.info("findall method");
        return billionairesService.listAll();
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> delete(@PathVariable Long id) {
        logger.info("deleting record for - id - {}", id);
        billionairesService.deleteBillionaire(id);
        return ResponseEntity.ok().body("User deleted successfully");

    }

    @PostMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public String save(@RequestBody Billionaires billionaires) {
        logger.info("billionaire record saving.");
        logger.debug("save method, First Name - {}", billionaires.getPersonName());
        logger.debug("save method Last Name - {}", billionaires.getLastName());
        logger.debug("save method Comapny - {}", billionaires.getCompany());
        logger.debug("save method Wealth - {}", billionaires.getWealthRank());

        billionairesService.createBillionaire(billionaires);
        return "created";

    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String update(@PathVariable long id, @RequestBody Billionaires billionaires) {
        logger.info("billionaire record Updaing.");
        logger.debug("update method, First Name - {}", billionaires.getPersonName());
        logger.debug("update method Last Name - {}", billionaires.getLastName());
        logger.debug("update method Comapny - {}", billionaires.getCompany());
        logger.debug("update method Wealth - {}", billionaires.getWealthRank());

        billionaires.setId(id);
        billionairesService.updateBillionaire(billionaires);
        return "updated";
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Billionaires> fetchBillionaire(@PathVariable long id) {
        logger.info("fetchBillionaire method for - id - {}", id);
        return billionairesService.getBillionaire(id);

    }
}
