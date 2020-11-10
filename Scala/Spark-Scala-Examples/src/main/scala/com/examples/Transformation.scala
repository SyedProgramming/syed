package com.examples

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

object Transformation extends App{

  val spark:SparkSession = SparkSession
    .builder()
    .master("local[1]")
    .appName("Spark-Scala-Examples")
    .getOrCreate()


  val sc = spark.sparkContext

  //textFile()
  val rdd:RDD[String] = sc.textFile("./config/shakespeare.txt")

  //flatMap()
  val rddFlatMap = rdd.flatMap(f => {
    f.split(" ")
  })

  //map()
  val rddMap:RDD[(String, Int)] = rddFlatMap.map(m => {
    (m,1)
  })

  //filter()
  val rddFilter = rddMap.filter(a => {
    a._1.startsWith("a")
  })

  //reduceByKey()
  val rddReduceByKey = rddFilter.reduceByKey(_ + _)

  //sortByKey()
  val rddSortByKey = rddReduceByKey.map(a => {
    (a._2, a._1)
  }).sortByKey()

  rddSortByKey.foreach(println)

  rddSortByKey.saveAsTextFile("./config/shakespeare_count.txt")

}
