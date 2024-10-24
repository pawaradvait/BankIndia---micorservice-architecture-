package com.bankIndia.account.dto;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
@ConfigurationProperties(prefix = "account")
public record AccountContactInfoDto(String message,Map<String,String> contactDetails,List<String> onCallSupport) {

}
