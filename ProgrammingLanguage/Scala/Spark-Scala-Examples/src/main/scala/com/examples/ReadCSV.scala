package com.examples
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{SaveMode, SparkSession}
import org.apache.spark.sql.types.{BooleanType, DoubleType, IntegerType, StringType, StructType}

object ReadCSV extends App {

  val spark:SparkSession = SparkSession
    .builder()
    .master("local[1]")
    .appName("Spark-Scala-Examples")
    .getOrCreate()


  //Load CSV file into RDD
  val rddFromFile = spark.sparkContext.textFile("./config/weather.csv")

  val rdd = rddFromFile.map(
    f =>
      {
        println(f(0) + " " + f(1))
      }
  )

  rdd.foreach(
    f =>
      {
        println(f)
      }
  )

  val df1 = spark.read.csv("./config/zipcodes.csv")
  df1.printSchema()

  val d2 = spark.read.option("header",true)
    .csv("./config/zipcodes.csv")

  val df3 = spark.read.options(Map("delimiter"->","))
    .csv("./config/zipcodes.csv")

  val df4 = spark.read.options(Map("inferSchema"->"true","delimiter"->",","header"->"true"))
    .csv("./config/zipcodes.csv")

  val schema = new StructType()
    .add("RecordNumber",IntegerType,true)
    .add("Zipcode",IntegerType,true)
    .add("ZipCodeType",StringType,true)
    .add("City",StringType,true)
    .add("State",StringType,true)
    .add("LocationType",StringType,true)
    .add("Lat",DoubleType,true)
    .add("Long",DoubleType,true)
    .add("Xaxis",IntegerType,true)
    .add("Yaxis",DoubleType,true)
    .add("Zaxis",DoubleType,true)
    .add("WorldRegion",StringType,true)
    .add("Country",StringType,true)
    .add("LocationText",StringType,true)
    .add("Location",StringType,true)
    .add("Decommisioned",BooleanType,true)
    .add("TaxReturnsFiled",StringType,true)
    .add("EstimatedPopulation",IntegerType,true)
    .add("TotalWages",IntegerType,true)
    .add("Notes",StringType,true)
  val df_with_schema = spark.read.format("csv")
    .option("header", "true")
    .schema(schema)
    .load("./config/zipcodes.csv")
  df_with_schema.printSchema()
  df_with_schema.show(false)


  df1.write.option("header","true")
    .csv("./config/zipcodes_output")

  df1.write.option("header",true)
    .csv("./config/zipcodes_output1")

  df1.write.mode(SaveMode.Overwrite).csv("./config/zipcodes_output")


}
