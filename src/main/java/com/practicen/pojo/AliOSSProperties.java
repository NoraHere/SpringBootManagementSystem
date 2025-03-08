package com.practicen.pojo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "aliyun.oss") // replace @Value to autowired a mass of config
public class AliOSSProperties {
    private String endpoint;
    private String bucketName;
    private String region;
}
