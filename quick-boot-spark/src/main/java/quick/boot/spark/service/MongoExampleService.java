package quick.boot.spark.service;

import com.mongodb.spark.MongoSpark;
import com.mongodb.spark.config.ReadConfig;
import com.mongodb.spark.config.WriteConfig;
import com.mongodb.spark.rdd.api.java.JavaMongoRDD;
import lombok.extern.slf4j.Slf4j;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.bson.Document;

import java.util.HashMap;
import java.util.Map;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

@Slf4j
@Service
public class MongoExampleService {

  @Autowired
  private JavaSparkContext sc;

  @Autowired
  private SparkSession sparkSession;

  public void write() {
    log.info("------------mongo example write----------");
    Map<String, String> writeOverrides = new HashMap() {
      {
        put("collection", "spark");
        put("writeConcern.w", "majority");
      }
    };
    WriteConfig writeConfig = WriteConfig.create(sc).withOptions(writeOverrides);

    JavaRDD<Document> sparkDocuments = sc.parallelize(asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
        .map((Function<Integer, Document>) i -> Document.parse("{spark: " + i + "}"));

    MongoSpark.save(sparkDocuments, writeConfig);
  }

  public Object read() {
    log.info("------------mongo example read----------");
    Map<String, String> readOverrides = new HashMap() {
      {
        put("collection", "spark");
        put("readPreference.name", "secondaryPreferred");
      }
    };

    ReadConfig readConfig = ReadConfig.create(sc).withOptions(readOverrides);

    JavaMongoRDD<Document> rdd = MongoSpark.load(sc, readConfig);
    return rdd.first().toJson();
  }

  public Object aggregation() {
    log.info("------------mongo example aggregation----------");
    JavaMongoRDD<Document> rdd = MongoSpark.load(sc);

    JavaMongoRDD<Document> aggregatedRdd = rdd.withPipeline(
        singletonList(
            Document.parse("{ $match: { test : { $gt : 5 } } }")
        )
    );

    return aggregatedRdd.first().toJson();
  }

  public Object dataset() {
    log.info("------------mongo example dataset----------");
    // Load data and infer schema, disregard toDF() name as it returns Dataset
    Dataset<Row> implicitDS = MongoSpark.load(sc).toDF();
    implicitDS.printSchema();
    implicitDS.show();

    // Load data with explicit schema
    Dataset<Character> explicitDS = MongoSpark.load(sc).toDS(Character.class);
    explicitDS.printSchema();
    explicitDS.show();

    // Create the temp view and execute the query
    explicitDS.createOrReplaceTempView("characters");
    Dataset<Row> centenarians = sparkSession.sql("SELECT name, age FROM characters WHERE age >= 100");
    centenarians.show();

    // Write the data to the "hundredClub" collection
    MongoSpark.write(centenarians).option("collection", "hundredClub").mode("overwrite").save();

    // Load the data from the "hundredClub" collection
    MongoSpark.load(
        sparkSession,
        ReadConfig.create(sparkSession).withOption("collection", "hundredClub"),
        Character.class
    ).show();
    return null;
  }
}
