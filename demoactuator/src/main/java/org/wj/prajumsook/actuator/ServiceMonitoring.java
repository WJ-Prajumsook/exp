package org.wj.prajumsook.actuator;

import org.springframework.boot.actuate.endpoint.web.annotation.RestControllerEndpoint;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@RestControllerEndpoint(id = "monitor")
public class ServiceMonitoring {

    @GetMapping
    public String status() {
        return "UP";
    }

    @PostMapping
    public String setStatus(@RequestBody String status) {
        return status;
    }
}
