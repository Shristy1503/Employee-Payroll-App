package com.bridgelabz.employeepayrollapp.service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LoggingService {
    @Value("${spring.profiles.active}")
    private String activeProfile;

    public void logMessage() {
        log.info("Application running in profile: {}", activeProfile);

        if ("dev".equals(activeProfile)) {
            log.debug("This is a debug message for development mode.");
        } else if ("staging".equals(activeProfile)) {
            log.info("This is an info message for staging.");
        } else if ("prod".equals(activeProfile)) {
            log.warn("This is a warning message for production!");
        }
    }
}
