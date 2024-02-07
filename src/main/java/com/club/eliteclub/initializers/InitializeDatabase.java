package com.club.eliteclub.initializers;
import org.springframework.web.reactive.function.client.WebClient;
import com.club.eliteclub.entity.Billionaires;
import com.club.eliteclub.exception.InitializationFailedException;
import com.club.eliteclub.repository.BillionairesRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;



import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class InitializeDatabase implements ApplicationRunner {


    @Autowired
    BillionairesRepository billionairesRepository;
    @Autowired
    Forbes400Properties forbes400Properties;
    @Autowired
    WebClient webClient;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("refreshing DB...");
        fillDatabaseDuringStartup();
        log.info("DB refresh complete...");
    }

    private void fillDatabaseDuringStartup() throws JsonProcessingException {
        List<Billionaires> billionaires = new ArrayList<>();

        final ResponseEntity<String> forbes400ResponseEntity = webClient.get()
                .retrieve()
                .toEntity(String.class)
                .block();
        if (forbes400ResponseEntity == null || forbes400ResponseEntity.getStatusCode().isError()) {
            throw new InitializationFailedException("Issue with forbes 400 service");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        final JsonNode jsonNode = objectMapper.readTree(forbes400ResponseEntity.getBody());
        if (jsonNode.isArray()) {
            for (JsonNode eachBillionaire : jsonNode) {
                Billionaires bill = new Billionaires();
                final String billionaireString = eachBillionaire.toString();
                String firstname = JsonPath.read(billionaireString, "$.personName");
                bill.setPersonName(firstname);
                String lastname = JsonPath.read(billionaireString, "$.lastName");
                bill.setLastName(lastname);
                String company = JsonPath.read(billionaireString, "$.source");
                bill.setCompany(company);
                Integer netWorth = JsonPath.read(billionaireString, "$.rank");
                bill.setWealthRank(netWorth);
                billionaires.add(bill);
            }
        }
        billionairesRepository.saveAll(billionaires);

    }


}
