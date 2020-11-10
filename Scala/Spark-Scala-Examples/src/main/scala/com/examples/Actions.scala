package com.examples

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

object Actions extends App{

  val spark:SparkSession = SparkSession
    .builder()
    .master("local[1]")
    .appName("Spark-Scala-Examples")
    .getOrCreate()

  val inputRDD = spark.sparkContext.parallelize(List(("Z", 1),("A", 20),("B", 30),("C", 40),("B", 30),("B", 60)))
  val listRdd = spark.sparkContext.parallelize(List(1,2,3,4,5,3,2))


  //aggregate
  def param0 = (accu:Int, v:Int) => accu + v
  def param1 = (accu1:Int, accu2:Int) => accu1+accu2

  println("Aggregate : " + listRdd.aggregate(0)(param0,param1))

  def param3 = (accu:Int, v:(String, Int)) => accu + v._2
  def param4 = (accu1:Int, accu2:Int) => accu1+accu2
  println("Aggregate : " + inputRDD.aggregate(0)(param3,param4))



  //treeAggregate
  def param8 = (accu:Int, v:Int) => accu+v
  def param9 = (accu1:Int,accu2:Int) => accu1 + accu2
  println("TreeAggregate : "+listRdd.treeAggregate(0)(param8,param9))

  //fold()
  println("Fold : " + listRdd.fold(0) {
    (accu,v) => {
      val sum = accu+v
      sum
    }
  })

  println("Fold : " + inputRDD.fold(("Total", 0)){
    (accu:(String, Int), v:(String, Int)) => {
      val sum = accu._2 + v._2
      ("Total", sum)
    }
  })


  //reduce
  println("reduce : "+listRdd.reduce(_ + _))
  println("reduce alternate : "+listRdd.reduce((x, y) => x + y))
  println("reduce : "+inputRDD.reduce((x, y) => ("Total",x._2 + y._2)))

  //treeReduce
  println("treeReduce : "+listRdd.treeReduce(_ + _))

  //collect
  val data:Array[Int] = listRdd.collect()
  data.foreach(println)

  //count, countApprox, countApproxDistinct
  println("Count : "+listRdd.count)
  println("countApprox : "+listRdd.countApprox(1200))
  println("countApproxDistinct : "+listRdd.countApproxDistinct())
  println("countApproxDistinct : "+inputRDD.countApproxDistinct())

  //countByValue, countByValueApprox
  println("countByValue :  "+listRdd.countByValue())
  println("countByValueApprox : " + listRdd.countByValueApprox(1200))

  //first
  println("first :  "+listRdd.first())
  println("first :  "+inputRDD.first())

  //top
  println("top : "+listRdd.top(2).mkString(","))
  println("top : "+inputRDD.top(2).mkString(","))

  //min
  println("min :  "+listRdd.min())
  println("min :  "+inputRDD.min())

  //max
  println("max :  "+listRdd.max())
  println("max :  "+inputRDD.max())


  //take, takeOrdered, takeSample
  println("take : "+listRdd.take(2).mkString(","))
  println("takeOrdered : "+ listRdd.takeOrdered(2).mkString(","))
  println("take : "+listRdd.takeSample(true, 10).foreach(println))

  //toLocalIterator
  println("toLocalIterator : " + listRdd.toLocalIterator.foreach(println))

}
