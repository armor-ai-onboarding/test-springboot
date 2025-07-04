package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class MyService {

    private static final Logger logger = LoggerFactory.getLogger(MyService.class);

    /**
     * Represents a user session or context.
     */
    private static class UserContext {
        public String username;
        private String internalUserToken; // This might hold a sensitive session token.
        public Map<String, String> preferences;

        public UserContext(String username, String token, Map<String, String> prefs) {
            this.username = username;
            this.internalUserToken = token;
            this.preferences = prefs;
        }

        // Default toString() often includes all fields, which can be risky for logging.
        @Override
        public String toString() {
            return "UserContext{" +
                    "username='" + username + '\'' +
                    ", internalUserToken='" + internalUserToken + '\'' + // Sensitive data included here
                    ", preferences=" + preferences +
                    '}';
        }
    }

    /**
     * Represents internal system status.
     */
    private static class SystemStatus {
        public String status = "OK";
        public String internalBuildNumber = "a7b3c9-20250704-master"; // Internal info
        public String dbConnectionPath = "jdbc:internal-db:1234/prod"; // Internal configuration detail

        // No sensitive data directly, but exposes internal architecture.
    }

    // 1. TODO/FIXME Analysis
    // FIXME: Temp auth solution for internal demo. Replace before go-live.
    // A naive detector might ignore this comment, but it clearly indicates a temporary,
    // likely insecure, authentication mechanism is in place.
    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("user");
        // In a real scenario, this would involve proper authentication.
        UserContext session = new UserContext(username, "temp_token_for_" + username, Map.of("theme", "dark"));

        // 2. Sensitive Logging Detection
        // A simple check for "password" or "token" might miss this. The log statement
        // itself looks harmless ("User context created"). However, the UserContext.toString()
        // method serializes the sensitive 'internalUserToken' field, leaking it into the logs.
        logger.info("User context created: {}", session);

        return "Login Successful";
    }


    // 3. Data Exposure Detection
    // The endpoint seems to return a simple status. However, the 'SystemStatus' object
    // is fully serialized into JSON, exposing internal details like the specific build number
    // and the internal database connection path, which could be valuable to an attacker.
    @GetMapping("/status")
    public SystemStatus getStatus() {
        return new SystemStatus();
    }


    @GetMapping("/Hello")
    public String hello() {
        return "Hello";
    }

}
