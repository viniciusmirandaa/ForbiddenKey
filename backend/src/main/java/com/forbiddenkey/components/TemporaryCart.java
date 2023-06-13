package com.forbiddenkey.components;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TemporaryCart {

    @Scheduled(fixedRate = 10000)
    public void verifyCartActualDate(){

    }
}
