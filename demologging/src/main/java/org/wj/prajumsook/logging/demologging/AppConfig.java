package org.wj.prajumsook.logging.demologging;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Component
@PropertySource("classpath:appconfig.properties")
@ConfigurationProperties(prefix = "appconfig")
@Validated
public class AppConfig {

    @NotNull
    private String appName;

    @NotNull
    @Email
    private String email;

    @Min(10)
    @Max(50)
    private Integer num;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }
}
