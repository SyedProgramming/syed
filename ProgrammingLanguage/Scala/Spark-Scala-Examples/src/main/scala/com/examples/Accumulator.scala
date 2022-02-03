package com.examples

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

object Accumulator extends App{

  val spark:SparkSession = SparkSession
    .builder()
    .master("local[1]")
    .appName("Spark-Scala-Examples")
    .getOrCreate()

  //LongAccumulator
  val longAcc = spark.sparkContext.longAccumulator("SumAccumulator")

  val rdd = spark.sparkContext.parallelize(Array(1, 2, 3))
  rdd.foreach(x => longAcc.add(x))

  println(longAcc.value)

  /* Methods:
  isZero
  copy
  reset
  add
  count
  sum
  avg
  merge
  value
  */

}
