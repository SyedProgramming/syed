package com.examples

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.types.{StringType, StructField, StructType, IntegerType}
import org.apache.spark.sql.functions.{col, lit}

object WithColumn extends App{

  val spark:SparkSession = SparkSession
    .builder()
    .master("local[1]")
    .appName("Spark-Scala-Examples")
    .getOrCreate()


  val data = Seq(Row(Row("James ","","Smith"),"36636","M","3000"),
                 Row(Row("Michael ","Rose",""),"40288","M","4000"),
                 Row(Row("Robert ","","Williams"),"42114","M","4000"),
                 Row(Row("Maria ","Anne","Jones"),"39192","F","4000"),
                 Row(Row("Jen","Mary","Brown"),"","F","-1")
  )

  val schema = new StructType()
      .add("name",new StructType()
      .add("firstname",StringType)
      .add("middlename",StringType)
      .add("lastname",StringType))
      .add("dob",StringType)
      .add("gender",StringType)
      .add("salary",StringType)

  val df = spark.createDataFrame(spark.sparkContext.parallelize(data),schema)

  //Spark withColumn – To change column DataType
  df.withColumn("salary",col("salary").cast("Integer"))

  //Change the value of an existing column
  df.withColumn("salary",col("salary")*100)

  //Derive new column from an existing column
  df.withColumn("CopiedColumn",col("salary")* -1)

  //Add a new column
  df.withColumn("Country", lit("USA"))

  //chaining to operate on multiple columns
  df.withColumn("Country", lit("USA"))
    .withColumn("anotherColumn",lit("anotherValue"))

  //Rename DataFrame column name
  df.withColumnRenamed("gender","sex")

  //Drop a column from Spark DataFrame
  df.drop("CopiedColumn")

  //Split column to multiple columns
  import spark.implicits._
  val columns = Seq("name","address")
  val data1 = Seq(("Robert, Smith", "1 Main st, Newark, NJ, 92537"),
                 ("Maria, Garcia","3456 Walnut st, Newark, NJ, 94732"))


  //Splitting one column to multiple columns
  import spark.implicits._

  var dfFromData = spark.createDataFrame(data1).toDF(columns:_*)
  dfFromData.printSchema()

  val newDF = dfFromData.map(f=>{
    val nameSplit = f.getAs[String](0).split(",")
    val addSplit = f.getAs[String](1).split(",")
    (nameSplit(0),nameSplit(1),addSplit(0),addSplit(1),addSplit(2),addSplit(3))
  })
  val finalDF = newDF.toDF("First Name","Last Name",
    "Address Line1","City","State","zipCode")
  finalDF.printSchema()
  finalDF.show(false)


  //Spark withColumnRenamed to Rename Column
  val data2 = Seq(Row(Row("James ","","Smith"),"36636","M",3000),
    Row(Row("Michael ","Rose",""),"40288","M",4000),
    Row(Row("Robert ","","Williams"),"42114","M",4000),
    Row(Row("Maria ","Anne","Jones"),"39192","F",4000),
    Row(Row("Jen","Mary","Brown"),"","F",-1)
  )

  val schema2 = new StructType()
       .add("name",new StructType()
       .add("firstname",StringType)
       .add("middlename",StringType)
       .add("lastname",StringType))
       .add("dob",StringType)
       .add("gender",StringType)
       .add("salary",IntegerType)

  /*val schema2 = StructType(Array(
    StructField("name",StringType,true),
    StructField("firstname",StringType,true),
    StructField("middlename",StringType,true),
    StructField("lastname",StringType,true),
    StructField("dob", StringType, true),
    StructField("gender", StringType, true),
    StructField("salary", IntegerType, true)
  ))*/

  val df2 = spark.createDataFrame(spark.sparkContext.parallelize(data),schema)
  df.printSchema()

  //Using Spark withColumnRenamed – To rename DataFrame
  df2.withColumnRenamed("dob","DateOfBirth").printSchema()

  //Using withColumnRenamed – To rename multiple columns
  val df3 = df.withColumnRenamed("dob","DateOfBirth")
    .withColumnRenamed("salary","salary_amount")
  df3.printSchema()

  //Using Spark StructType – To rename a nested column in Dataframe
  val schema3 = new StructType()
    .add("fname",StringType)
    .add("middlename",StringType)
    .add("lname",StringType)

  df.select(col("name").cast(schema3),
    col("dob"),
    col("gender"),
    col("salary"))
    .printSchema()


  //Using Select – To rename nested elements.
  df.select(col("name.firstname").as("fname"),
    col("name.middlename").as("mname"),
    col("name.lastname").as("lname"),
    col("dob"),col("gender"),col("salary"))
    .printSchema()

  //Using Spark DataFrame withColumn – To rename nested columns
  val df4 = df.withColumn("fname",col("name.firstname"))
    .withColumn("mname",col("name.middlename"))
    .withColumn("lname",col("name.lastname"))
    .drop("name")
  df4.printSchema()

  //Using col() function – To Dynamically rename all or multiple columns
  val old_columns = Seq("dob","gender","salary","fname","mname","lname")
  val new_columns = Seq("DateOfBirth","Sex","salary","firstName","middleName","lastName")
  val columnsList = old_columns.zip(new_columns).map(f=>{col(f._1).as(f._2)})
  val df5 = df4.select(columnsList:_*)
  df5.printSchema()

  //Using toDF() – To change all columns in a Spark DataFrame
  val newColumns = Seq("newCol1","newCol2","newCol3", "newCol4")
  val df6 = df.toDF(newColumns:_*)
  df6.printSchema()


  val df7 = df.drop("name")
  df7.printSchema()

  //df.drop(df("name")).printSchema()


  //Drop multiple columns from DataFrame
  //Refering more than one column
  df.drop("firstname","middlename","lastname")
    .printSchema()

  // using array/sequence of columns
  val cols = Seq("firstname","middlename","lastname")
  df.drop(cols:_*)
    .printSchema()





















}
