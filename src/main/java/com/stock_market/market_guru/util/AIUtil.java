package com.stock_market.market_guru.util;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AIUtil {

  private static final Logger log = LoggerFactory.getLogger("application");

  private static final HttpClient client = HttpClient.newHttpClient();

  private static final String GEMINI_API_URL =
      "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=AIzaSyCX0ReBG_jyXVXVaWBuj9Pn-kbXj_F4ecw";
  private static final String OPEN_API_URL = "https://api.openai.com/v1/chat/completions";
  private static final String API_KEY =
      "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";

  public static String callGEMINI(String prompt) {
    try {
      String jsonPayload =
          String.format(
              """
                {
                   "contents": [{
                     "parts":[{"text": "%s"}]
                     }]
                    }
                """,
                  prompt);

      // Build the HTTP request
      HttpRequest request =
          HttpRequest.newBuilder()
              .uri(URI.create(GEMINI_API_URL))
              .header("Content-Type", "application/json")
              .POST(HttpRequest.BodyPublishers.ofString(jsonPayload))
              .build();

      // Send the request and get the response as a String
      HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
      String responseBody = response.body();

      // Parse the JSON response using Gson
      Gson gson = new Gson();
      JsonObject jsonResponse = gson.fromJson(responseBody, JsonObject.class);
      JsonObject message = jsonResponse.getAsJsonArray("candidates")
              .get(0).getAsJsonObject()
              .getAsJsonObject("content")
              .getAsJsonArray("parts").get(0).getAsJsonObject();

      String content = message.get("text").getAsString();
      return removeMarkdownMarkers(content);
    } catch (Exception e) {
      log.error("Error while calling AI model for translation: {}", e.getMessage());
    }
    return null;
  }

  private static String removeMarkdownMarkers(String content) {
    String cleaned = content.trim();
    if (cleaned.startsWith("```json")) {
      cleaned = cleaned.substring("```json".length());
    }
    if (cleaned.endsWith("```")) {
      cleaned = cleaned.substring(0, cleaned.lastIndexOf("```"));
    }
    return cleaned.replace("\n", "").trim();
  }

}
