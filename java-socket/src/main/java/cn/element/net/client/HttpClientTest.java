package cn.element.net.client;

import java.io.*;
import java.math.*;
import java.net.*;
import java.nio.file.*;
import java.util.*;

import java.net.http.*;
import java.net.http.HttpRequest.*;

public class HttpClientTest {
    
    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
        System.setProperty("jdk.httpclient.HttpClient.log", "headers,errors");
        
        String path = "java-socket/src/main/resources/postClient.properties";
        String propsFilename = args.length > 0 ? args[0] : path;
        Path propsPath = Paths.get(propsFilename);
        var props = new Properties();
        
        try (InputStream in = Files.newInputStream(propsPath)) {
            props.load(in);
        }
        
        String urlString = "" + props.remove("url");
        String contentType = "" + props.remove("Content-Type");
        
        if (contentType.equals("multipart/form-data")) {
            var generator = new Random();
            String boundary = new BigInteger(256, generator).toString();
            contentType += ";boundary=" + boundary;
            props.replaceAll((k, v) ->
                    v.toString().startsWith("file://")
                            ? propsPath.getParent().resolve(Paths.get(v.toString().substring(7)))
                            : v);
        }
        
        String result = doPost(urlString, contentType, props);
        System.out.println(result);
    }

    public static String doPost(String url, String contentType, Map<Object, Object> data) throws IOException, URISyntaxException, InterruptedException {
        HttpClient client = HttpClient.newBuilder()
                                      .followRedirects(HttpClient.Redirect.ALWAYS).build();

        BodyPublisher publisher;
        if (contentType.startsWith("multipart/form-data")) {
            String boundary = contentType.substring(contentType.lastIndexOf("=") + 1);
            publisher = MoreBodyPublishers.ofMimeMultipartData(data, boundary);
        } else if (contentType.equals("application/x-www-form-urlencoded")) {
            publisher = MoreBodyPublishers.ofFormData(data);
        } else {
            contentType = "application/json";
            publisher = MoreBodyPublishers.ofSimpleJSON(data);
        }

        HttpRequest request = HttpRequest.newBuilder()
                                         .uri(new URI(url))
                                         .header("Content-Type", contentType)
                                         .POST(publisher)
                                         .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}
