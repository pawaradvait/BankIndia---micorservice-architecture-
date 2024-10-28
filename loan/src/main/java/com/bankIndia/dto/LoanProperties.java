package com.bankIndia.dto;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties(prefix = "loan")
@Data
public class LoanProperties {

    private String message;
    private Map<String,String> contactDetails;
   private  List<String> onCallSupport;

}
