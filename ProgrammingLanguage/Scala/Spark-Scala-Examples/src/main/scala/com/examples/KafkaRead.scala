package com.examples

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.types.{StringType, StructField, StructType, IntegerType}
import org.apache.spark.sql.functions.{col,asc,desc, from_json}


object KafkaRead extends App{

  val spark:SparkSession = SparkSession
    .builder()
    .master("local[1]")
    .appName("Spark-Scala-Examples")
    .getOrCreate()

  val columns = Seq("language","users_count")
  val data = Seq(("Java", "20000"), ("Python", "100000"), ("Scala", "3000"))
  val rdd = spark.sparkContext.parallelize(data)

  import spark.implicits._
  val df = rdd.toDF("language","users_count")
  df.printSchema()

  val df1 = spark.readStream
    .format("kafka")
    .option("kafka.bootstrap.servers", "192.168.1.100:9092")
    .option("subscribe", "json_topic")
    .option("startingOffsets", "earliest") // From starting
    .load()

  df1.printSchema()


  //Spark Streaming Write to Console
  val personStringDF = df.selectExpr("CAST(value AS STRING)")
  val schema = new StructType()
    .add("id",IntegerType)
    .add("firstname",StringType)
    .add("middlename",StringType)
    .add("lastname",StringType)
    .add("dob_year",IntegerType)
    .add("dob_month",IntegerType)
    .add("gender",StringType)
    .add("salary",IntegerType)

  val personDF = personStringDF.select(from_json(col("value"), schema).as("data"))
    .select("data.*")

  personDF.writeStream
    .format("console")
    .outputMode("append")
    .start()
    .awaitTermination()


  //Spark Streaming Write to Kafka Topic
  df.selectExpr("CAST(id AS STRING) AS key", "to_json(struct(*)) AS value")
    .writeStream
    .format("kafka")
    .outputMode("append")
    .option("kafka.bootstrap.servers", "192.168.1.100:9092")
    .option("topic", "josn_data_topic")
    .start()
    .awaitTermination()

  val data1 = Seq (("iphone", "2007"),("iphone 3G","2008"),
    ("iphone 3GS","2009"),
    ("iphone 4","2010"),
    ("iphone 4S","2011"),
    ("iphone 5","2012"),
    ("iphone 8","2014"),
    ("iphone 10","2017"))

  val df3 = spark.createDataFrame(data).toDF("key","value")
  /*
    since we are using dataframe which is already in text,
    selectExpr is optional.
    If the bytes of the Kafka records represent UTF8 strings,
    we can simply use a cast to convert the binary data
    into the correct type.

    df.selectExpr("CAST(key AS STRING)", "CAST(value AS STRING)")
  */
  df3.write
    .format("kafka")
    .option("kafka.bootstrap.servers","192.168.1.100:9092")
    .option("topic","text_topic")
    .save()


  //Spark SQL Batch Processing – Consuming Messages from Kafka Topic
  val df4 = spark
    .read
    .format("kafka")
    .option("kafka.bootstrap.servers", "192.168.1.100:9092")
    .option("subscribe", "text_topic")
    .load()

  df4.printSchema()

  val df5 = df4.selectExpr("CAST(key AS STRING)",
    "CAST(value AS STRING)","topic")
  df5.show(false)



}
