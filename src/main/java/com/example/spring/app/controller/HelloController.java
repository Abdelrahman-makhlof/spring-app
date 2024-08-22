package com.example.spring.app.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("api/v1/hello")
public class HelloController {

    @GetMapping
    public String helloWithParam(@RequestParam(required = false) String name) throws UnknownHostException {
        InetAddress localHost = InetAddress.getLocalHost();

        // Get the host name
        String hostName = localHost.getHostName();
        if (name != null)
            return "Hello " + name + ", from " + hostName + " :)";
        return "Hello, from " + hostName + " :)";
    }


    @GetMapping("/{name}")
    public String hello(@PathVariable String name) throws UnknownHostException {
        InetAddress localHost = InetAddress.getLocalHost();

        // Get the host name
        String hostName = localHost.getHostName();
        return "Hello " + name + ",\nfrom " + hostName + " :)";
    }


    /**
     * Example how to handle HttpServletRequest and return HttpServletResponse in Rest API,
     * also read all details from the request
     * without using annotations.<br>
     * <p>
     * You can get query param from HttpServletRequest using getParameter(paramName).
     * Url contains the full URL (without the query params)<br>
     * Uet uri contains the path (without protocol, ip an port)
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     */
    @GetMapping("/http/request")
    public void hello(HttpServletRequest request, HttpServletResponse response) throws IOException {
        var fName = request.getParameter("fname");
        var lName = request.getParameter("lname");
        var url = request.getRequestURL();
        var uri = request.getRequestURI();
        var query = request.getQueryString();

        var output = new StringBuilder();
        var body = new StringBuilder();


        try (BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                body.append(line);
            }
        }

        output.append("Hello " + fName + " " + lName);
        output.append("\n----------------------------------------\n");
        output.append("\nURL: " + url);
        output.append("\nURI: " + uri);
        output.append("\nQuery: " + query);
        output.append("\n");
        output.append("\nUser name: " + request.getHeader("user"));
        output.append("\nRequest body: " + body.toString());

        response.getWriter().write(output.toString());
    }


    /**
     * Example how to handle RequestEntity in Rest API,
     * also read all details from the request
     * without using annotations.<br>
     * <p>
     * You can't get query param from RequestEntity direct, you need to read the query and
     * split the parameters.<br>
     * Url contains the full URL (including the query params)
     *
     * @param request RequestEntity
     * @return ResponseEntity
     */
    @GetMapping("/req/entity")
    public ResponseEntity<String> hello(RequestEntity<String> request) {
        var output = new StringBuilder();
        var requestUrl = request.getUrl();
        var headers = request.getHeaders();

        Map<String, String> queryParams = parseQueryParams(requestUrl.getQuery());

        output.append("Hello " + queryParams.get("fname") + " " + queryParams.get("lname"));
        output.append("\n--------------------------");
        output.append("\nURL: " + requestUrl);
        output.append("\nPath: " + requestUrl.getPath());
        output.append("\nQuery: " + requestUrl.getQuery());
        output.append("\nRaw query: " + requestUrl.getRawQuery());
        output.append("\n-------------------------");
        output.append("\nHeaders: " + headers);
        output.append("\nUser name: " + headers.getFirst("user"));
        output.append("\n-------------------------");
        output.append("\nBody: " + request.getBody());

        return new ResponseEntity<>(output.toString(), HttpStatus.OK);

    }


    private Map<String, String> parseQueryParams(String query) {
        if (query == null || query.isEmpty()) {
            return Map.of();
        }
        return Stream.of(query.split("&"))
                .map(param -> param.split("="))
                .collect(Collectors.toMap(arr -> arr[0], arr -> arr.length > 1 ? arr[1] : ""));
    }
}
