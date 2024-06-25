package com.cbehrens.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@SpringBootApplication
public class FormegptApplication {

	public static void main(String[] args) throws IOException, InterruptedException {
		SpringApplication.run(FormegptApplication.class, args);

		String apiKey = System.getenv("OPENAPIKEY");
		var body = """
				{
				    "model": "gpt-3.5-turbo-0125",
				    "messages": [
				        {
				            "role": "user",
				            "content": "Tell me another good dad joke about dogs"
				        }
				    ]
				}""";

		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://api.openai.com/v1/chat/completions"))
				.header("Content-Type", "application/json")
				.header("Authorization", "Bearer " + apiKey)
				.POST(HttpRequest.BodyPublishers.ofString(body))
				.build();

		var client = HttpClient.newHttpClient();
		var response = client.send(request, HttpResponse.BodyHandlers.ofString());

	}
}

