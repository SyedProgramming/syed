package com.examples

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{current_date, col,
  date_format, to_date, datediff, months_between, trunc, _}
import org.apache.spark.sql.types.{StringType, StructField, StructType}
import org.apache.spark.sql.functions.{col,asc,desc}

object DateExample extends App{

  val spark:SparkSession = SparkSession
    .builder()
    .master("local[1]")
    .appName("Spark-Scala-Examples")
    .getOrCreate()

  import spark.implicits._
  val data = Seq("2019-01-23")
  val rdd = spark.sparkContext.parallelize(data)
  val dfFromRDD1 = rdd.toDF("Input")

  //current_date
  dfFromRDD1.select(
    current_date().as("Current_Date"),
    col("Input"),
    date_format(col("Input"),"MM-dd-yyyy").as("format")
  ).show()

  //to_date
  Seq(("04/13/2019"))
    .toDF("Input")
    .select( col("Input"),
      to_date(col("Input"), "MM/dd/yyyy").as("to_date")
    ).show()

  //datediff
  Seq(("2019-01-23"),("2019-06-24"),("2019-09-20"))
    .toDF("input")
    .select( col("input"), current_date(),
      datediff(current_date(),col("input")).as("diff")
    ).show()

  //months_between
  Seq(("2019-01-23"),("2019-06-24"),("2019-09-20"))
    .toDF("date")
    .select( col("date"), current_date(),
      datediff(current_date(),col("date")).as("datediff"),
      months_between(current_date(),col("date")).as("months_between")
    ).show()

  //trunc
  Seq(("2019-01-23"),("2019-06-24"),("2019-09-20"))
    .toDF("input")
    .select( col("input"),
      trunc(col("input"),"Month").as("Month_Trunc"),
      trunc(col("input"),"Year").as("Month_Year"),
      trunc(col("input"),"Month").as("Month_Trunc")
    ).show()

  //add_months , date_add, date_sub
  Seq(("2019-01-23"),("2019-06-24"),("2019-09-20")).toDF("input")
    .select( col("input"),
      add_months(col("input"),3).as("add_months"),
      add_months(col("input"),-3).as("sub_months"),
      date_add(col("input"),4).as("date_add"),
      date_sub(col("input"),4).as("date_sub")
    ).show()

  //year, month, month, dayofweek, dayofmonth, dayofyear, next_day, weekofyear
  Seq(("2019-01-23"),("2019-06-24"),("2019-09-20"))
    .toDF("input")
    .select( col("input"), year(col("input")).as("year"),
      month(col("input")).as("month"),
      dayofweek(col("input")).as("dayofweek"),
      dayofmonth(col("input")).as("dayofmonth"),
      dayofyear(col("input")).as("dayofyear"),
      next_day(col("input"),"Sunday").as("next_day"),
      weekofyear(col("input")).as("weekofyear")
    ).show()



}
