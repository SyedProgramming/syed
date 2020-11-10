package com.examples

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{SaveMode, SparkSession}
import org.apache.spark.sql.types.{BooleanType, DoubleType, IntegerType, StringType, StructType}


object ReadXML extends App{

  val spark:SparkSession = SparkSession
    .builder()
    .master("local[1]")
    .appName("Spark-Scala-Examples")
    .getOrCreate()

 /* val df = spark.read
    .format("com.databricks.spark.xml")
    .option("rowTag", "person")
    .xml("./config/persons.xml")


  df.write
    .format("com.databricks.spark.xml")
    .option("rootTag", "persons")
    .option("rowTag", "person")
    .save("./config/persons_new.xml")

  df.write.format("avro")
    .mode(SaveMode.Overwrite)
    .save("./config/persons.avro")

  df2.write.partitionBy("_id")
    .format("avro").save("persons_partition.avro")

  df2.write
    .parquet("./configpersons.parquet")

  df2.write
    .partitionBy("_id")
    .parquet("./config/persons_partition.parquet")
*/
}
