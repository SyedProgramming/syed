package com.examples

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession


object RDDParallelize {

  def main(args : Array[String]): Unit = {

    val spark:SparkSession = SparkSession
                              .builder()
                              .master("local[1]")
                              .appName("Spark-Scala-Examples")
                              .getOrCreate()

    val rdd:RDD[Int] = spark.sparkContext.parallelize(List(1,2,3,4,5))
    val rddCollect:Array[Int] = rdd.collect()

    println("Number of Partitions : " + rdd.getNumPartitions)
    println("Action: First Element : " + rdd.first())
    println("Action: RDD converted to Array[Int] : ")
    rddCollect.foreach(println)

    //create empty RDD by using sparkContext.parallelize
    spark.sparkContext.parallelize(Seq.empty[String])

  }




}
