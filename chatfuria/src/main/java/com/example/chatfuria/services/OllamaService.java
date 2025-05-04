package com.example.chatfuria.services;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.stream.Stream;

@Service
public class OllamaService {

    private final String ollamaUrl = "http://ollama:11434/api/generate";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String askOllama(String prompt) throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        String jsonBody = String.format(
                "{\"model\":\"llama2\", \"prompt\":\"%s\"}", prompt + "observação: responda em português"
        );

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(ollamaUrl))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse<String> response = client.send(
                request, HttpResponse.BodyHandlers.ofString()
        );

        return processJsonlResponse(response.body());
    }

    private String processJsonlResponse(String jsonlResponse) throws Exception {
        StringBuilder fullResponse = new StringBuilder();
        String[] lines = jsonlResponse.split("\n");

        for (String line : lines) {
            try {
                JsonLine jsonLine = objectMapper.readValue(line, JsonLine.class);
                
                if (jsonLine.response != null && !jsonLine.response.isEmpty()) {
                    fullResponse.append(jsonLine.response);
                }
                
                if (jsonLine.done) {
                    break;
                }
            } catch (Exception e) {
                System.err.println("Erro ao processar linha: " + line);
            }
        }

        return fullResponse.toString();
    }

    private static class JsonLine {
        public String model;
        public String created_at;
        public String response;
        public boolean done;
        public String done_reason;
        public int[] context;
        public long total_duration;
        public long load_duration;
        public int prompt_eval_count;
        public long prompt_eval_duration;
        public int eval_count;
        public long eval_duration;
    }

    public String askFallen(String question) throws Exception {
        HttpClient client = HttpClient.newHttpClient();

       
        String obs = "observação: responda em português como se fosse o FalleN, personalidade do Counter-Strike e jogador pela Furia E-Sports";

        String prompt = question + obs;

        String jsonBody = String.format(
                "{\"model\":\"llama2\", \"prompt\":\"%s\"}", prompt
        );

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(ollamaUrl))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse<String> response = client.send(
                request, HttpResponse.BodyHandlers.ofString()
        );

        return processJsonlResponse(response.body());

       }
}