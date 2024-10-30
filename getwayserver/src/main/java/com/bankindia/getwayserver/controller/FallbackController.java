package com.bankindia.getwayserver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class FallbackController {

    @RequestMapping("/contactsupport")
    public Mono<String> fallback() {

        return Mono.just("An error occurred. Please try after some time or contact support team!!!");
    }

}
