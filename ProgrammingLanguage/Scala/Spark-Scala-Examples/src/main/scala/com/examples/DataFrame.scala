package com.examples

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.types.{StringType, StructField, StructType}
import org.apache.spark.sql.functions.{col,asc,desc}

object DataFrame extends App{

  val spark:SparkSession = SparkSession
    .builder()
    .master("local[1]")
    .appName("Spark-Scala-Examples")
    .getOrCreate()

  import spark.implicits._

  val columns = Seq("language","users_count")
  val data = Seq(("Java", "20000"), ("Python", "100000"), ("Scala", "3000"))
  val rdd = spark.sparkContext.parallelize(data)

  val dfFromRDD1 = rdd.toDF("language","users_count")
  dfFromRDD1.printSchema()

  //Using Spark createDataFrame() from SparkSession
  val dfFromRDD2 = spark.createDataFrame(rdd).toDF(columns:_*)

  //Using createDataFrame() with the Row type
  val schema = StructType( Array(StructField("language", StringType,true),
                                 StructField("users_count", StringType,true)))
  val rowRDD = rdd.map(attributes => Row(attributes._1, attributes._2))
  val dfFromRDD3 = spark.createDataFrame(rowRDD,schema)

  //Create Spark DataFrame from List and Seq Collection
  val dfFromData1 = data.toDF()

  //Using createDataFrame() from SparkSession
  var dfFromData2 = spark.createDataFrame(data).toDF(columns:_*)

  //Creating Spark DataFrame from CSV
  val df2 = spark.read.csv("./config/weather.csv")

  //Creating from text (TXT) file
  val df3 = spark.read.text("./config/shakespeare.txt")

  //Creating from JSON file
  val df4 = spark.read.json("./config/countries.json")

  //Creating from an XML file
  /*val df5 = spark.read
    .format("com.databricks.spark.xml")
    .option("rowTag", "person")
    .xml("src/main/resources/persons.xml")*/

  //Creating from Hive
  /*val hiveContext = new org.apache.spark.sql.hive.HiveContext(spark.sparkContext)
  val hiveDF = hiveContext.sql("select * from emp")*/

  //Creating from the Database table (RDBMS)
  val df_mysql = spark.read.format("jdbc")
    .option("url", "jdbc:mysql://localhost:port/db")
    .option("driver", "com.mysql.jdbc.Driver")
    .option("dbtable", "tablename")
    .option("user", "user")
    .option("password", "password")
    .load()

  //From DB2 table
  val df_db2 = spark.read.format("jdbc")
    .option("url", "jdbc:db2://localhost:50000/dbname")
    .option("driver", "com.ibm.db2.jcc.DB2Driver")
    .option("dbtable", "tablename")
    .option("user", "user")
    .option("password", "password")
    .load()

  //Create DataFrame from HBase table
  /*val hbaseDF = spark.read.options(Map(HBaseTableCatalog.tableCatalog -> catalog))
    .format("org.apache.spark.sql.execution.datasources.hbase")
    .load()*/


}


