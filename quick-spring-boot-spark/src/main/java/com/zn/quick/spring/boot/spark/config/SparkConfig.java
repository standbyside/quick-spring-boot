package com.zn.quick.spring.boot.spark.config;

import com.zn.quick.spring.boot.spark.config.properties.SparkProperties;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

import java.util.Optional;


/**
 * @author zhaona
 * @create 2018/8/13 上午11:40
 */
@Configuration
@ConditionalOnClass({ SparkConf.class, JavaSparkContext.class })
@EnableConfigurationProperties(SparkProperties.class)
public class SparkConfig {

  @Autowired
  private SparkProperties sparkProperties;

  @Bean
  @ConditionalOnMissingBean
  public SparkConf sparkConf() {
    Optional.ofNullable(sparkProperties.getAppName())
        .orElseThrow(() ->
            new IllegalArgumentException("Spark Conf Bean not created. App Name not defined.")
        );
    final SparkConf conf = new SparkConf();
    sparkProperties.getProps().forEach(
        (prop, value) -> conf.set("spark." + prop, (String) value)
    );
    return conf.setAppName(sparkProperties.getAppName())
        .setMaster(
            Optional.ofNullable(sparkProperties.getMaster())
                .orElse("local")
        );
  }

  @Bean
  @ConditionalOnMissingBean
  @ConditionalOnBean(SparkConf.class)
  public JavaSparkContext sparkContext() {
    return new JavaSparkContext(sparkConf());
  }

  @Bean
  @ConditionalOnMissingBean
  @ConditionalOnClass(JavaStreamingContext.class)
  @ConditionalOnBean(JavaSparkContext.class)
  @ConditionalOnProperty(name = "spark.streaming.duration")
  public JavaStreamingContext streamingContext() {
    return new JavaStreamingContext(
        sparkContext(),
        Durations.seconds(sparkProperties.getStreaming().getDuration())
    );
  }


  @Bean
  @ConditionalOnMissingBean
  @ConditionalOnClass(SparkSession.class)
  @ConditionalOnProperty(name = "spark.appName")
  public SparkSession sqlSession() {
    Optional.ofNullable(sparkProperties.getAppName())
        .orElseThrow(() ->
            new IllegalArgumentException("Spark Session Bean not created. App Name not defined.")
        );
    return SparkSession.builder()
        .appName(sparkProperties.getAppName())
        .config(sparkConf())
        .getOrCreate();
  }
}
