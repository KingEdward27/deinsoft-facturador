package com.deinsoft.efacturador3commons.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class AppConfig
{
  @NotEmpty
  @Value("${app.rootPath}")
  private String rootPath;
  
  @NotEmpty
  @Value("${plazoBoleta}")
  private String plazoBoleta;
  
  @NotEmpty
  @Value("${app.environment}")
  private String environment;
  
  @NotEmpty
  @Value("${app.sendEmail.email}")
  private String sendEmailEmail;

  @NotEmpty
  @Value("${app.sendEmail.password}")
  private String sendEmailPassword;
  
  @NotEmpty
  @Value("${app.urlServiceCDR}")
  private String urlServiceCDR;
  
  @NotEmpty
  @Value("${app.urlServiceCDP}")
  private String urlServiceCDP;
  
  
  @NotEmpty
  @Value("${app.timeZone.diferenceHours}")
  private long diferenceHours;
  
  @JsonProperty
  public String getRootPath() {
    return this.rootPath;
  }
  
  @JsonProperty
  public void setRootPath(String rootPath) {
    this.rootPath = rootPath;
  }

    public String getPlazoBoleta() {
        return plazoBoleta;
    }

    public void setPlazoBoleta(String plazoBoleta) {
        this.plazoBoleta = plazoBoleta;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getSendEmailEmail() {
        return sendEmailEmail;
    }

    public void setSendEmailEmail(String sendEmailEmail) {
        this.sendEmailEmail = sendEmailEmail;
    }

    public String getSendEmailPassword() {
        return sendEmailPassword;
    }

    public void setSendEmailPassword(String sendEmailPassword) {
        this.sendEmailPassword = sendEmailPassword;
    }

    public String getUrlServiceCDR() {
        return urlServiceCDR;
    }

    public void setUrlServiceCDR(String urlServiceCDR) {
        this.urlServiceCDR = urlServiceCDR;
    }

    public String getUrlServiceCDP() {
        return urlServiceCDP;
    }

    public void setUrlServiceCDP(String urlServiceCDP) {
        this.urlServiceCDP = urlServiceCDP;
    }

    public long getDiferenceHours() {
        return diferenceHours;
    }

    public void setDiferenceHours(long diferenceHours) {
        this.diferenceHours = diferenceHours;
    }
  
  
}
