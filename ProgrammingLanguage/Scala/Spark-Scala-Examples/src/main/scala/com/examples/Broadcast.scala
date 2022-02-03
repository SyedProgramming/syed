package com.examples

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

object Broadcast extends App{

  val spark:SparkSession = SparkSession
    .builder()
    .master("local[1]")
    .appName("Spark-Scala-Examples")
    .getOrCreate()


  val states = Map(("NY","New York"),("CA","California"),("FL","Florida"))
  val countries = Map(("USA","United States of America"),("IN","India"))


  val broadcastStates = spark.sparkContext.broadcast(states)
  val broadcastCountries = spark.sparkContext.broadcast(countries)

  val data = Seq(("James","Smith","USA","CA"),
    ("Michael","Rose","USA","NY"),
    ("Robert","Williams","USA","CA"),
    ("Maria","Jones","USA","FL")
  )
  val rdd = spark.sparkContext.parallelize(data)


  val rdd2 = rdd.map(f=>{
    val country = f._3
    val state = f._4
    val fullCountry = broadcastCountries.value.get(country).get
    val fullState = broadcastStates.value.get(state).get
    (f._1,f._2,fullCountry,fullState)
  })

  println(rdd2.collect().mkString("\n"))


  val columns = Seq("firstname","lastname","country","state")
  import spark.sqlContext.implicits._
  val df = data.toDF(columns:_*)

  val df2 = df.map(row=>{
    val country = row.getString(2)
    val state = row.getString(3)

    val fullCountry = broadcastCountries.value.get(country).get
    val fullState = broadcastStates.value.get(state).get
    (row.getString(0),row.getString(1),fullCountry,fullState)
  }).toDF(columns:_*)

  df2.show(false)

  }
