package com.examples

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.types.{StringType, StructField, StructType}
import org.apache.spark.sql.functions.{col,asc,desc}

object ReadORC extends App{

  val spark:SparkSession = SparkSession
    .builder()
    .master("local[1]")
    .appName("Spark-Scala-Examples")
    .getOrCreate()

  val data =Seq(("James ","","Smith","36636","M",3000),
    ("Michael ","Rose","","40288","M",4000),
    ("Robert ","","Williams","42114","M",4000),
    ("Maria ","Anne","Jones","39192","F",4000),
    ("Jen","Mary","Brown","","F",-1))
  val columns=Seq("firstname","middlename","lastname","dob","gender","salary")
  val df=spark.createDataFrame(data).toDF(columns:_*)

  df.write.mode("overwrite")
    .orc("./config/data.orc")

  df.write.mode("overwrite")
    .option("compression","none12")
    .orc("./config/data-nocomp.orc")

  df.write.mode("overwrite")
    .option("compression","zlib")
    .orc("./config/data-zlib.orc")

  val df2=spark.read.orc("./config/data.orc")
  df2.show(false)

  df2.createOrReplaceTempView("ORCTable")
  val orcSQL = spark.sql("select firstname,dob from ORCTable where salary >= 4000 ")
  orcSQL.show(false)

  spark.sql("CREATE TEMPORARY VIEW PERSON USING orc OPTIONS (path \"./config/data.orc\")")
  spark.sql("SELECT * FROM PERSON").show()
}

