package com.examples

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.types.{StringType, StructField, StructType}
import org.apache.spark.sql.functions.{col,asc,desc}

object ForEach extends App{

  val spark:SparkSession = SparkSession
    .builder()
    .master("local[1]")
    .appName("Spark-Scala-Examples")
    .getOrCreate()

  // foreachPartition DataFrame
  val rdd = spark.sparkContext.parallelize(Seq(1,2,3,4,5,6,7,8,9))
  rdd.foreachPartition(partition => {
    //Initialize any database connection
    partition.foreach(fun=>{
      //apply the function
    })
  })

  //rdd accumulator
  val rdd2 = spark.sparkContext.parallelize(Seq(1,2,3,4,5,6,7,8,9))
  val longAcc2 = spark.sparkContext.longAccumulator("SumAccumulator2")
  rdd .foreach(f=> {
    longAcc2.add(f)
  })
  println("Accumulator value:"+longAcc2.value)



}
