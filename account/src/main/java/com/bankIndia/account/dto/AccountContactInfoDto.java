package com.bankIndia.account.dto;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;
@ConfigurationProperties(prefix = "account")
@Data
public class AccountContactInfoDto {

   private String message;
    private Map<String,String> contactDetails;
   private  List<String> onCallSupport;

}
