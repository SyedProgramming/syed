package com.examples

import java.io.File

import org.apache.avro.Schema
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{SaveMode, SparkSession}
import org.apache.spark.sql.types.{BooleanType, DoubleType, IntegerType, StringType, StructType}


object ReadAvro extends App{

  val spark:SparkSession = SparkSession
    .builder()
    .master("local[1]")
    .appName("Spark-Scala-Examples")
    .getOrCreate()

  val columns = Seq("language","users_count")
  val data = Seq(("Java", "20000"), ("Python", "100000"), ("Scala", "3000"))
  val rdd = spark.sparkContext.parallelize(data)

  import spark.implicits._

  val dfFromRDD1 = rdd.toDF("language","users_count")
  dfFromRDD1.printSchema()

  dfFromRDD1.write.format("avro").save("./config/person.avro")
  val personDF= spark.read.format("avro").load("person.avro")

  val data1 = Seq(("James ","","Smith",2018,1,"M",3000),
    ("Michael ","Rose","",2010,3,"M",4000),
    ("Robert ","","Williams",2010,3,"M",4000),
    ("Maria ","Anne","Jones",2005,5,"F",4000),
    ("Jen","Mary","Brown",2010,7,"",-1)
  )

  val columns1 = Seq("firstname", "middlename", "lastname", "dob_year",
    "dob_month", "gender", "salary")
  import spark.sqlContext.implicits._
  val df = data.toDF(columns:_*)

  df.write.partitionBy("dob_year","dob_month")
    .format("avro").save("person_partition.avro")

  spark.read
    .format("avro")
    .load("person_partition.avro")
    //.where(col("dob_year") === 2010)
    .show()

  val schemaAvro = new Schema.Parser()
    .parse(new File("./config/person.avsc"))

  val df1 = spark.read
    .format("avro")
    .option("avroSchema", schemaAvro.toString)
    .load("person.avro")

 /* spark.sqlContext.sql("CREATE TEMPORARY VIEW PERSON USING avro
    OPTIONS (path \"person.avro\")")
    spark.sqlContext.sql("SELECT * FROM PERSON").show()*/


/*
  val data = Seq(("James ","","Smith",2018,1,"M",3000),
    ("Michael ","Rose","",2010,3,"M",4000),
    ("Robert ","","Williams",2010,3,"M",4000),
    ("Maria ","Anne","Jones",2005,5,"F",4000),
    ("Jen","Mary","Brown",2010,7,"",-1)
  )

  val columns = Seq("firstname","middlename","lastname","dob_year",
    "dob_month","gender","salary")
  import spark.sqlContext.implicits._
  val df = data.toDF(columns:_*)

  /**
   * Write Avro File
   */
  df.write
    .mode(SaveMode.Overwrite)
    .avro("C:/tmp/spark_out/avro/person.avro")

  //Alternatively you can specify the format to use instead:
  df.write.format("com.databricks.spark.avro")
    .mode(SaveMode.Overwrite)
    .save("C:/tmp/spark_out/avro/person2.avro")

  /**
   * Read Avro File
   */
  val readDF = spark.read.avro("C:/tmp/spark_out/avro/person.avro")
  //Alternatively you can specify the format to use instead:

  val readDF2 = spark.read
    .format("com.databricks.spark.avro")
    .load("C:/tmp/spark_out/avro/person2.avro")

  /**
   * Write Partition
   */
  df.write.partitionBy("dob_year","dob_month")
    .mode(SaveMode.Overwrite)
    .avro("C:/tmp/spark_out/avro/person_partition.avro")

  /**
   * Reading Partition Data
   */
  spark.read
    .avro("C:/tmp/spark_out/avro/person_partition.avro")
    .where(col("dob_year") === 2010)
    .show()

  /**
   * Namespace
   */
  val name = "AvroTest"
  val namespace = "com.sparkbyexamples.spark"
  val parameters = Map("recordName" -> name, "recordNamespace" -> namespace)
  df.write.options(parameters)
    .mode(SaveMode.Overwrite)
    .avro("C:/tmp/spark_out/avro/person_namespace.avro")

  /**
   * Explicit schema
   */
  val schemaAvro = new Schema.Parser()
    .parse(new File("src/main/resources/person.avsc"))

  spark
    .read
    .format("com.databricks.spark.avro")
    .option("avroSchema", schemaAvro.toString)
    .load("C:/tmp/spark_out/avro/person.avro")
    .show()

  /**
   * Spark SQL
   */
  spark.sqlContext.sql("CREATE TEMPORARY VIEW PERSON USING com.databricks.spark.avro OPTIONS (path \"person.avro\")")
  df2 = spark.sqlContext.sql("SELECT * FROM PERSON").show()
}
 */



}
