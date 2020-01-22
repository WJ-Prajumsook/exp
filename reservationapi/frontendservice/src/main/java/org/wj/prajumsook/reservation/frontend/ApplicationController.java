package org.wj.prajumsook.reservation.frontend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/reservation")
public class ApplicationController {



    @GetMapping("/state")
    public @ResponseBody String getState() {
        return "state";
    }


}
