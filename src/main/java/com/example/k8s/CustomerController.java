package com.example.k8s;


import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
@RequestMapping("api/v1")
public class CustomerController {
    @GetMapping
    public String hello() throws UnknownHostException {
        InetAddress localHost = InetAddress.getLocalHost();

        // Get the host name
        String hostName = localHost.getHostName();
        return "Hello i am " + hostName + " :)";
    }
}
