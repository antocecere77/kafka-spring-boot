package com.antocecere77.kafka.api.server;

import com.antocecere77.kafka.api.request.PromotionRequest;
import com.antocecere77.kafka.command.service.PromotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/promotion")
@RequiredArgsConstructor
public class PromotionApi {

    private final PromotionService promotionService;

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> create(@RequestBody PromotionRequest request) {

        promotionService.createPromotion(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(request.getPromotionCode());
    }

}
