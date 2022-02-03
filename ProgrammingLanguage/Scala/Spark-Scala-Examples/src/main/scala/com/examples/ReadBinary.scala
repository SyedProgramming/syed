package com.examples

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.types.{StringType, StructField, StructType, ArrayType}
import org.apache.spark.sql.functions.array_contains

object ReadBinary extends App{

  val spark:SparkSession = SparkSession
    .builder()
    .master("local[1]")
    .appName("Spark-Scala-Examples")
    .getOrCreate()

  val df = spark.read.format("binaryFile").load("./config/spark.png")
  df.printSchema()
  df.show()

  //Read multiple binary files
  val df3 = spark.read.format("binaryFile").load("./config/*.png")
  df3.printSchema()
  df3.show(false)

  //Read all binary files in a folder
  val df2 = spark.read.format("binaryFile").load("./config/binary/")
  df2.printSchema()
  df2.show(false)

  //Reading Binary file Options
  val df4 = spark.read.format("binaryFile")
    .option("pathGlobFilter", "*.png")
    .load("./config/binary/")

  val df5 = spark.read.format("binaryFile")
    .option("pathGlobFilter", "*.png")
    .option("recursiveFileLookup", "true")
    .load("./config/binary/")


}
