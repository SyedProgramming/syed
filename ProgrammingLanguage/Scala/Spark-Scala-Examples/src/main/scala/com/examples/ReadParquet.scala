package com.examples

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{SaveMode, SparkSession}
import org.apache.spark.sql.types.{BooleanType, DoubleType, IntegerType, StringType, StructType}

object ReadParquet extends App{

  val spark:SparkSession = SparkSession
    .builder()
    .master("local[1]")
    .appName("Spark-Scala-Examples")
    .getOrCreate()

  val data = Seq(("James ","","Smith","36636","M",3000),
    ("Michael ","Rose","","40288","M",4000),
    ("Robert ","","Williams","42114","M",4000),
    ("Maria ","Anne","Jones","39192","F",4000),
    ("Jen","Mary","Brown","","F",-1))

  val columns = Seq("firstname","middlename","lastname","dob","gender","salary")
  import spark.sqlContext.implicits._
  val df = data.toDF(columns:_*)
  df.show()
  df.printSchema()
  df.write
    .parquet("./config/people.parquet")
  val parqDF = spark.read.parquet("./config/people.parquet")
  parqDF.createOrReplaceTempView("ParquetTable")
  spark.sql("select * from ParquetTable where salary >= 4000").explain()
  val parkSQL = spark.sql("select * from ParquetTable where salary >= 4000 ")
  parkSQL.show()
  parkSQL.printSchema()
  df.write
    .partitionBy("gender","salary")
    .parquet("./config/people2.parquet")
  val parqDF2 = spark.read.parquet("./config/people2.parquet")
  parqDF2.createOrReplaceTempView("ParquetTable2")
  val df3 = spark.sql("select * from ParquetTable2  where gender='M' and salary >= 4000")
  df3.explain()
  df3.printSchema()
  df3.show()
  val parqDF3 = spark.read
    .parquet("./config/people2.parquet/gender=M")
  parqDF3.show()

}
