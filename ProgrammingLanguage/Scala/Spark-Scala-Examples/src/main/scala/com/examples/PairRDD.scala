package com.examples

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

object PairRDD extends App{

  val spark:SparkSession = SparkSession
    .builder()
    .master("local[1]")
    .appName("Spark-Scala-Examples")
    .getOrCreate()

  val rdd = spark.sparkContext.parallelize(
    List("Germany India USA","USA India Russia","India Brazil Canada China")
  )

  val rddFlatMap = rdd.flatMap(_.split(" "))
  rddFlatMap.foreach(f => println(f))

  val pairRDD = rddFlatMap.map(f => (f,1))
  pairRDD.foreach(f => println(f))

  //distinct
  pairRDD.distinct().foreach(println)

  //sortByKey – Transformation returns an RDD after sorting by key
  println("Sort by Key ==>")
  val sortRDD = pairRDD.sortByKey()
  sortRDD.foreach(println)

  //reduceByKey – Transformation returns an RDD after adding value for each key.
  println("Reduce by Key ==>")
  val wordCount = pairRDD.reduceByKey((a,b)=>a+b)
  wordCount.foreach(println)

  //aggregateByKey – Transformation same as reduceByKey
  def param1= (accu:Int,v:Int) => accu + v
  def param2= (accu1:Int,accu2:Int) => accu1 + accu2
  println("Aggregate by Key ==> wordcount")
  val wordCount2 = pairRDD.aggregateByKey(0)(param1,param2)
  wordCount2.foreach(println)

  //keys – Return RDD[K] with all keys in an dataset
  println("Keys ==>")
  wordCount2.keys.foreach(println)

  //values – return RDD[V] with all values in an dataset
  println("Values ==>")
  wordCount2.values.foreach(println)

  //count – This is an action function and returns a count of a dataset
  println("Count : "+wordCount2.count())

  //collectAsMap – This is an action function and returns Map to the master for retrieving all date from a dataset.
  println("collectAsMap ==>")
  pairRDD.collectAsMap().foreach(println)





















}
