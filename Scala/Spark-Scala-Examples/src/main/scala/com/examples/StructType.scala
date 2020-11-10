package com.examples

import org.apache.spark.sql.types.{ArrayType, DataType, IntegerType, MapType, StringType, StructField, StructType}
import org.apache.spark.sql.{Encoders, Row, SparkSession}

import scala.io.Source

object StructTypes extends App{

  val spark:SparkSession = SparkSession
    .builder()
    .master("local[1]")
    .appName("Spark-Scala-Examples")
    .getOrCreate()

  val simpleData = Seq(Row("James ","","Smith","36636","M",3000),
    Row("Michael ","Rose","","40288","M",4000),
    Row("Robert ","","Williams","42114","M",4000),
    Row("Maria ","Anne","Jones","39192","F",4000),
    Row("Jen","Mary","Brown","","F",-1)
  )

  val simpleSchema = StructType(Array(
    StructField("firstname",StringType,true),
    StructField("middlename",StringType,true),
    StructField("lastname",StringType,true),
    StructField("id", StringType, true),
    StructField("gender", StringType, true),
    StructField("salary", IntegerType, true)
  ))

  val df = spark.createDataFrame(spark.sparkContext.parallelize(simpleData),simpleSchema)
  df.printSchema()
  df.show()

  //Defining nested StructType object struct
  val structureData = Seq(
    Row(Row("James ","","Smith"),"36636","M",3100),
    Row(Row("Michael ","Rose",""),"40288","M",4300),
    Row(Row("Robert ","","Williams"),"42114","M",1400),
    Row(Row("Maria ","Anne","Jones"),"39192","F",5500),
    Row(Row("Jen","Mary","Brown"),"","F",-1)
  )

  val structureSchema = new StructType()
    .add("name",new StructType()
      .add("firstname",StringType)
      .add("middlename",StringType)
      .add("lastname",StringType))
    .add("id",StringType)
    .add("gender",StringType)
    .add("salary",IntegerType)

  val df2 = spark.createDataFrame(
    spark.sparkContext.parallelize(structureData),structureSchema)
  df2.printSchema()
  df2.show()

  //Creating StructType object struct from JSON file
  val url = ClassLoader.getSystemResource("./config/schema.json")
  val schemaSource = Source.fromFile(url.getFile).getLines.mkString
  val schemaFromJson = DataType.fromJson(schemaSource).asInstanceOf[StructType]
  val df3 = spark.createDataFrame(
    spark.sparkContext.parallelize(structureData),schemaFromJson)
  df3.printSchema()

  //Using SQL ArrayType and MapType
  val arrayStructureData = Seq(
    Row(Row("James ","","Smith"),List("Cricket","Movies"),Map("hair"->"black","eye"->"brown")),
    Row(Row("Michael ","Rose",""),List("Tennis"),Map("hair"->"brown","eye"->"black")),
    Row(Row("Robert ","","Williams"),List("Cooking","Football"),Map("hair"->"red","eye"->"gray")),
    Row(Row("Maria ","Anne","Jones"),null,Map("hair"->"blond","eye"->"red")),
    Row(Row("Jen","Mary","Brown"),List("Blogging"),Map("white"->"black","eye"->"black"))
  )

  val arrayStructureSchema = new StructType()
    .add("name",new StructType()
      .add("firstname",StringType)
      .add("middlename",StringType)
      .add("lastname",StringType))
    .add("hobbies", ArrayType(StringType))
    .add("properties", MapType(StringType,StringType))

  val df5 = spark.createDataFrame(
    spark.sparkContext.parallelize(arrayStructureData),arrayStructureSchema)
  df5.printSchema()
  df5.show()

  //Convert case class to Spark StructType
  case class Name(first:String,last:String,middle:String)
  case class Employee(fullName:Name,age:Integer,gender:String)

  import org.apache.spark.sql.catalyst.ScalaReflection
  val schema = ScalaReflection.schemaFor[Employee].dataType.asInstanceOf[StructType]

  val encoderSchema = Encoders.product[Employee].schema
  encoderSchema.printTreeString()


  //Creating StructType object struct from DDL String
  val ddlSchemaStr = "`fullName` STRUCT<`first`: STRING, `last`: STRING, `middle`: STRING>,`age` INT,`gender` STRING"
  val ddlSchema = StructType.fromDDL(ddlSchemaStr)
  ddlSchema.printTreeString()

  //Checking if a field exists in a DataFrame
  println(df.schema.fieldNames.contains("firstname"))
  println(df.schema.contains(StructField("firstname",StringType,true)))

}
