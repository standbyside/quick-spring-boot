package com.zn.quick.spring.boot.spark.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Properties;

/**
 * @author zhaona
 * @create 2018/8/13 上午11:53
 */
@Data
@ConfigurationProperties("spark")
public class SparkProperties {

  private String appName;

  private String master;

  private Properties props = new Properties();

  private StreamingProperties streaming = new StreamingProperties();

  @Data
  @ConfigurationProperties("streaming")
  public static class StreamingProperties {

    private Integer duration;

  }
}
