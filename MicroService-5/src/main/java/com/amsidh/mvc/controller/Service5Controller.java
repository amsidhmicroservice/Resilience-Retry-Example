package com.amsidh.mvc.controller;

import com.amsidh.mvc.domain.ResponseService5;
import com.amsidh.mvc.entities.Service5;
import com.amsidh.mvc.service.Service5Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;

@RequiredArgsConstructor
@RestController
@RequestMapping("/service5")
@Slf4j
public class Service5Controller {

    private final Service5Service service5Service;

    private int requestCount = 0;

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ResponseService5> getService5ById(@PathVariable("service5Id") Integer service5Id) {
        log.info("Request No- " + requestCount++);
        // simulate failure
        int random = ThreadLocalRandom.current().nextInt(1, 4);
        log.info("Random Value " + random);
        if (random < 3) {
            return ResponseEntity.status(500).build();
        } else {
            Service5 service5 = service5Service.getService5ById(service5Id);
            return ResponseEntity.ok(ResponseService5.builder()
                    .service5Id(service5.getService5Id())
                    .service5Message(service5.getService5Message())
                    .build());
        }
    }

}
