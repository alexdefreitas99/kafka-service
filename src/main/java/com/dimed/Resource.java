package com.dimed;

import com.dimed.model.Customer;
import com.dimed.service.CustomerService;
import com.dimed.service.KafkaProducerService;
//import com.dimed.service.RemetenteService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class Resource {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @PostMapping("/kafka")
    public String toProduceMessage(@RequestBody @Valid Customer customer) throws JsonProcessingException {
        kafkaProducerService.sendMessage(new ObjectMapper().writeValueAsString(customer));
        return "Message sent to kafka";
    }

    @PostMapping(path = "/customer")
    public Customer createCustomer(@RequestBody @Valid Customer customer) {
        return customerService.createCostumer(customer);
    }

//    @PostMapping(path = "/remententes ")
//    public String createRemetente(@RequestParam String rementente) {
//        return remetenteService.createRemetente(rementente);
//    }

//    @GetMapping(path = "/kafka")
//    public String consumer(@RequestParam String topic) {
//        return
//    }
}