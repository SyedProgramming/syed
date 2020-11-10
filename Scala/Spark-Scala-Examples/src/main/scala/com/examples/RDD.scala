package com.examples

import org.apache.spark.api.java.JavaSparkContext
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{SparkSession, Row}
import org.apache.spark.sql.types.{StringType, StructField, StructType}

object RDD extends App {

  val spark:SparkSession = SparkSession
    .builder()
    .master("local[1]")
    .appName("Spark-Scala-Examples")
    .getOrCreate()

  //Create RDD from Seq or List (using Parallelize)
  val rdd = spark.sparkContext.parallelize(Seq(
                                            ("Scala",20000),
                                            ("Java",40000),
                                            ("Python",60000)
                                              ))

  rdd.foreach(f => {
    println(f)
  })



  //Create an RDD from a text file
  val rddText = spark.sparkContext.textFile("./config/shakespeare.txt")

  //wholeTextFiles()
  val rddWhole = spark.sparkContext.wholeTextFiles("./config/shakespeare.txt")
  rddWhole.foreach(
          record=>println("FileName : "+record._1+", FileContents :"+record._2))

  //Creating from another RDD
  val rddDup = rdd.map(row => {
    (row._1, row._2)
  })

  rddDup.foreach(f => {
    println(f)
  })

  //From existing DataFrames and DataSet
  val rddFromDF = spark.range(20).toDF().rdd
  rddFromDF.foreach(f => {
    println(f)
  })

  //sc.emptyRDD
  val rddEmpty = spark.sparkContext.emptyRDD // creates EmptyRDD[0]
  val rddStringEmpty = spark.sparkContext.emptyRDD[String] // creates EmptyRDD[1]
  val emptyRDD = spark.sparkContext.parallelize(Seq(""))

  //Create an Empty RDD with Partition
  val rddEmptyPartition = spark.sparkContext.parallelize(Seq.empty[String])
  println("Num of Partitions: "+rddEmptyPartition.getNumPartitions)

  //Creating an Empty pair RDD
  type pairRDD = (String, Int)
  val resultRDD = spark.sparkContext.emptyRDD[pairRDD]

  //Convert RDD to DataFrame – Using toDF()
  import spark.implicits._
  val dfFromRDD1 = rdd.toDF()
  dfFromRDD1.printSchema()

  val dfFromRDD2 = rdd.toDF("language","users_count")
  dfFromRDD2.printSchema()

  //Convert RDD to DataFrame – Using createDataFrame()
  val columns = Seq("language","users_count")
  val dfFromRDD3 = spark.createDataFrame(rdd).toDF(columns:_*)

  //Using RDD Row type RDD[Row] to DataFrame
  //From RDD (USING createDataFrame and Adding schema using StructType)
  val schema = StructType(columns
                          .map(fieldName => StructField(fieldName, StringType, nullable = true)))
  //convert RDD[T] to RDD[Row]
  val rowRDD = rdd.map(attributes => Row(attributes._1, attributes._2.toString))
  val dfFromRDD4 = spark.createDataFrame(rowRDD, schema)
  rowRDD.foreach(println)
  dfFromRDD4.printSchema()
  dfFromRDD4.show()

  //Convert Spark RDD to Dataset
  val ds = spark.createDataset(rdd)
}



