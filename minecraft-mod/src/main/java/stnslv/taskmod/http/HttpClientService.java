package stnslv.taskmod.http;

import com.google.gson.Gson;
import stnslv.taskmod.Taskmod;
import stnslv.taskmod.dto.PlayerMessageDto;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;

/**
 * HTTP client service for communicating with the backend.
 */
public class HttpClientService {

    private static final String BACKEND_URL = "http://localhost:8080/api/messages";
    private static final HttpClient HTTP_CLIENT = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10))
            .build();
    private static final Gson GSON = new Gson();

    /**
     * Sends a player message to the Spring backend.
     */
    public static CompletableFuture<PlayerMessageDto> sendMessageToBackend(PlayerMessageDto dto) {
        try {
            String json = GSON.toJson(dto);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BACKEND_URL))
                    .timeout(Duration.ofSeconds(10))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            return HTTP_CLIENT.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .thenApply(response -> GSON.fromJson(response, PlayerMessageDto.class))
                    .exceptionally(e -> {
                        Taskmod.LOGGER.error("Failed to send message to backend", e);
                        return null;
                    });

        } catch (Exception e) {
            Taskmod.LOGGER.error("Error preparing message for backend", e);
        }
        return CompletableFuture.completedFuture(null);
    }
}
