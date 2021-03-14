package novosrecursos.java11;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class PadronizacaoHttpClient {
	
	public static void main(String[] args) {
		HttpRequest request = HttpRequest.newBuilder()
		          .uri(URI.create("http://www.google.com"))
		          .build();

		HttpResponse.BodyHandler<String> bodyHandler = HttpResponse.BodyHandlers.ofString();

		HttpClient client = HttpClient.newHttpClient();
		CompletableFuture<HttpResponse<String>> future = client.sendAsync(request, bodyHandler);
		future.thenApply(HttpResponse::body)
		          .thenAccept(System.out::println)
		          .join();
	}

}
