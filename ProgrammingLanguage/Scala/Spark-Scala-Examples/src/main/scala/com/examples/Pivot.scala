package com.examples

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.types.{StringType, StructField, StructType}
import org.apache.spark.sql.functions.expr


object Pivot extends App{

  val spark:SparkSession = SparkSession
    .builder()
    .master("local[1]")
    .appName("Spark-Scala-Examples")
    .getOrCreate()

  val data = Seq(("Banana",1000,"USA"), ("Carrots",1500,"USA"), ("Beans",1600,"USA"),
    ("Orange",2000,"USA"),("Orange",2000,"USA"),("Banana",400,"China"),
    ("Carrots",1200,"China"),("Beans",1500,"China"),("Orange",4000,"China"),
    ("Banana",2000,"Canada"),("Carrots",2000,"Canada"),("Beans",2000,"Mexico"))

  import spark.sqlContext.implicits._
  val df = data.toDF("Product","Amount","Country")
  df.show()


  //Pivot Spark DataFrame
  val pivotDF = df.groupBy("Product").pivot("Country").sum("Amount")
  pivotDF.show()

  //Pivot Performance improvement in Spark 2.0
  val countries = Seq("USA","China","Canada","Mexico")
  val pivotDF1 = df.groupBy("Product").pivot("Country", countries).sum("Amount")
  pivotDF.show()

  //Unpivot Spark DataFrame
  val unPivotDF = pivotDF.select($"Product",
    expr("stack(3, 'Canada', Canada, 'China', China, 'Mexico', Mexico) as (Country,Total)"))
    .where("Total is not null")
  unPivotDF.show()















}
