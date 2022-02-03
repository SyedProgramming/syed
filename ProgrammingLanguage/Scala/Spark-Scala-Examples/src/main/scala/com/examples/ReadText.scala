package com.examples
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

object ReadText {

  def main(args : Array[String]): Unit = {

    val spark:SparkSession = SparkSession
      .builder()
      .master("local[1]")
      .appName("Spark-Scala-Examples")
      .getOrCreate()

    val rdd = spark.sparkContext.textFile("./config/shakespeare.txt")
    rdd.foreach(
      f => {
        println(f)
      }
    )


    //If you are running on a cluster you should first collect the data in order to print on a console as shown below.
    rdd.collect.foreach(
      f => {
        println(f)
      }
    )

    //wholeTextFiles()
    val rddWhole = spark.sparkContext.wholeTextFiles("./config/shakespeare.txt")
    rddWhole.foreach(
      f => {
        println(f)
      }
    )

    //Spark Read multiple text files into a single RDD
    val rddMultiple = spark.sparkContext.textFile("./config/shakespeare.txt,./config/shakespeare.txt,./config/shakespeare.txt")
    rddMultiple.foreach(
      f => {
        println(f)
      }
    )

    //Read all text files matching a pattern to single RDD
    val rddPattern = spark.sparkContext.textFile("./config/shake*.txt")
    rddPattern.foreach(
      f => {
        println(f)
      }
    )

    //Read files from multiple directories into single RDD
    val rddMulDirPattern = spark.sparkContext.textFile("./config/,./config/,./config/shake*.txt")
    rddMulDirPattern.foreach(
      f => {
        println(f)
      }
    )

  }

}
