package org.wj.prajumsook.profile.demoprofile;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
public class ProdMessageService implements ApplicationService {

    @Override
    public String message() {
        return "PROD message";
    }

}
