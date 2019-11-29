package com.tutorialspoint.demo.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class FixedWithDelayScheduler {
    // There was an initialDelay before the jobs
    // After the initial delay, it will be a fixed delay
    @Scheduled(fixedDelay = 1000000, initialDelay = 300000)
    public void fixedRateSch() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date now = new Date();
        String strDate = sdf.format(now);
        System.out.println("Fixed Delay scheduler:: " + strDate);
    }
}