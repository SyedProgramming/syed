package com.examples

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{SaveMode, SparkSession}

object RepartitionVsCoalesce extends App{

  val spark:SparkSession = SparkSession
    .builder()
    .master("local[1]")
    .appName("Spark-Scala-Examples")
    .getOrCreate()


  val df = spark.range(0,20)
  println(df.rdd.partitions.length)

  //RDD Partition and repartition
  val rdd = spark.sparkContext.parallelize(Range(0,20))
  println("From local[5] "+rdd.partitions.size)

  val rdd1 = spark.sparkContext.parallelize(Range(0,25), 6)
  println("parallelize : "+rdd1.partitions.size)

  val rddFromFile = spark.sparkContext.textFile("./config/shakespeare.txt",10)
  println("TextFile : "+rddFromFile.partitions.size)

  //Writes 6 part files, one for each partition
  //rdd1.saveAsTextFile("./config/partition")

  //RDD repartition()
  val rdd2 = rdd1.repartition(4)
  println("Repartition size : "+rdd2.partitions.size)
  //rdd2.saveAsTextFile("./config/re-partition")

  //RDD coalesce()
  val rdd3 = rdd1.coalesce(4)
  println("Repartition size : "+rdd3.partitions.size)
  //rdd3.saveAsTextFile("./config/coalesce")

  //DataFrame Partition and repartition
  val df1 = spark.range(0,20)
  println(df1.rdd.partitions.length)
  df1.write.mode(SaveMode.Overwrite)csv("./config/partition.csv")

  //DataFrame repartition()
  val df2 = df.repartition(6)
  println(df2.rdd.partitions.length)

  //DataFrame coalesce()
  val df3 = df.coalesce(2)
  println(df3.rdd.partitions.length)

  //Default Shuffle Partition
  val df4 = df.groupBy("id").count()
  println(df4.rdd.getNumPartitions)





























}
