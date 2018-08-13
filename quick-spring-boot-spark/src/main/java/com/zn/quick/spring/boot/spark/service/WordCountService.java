package com.zn.quick.spring.boot.spark.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scala.Tuple2;

import java.util.Arrays;
import java.util.Map;

/**
 * @author zhaona
 * @create 2018/8/13 下午2:51
 */
@Slf4j
@Service
public class WordCountService {

  @Autowired
  private JavaSparkContext sc;

  public Map<String, Integer> run() {
    log.info("------------word count run----------");

    JavaRDD<String> lines = sc.textFile("input-files/spark/english-words.txt").cache();

    JavaRDD<String> words = lines.flatMap(
        (FlatMapFunction<String, String>) s -> Arrays.asList(s.split(" ")).iterator()
    );

    JavaPairRDD<String, Integer> ones = words.mapToPair(
        (PairFunction<String, String, Integer>) s -> new Tuple2<>(s, 1)
    );

    JavaPairRDD<String, Integer> counts = ones.reduceByKey(
        (Function2<Integer, Integer, Integer>) (i1, i2) -> i1 + i2
    );

    Map<String, Integer> result = counts.collectAsMap();

    return result;

  }
}
