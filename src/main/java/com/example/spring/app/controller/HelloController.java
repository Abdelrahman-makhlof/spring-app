package com.example.spring.app.controller;


import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
@RequestMapping("api/v1/hello")
public class HelloController {
    @GetMapping
    public String hello() throws UnknownHostException {
        InetAddress localHost = InetAddress.getLocalHost();

        // Get the host name
        String hostName = localHost.getHostName();
        return "Hello, from " + hostName + " :)";
    }

    @GetMapping("/{name}")
    public String hello(@PathVariable String name) throws UnknownHostException {
        InetAddress localHost = InetAddress.getLocalHost();

        // Get the host name
        String hostName = localHost.getHostName();
        return "Hello "+name +",\nfrom " + hostName + " :)";
    }
}
