package com.examples

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.types.{StringType, StructField, StructType}
import org.apache.spark.sql.functions.{when, _}

object When extends App{

  val spark:SparkSession = SparkSession
    .builder()
    .master("local[1]")
    .appName("Spark-Scala-Examples")
    .getOrCreate()


  import spark.sqlContext.implicits._
  val data = List(("James","","Smith","36636","M",60000),
    ("Michael","Rose","","40288","M",70000),
    ("Robert","","Williams","42114","",400000),
    ("Maria","Anne","Jones","39192","F",500000),
    ("Jen","Mary","Brown","","F",0))

  val cols = Seq("first_name","middle_name","last_name","dob","gender","salary")
  val df = spark.createDataFrame(data).toDF(cols:_*)

  //Using “when otherwise” on Spark DataFrame
  val df2 = df.withColumn("new_gender", when(col("gender") === "M","Male")
    .when(col("gender") === "F","Female")
    .otherwise("Unknown"))

  //Using “case when” on Spark DataFrame.
  val df3 = df.withColumn("new_gender",
    expr("case when gender = 'M' then 'Male' " +
      "when gender = 'F' then 'Female' " +
      "else 'Unknown' end"))

  //Using within SQL select.
  val df4 = df.select(col("*"),
    expr("case when gender = 'M' then 'Male' " +
      "when gender = 'F' then 'Female' " +
      "else 'Unknown' end").alias("new_gender"))

  //Using && and || operator
  val dataDF = Seq(
    (66, "a", "4"), (67, "a", "0"), (70, "b", "4"), (71, "d", "4"
    )).toDF("id", "code", "amt")
  dataDF.withColumn("new_column",
    when(col("code") === "a" || col("code") === "d", "A")
      .when(col("code") === "b" && col("amt") === "4", "B")
      .otherwise("A1"))
    .show()




}
