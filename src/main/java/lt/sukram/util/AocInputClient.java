package lt.sukram.util;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AocInputClient {

    private static final Logger log = Logger.getLogger(AocInputClient.class.getName());

    private final String session;
    private final HttpClient client;

    public AocInputClient(String session) {
        this.session = session;
        this.client = HttpClient.newHttpClient();
    }

    public String fetchInputForDay(int day) {
        String url = String.format("https://adventofcode.com/2024/day/%d/input", day);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Cookie", String.format("session=%s", session))
                .build();
        try {
            log.log(Level.INFO, "Fetching input for day {0}", day);
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            log.log(Level.INFO, "Fetch of input done. Status: {0}", response.statusCode());
            return response.body();
        } catch (Exception e) {
            log.log(Level.WARNING, "Failed to fetch input for day {0}", day);
        }
        return "";
    }
}
