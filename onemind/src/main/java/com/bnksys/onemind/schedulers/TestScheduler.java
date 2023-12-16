package com.bnksys.onemind.schedulers;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TestScheduler {

    @Scheduled(cron = "0 0 0 * * *")
    public void task(){
        System.out.println("");
    }

}
