from pyspark.sql import SparkSession
from pyspark import StorageLevel, Row
from pyspark.sql.functions import col, lit, array_contains
from pyspark.sql.types import StructType, StructField, StringType, IntegerType, ArrayType

# Creation of spark object
spark = SparkSession.builder \
    .master("local[1]") \
    .appName("PySpark Examples") \
    .getOrCreate()

simpleData = [("James","Sales","NY",90000,34,10000), \
              ("Michael","Sales","NY",86000,56,20000), \
              ("Robert","Sales","CA",81000,30,23000), \
              ("Maria","Finance","CA",90000,24,23000), \
              ("Raman","Finance","CA",99000,40,24000), \
              ("Scott","Finance","NY",83000,36,19000), \
              ("Jen","Finance","NY",79000,53,15000), \
              ("Jeff","Marketing","CA",80000,25,18000), \
              ("Kumar","Marketing","NY",91000,50,21000) \
              ]
columns= ["employee_name","department","state","salary","age","bonus"]
df = spark.createDataFrame(data = simpleData, schema = columns)
df.printSchema()
df.show(truncate=False)

#DataFrame sorting using the sort() function
df.sort("department","state").show(truncate=False)
df.sort(col("department"),col("state")).show(truncate=False)

#DataFrame sorting using orderBy() function
df.orderBy("department","state").show(truncate=False)
df.orderBy(col("department"),col("state")).show(truncate=False)

#Sort by Ascending (ASC)
df.sort(df.department.asc(),df.state.asc()).show(truncate=False)
df.sort(col("department").asc(),col("state").asc()).show(truncate=False)
df.orderBy(col("department").asc(),col("state").asc()).show(truncate=False)

#Sort by Descending (DESC)
df.sort(df.department.asc(),df.state.desc()).show(truncate=False)
df.sort(col("department").asc(),col("state").desc()).show(truncate=False)
df.orderBy(col("department").asc(),col("state").desc()).show(truncate=False)

#asc_nulls_first() and asc_nulls_last()
df.sort(df.department.asc_nulls_first(),df.state.asc_nulls_last()).show(truncate=False)

#Using Raw SQL
df.createOrReplaceTempView("EMP")
spark.sql("select employee_name,department,state,salary,age,bonus from EMP ORDER BY department asc").show(truncate=False)