package com.examples

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.types.{StringType, StructField, StructType, ArrayType}
import org.apache.spark.sql.functions.{array_contains,_}

object StreamingExample extends App{

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


  df.writeStream
    .format("console")
    .outputMode("append")
    .start()
    .awaitTermination()

  val wordCountDF = df.select(explode(split(col("value")," ")).alias("word"))
    .groupBy("word").count()

  wordCountDF.writeStream
    .format("console")
    .outputMode("complete")
    .start()
    .awaitTermination()


  wordCountDF.writeStream
    .format("console")
    .outputMode("update")
    .start()
    .awaitTermination()

  //Spark Streaming files from a folder
  val df1 = spark.readStream
    .schema("provide schema of json file")//Below codes provides example
    .json("./config/")

  //Writing Spark Streaming to Console
  df1.writeStream
    .format("console")
    .outputMode("append")
    .start()             // Start the computation
    .awaitTermination()  // Wait for the computation to terminate


  val groupDF = df1.select("Zipcode")
    .groupBy("Zipcode").count()

  groupDF.writeStream
    .format("console")
    .outputMode("complete")
    .start()
    .awaitTermination()


  //Spark Streaming data from TCP Socket
  val df4 = spark.readStream
    .format("socket")
    .option("host","localhost")
    .option("port","9090")
    .load()

  val wordsDF = df4.select(explode(split(df("value")," ")).alias("word"))
  val count = wordsDF.groupBy("word").count()

  val query = count.writeStream
    .format("console")
    .outputMode("complete")
    .start()
    .awaitTermination()






















}
