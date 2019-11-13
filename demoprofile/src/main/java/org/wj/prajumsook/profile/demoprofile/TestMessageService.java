package org.wj.prajumsook.profile.demoprofile;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"test", "default"})
public class TestMessageService implements ApplicationService {

    @Override
    public String message() {
        return "Test message";
    }

}
