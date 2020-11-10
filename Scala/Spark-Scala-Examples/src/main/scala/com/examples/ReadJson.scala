package com.examples

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, current_date, date_format, datediff, months_between, to_date, trunc, _}
import org.apache.spark.sql.functions.{asc, col, desc}
import org.apache.spark.sql.types.{BooleanType, DoubleType, IntegerType, StringType, StructType}

object ReadJson extends App{

  val spark:SparkSession = SparkSession
    .builder()
    .master("local[1]")
    .appName("Spark-Scala-Examples")
    .getOrCreate()

  //read json file into dataframe
  val df = spark.read.json("./config/countries.json")
  df.printSchema()
  df.show(false)

  //read multiline json file
  val multiline_df = spark.read.option("multiline","true")
    .json("./config/countries.json")
  multiline_df.show(false)

  //read multiple files
  val df2 = spark.read.json(
    "./config/countries.json",
    "./config/countries.json")
  df2.show(false)

  //read all files from a folder
  val df3 = spark.read.json("./config/")
  df3.show(false)


/*
  spark.sqlContext.sql("CREATE TEMPORARY VIEW countries USING json OPTIONS" +
    " (path './config/countries.json')")
  spark.sqlContext.sql("select * fro countries").show(false)*/

  df2.write
    .json("./config//countries_output")


}
