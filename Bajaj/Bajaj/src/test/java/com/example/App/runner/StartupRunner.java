package com.example.App.runner;

import com.example.App.model.GenerateWebhookResponse;
import com.example.App.model.UserRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class StartupRunner implements CommandLineRunner {

    private final RestTemplate restTemplate;

    public StartupRunner(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void run(String... args) {
        try {
            // Step 1: Generate webhook
            String generateUrl = "https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA";
            UserRequest request = new UserRequest("Somil Kirar", "REG12347", "somildhakad500@gmail.com");

            HttpHeaders headers1 = new HttpHeaders();
            headers1.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<UserRequest> entity1 = new HttpEntity<>(request, headers1);

            ResponseEntity<GenerateWebhookResponse> response =
                    restTemplate.exchange(generateUrl, HttpMethod.POST, entity1, GenerateWebhookResponse.class);

            GenerateWebhookResponse body = response.getBody();
            if (body == null) {
                System.err.println("Failed to get webhook/AccessToken!");
                return;
            }

            String webhookUrl = body.getWebhook();
            String token = body.getAccessToken();

            System.out.println("Webhook: " + webhookUrl);
            System.out.println("AccessToken: " + token);

            // Step 2: Solve SQL (replace this with your real query)
            String finalQuery = "SELECT * FROM employees;";

            // Step 3: Submit to webhook
            HttpHeaders headers2 = new HttpHeaders();
            headers2.setContentType(MediaType.APPLICATION_JSON);
            headers2.set("Authorization", "Bearer " + token); // safer

            Map<String, String> bodyMap = Map.of("finalQuery", finalQuery);
            HttpEntity<Map<String, String>> entity2 = new HttpEntity<>(bodyMap, headers2);

            ResponseEntity<String> submitResp =
                    restTemplate.exchange(webhookUrl, HttpMethod.POST, entity2, String.class);

            System.out.println("Submit Response: " + submitResp.getStatusCode() + " → " + submitResp.getBody());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
