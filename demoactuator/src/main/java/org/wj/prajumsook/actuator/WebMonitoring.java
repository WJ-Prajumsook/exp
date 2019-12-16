package org.wj.prajumsook.actuator;

import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "web-monitor")
public class WebMonitoring {

    @ReadOperation
    public String read() {
        return "Read operation";
    }

    @WriteOperation
    public String write() {
        return "Write operation";
    }

    @DeleteOperation
    public String delete() {
        return "Delete operation";
    }
}
