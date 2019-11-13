package org.wj.prajumsook.profile.demoprofile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {

    @Autowired
    ApplicationService applicationService;

    @GetMapping("/status")
    public String status() {
        return applicationService.message();
    }
}
