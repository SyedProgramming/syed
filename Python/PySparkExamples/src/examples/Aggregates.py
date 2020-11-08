from pyspark.sql import SparkSession
from pyspark import StorageLevel, Row
from pyspark.sql.functions import col, lit, array_contains, udf, split, \
    approx_count_distinct, avg, collect_list, collect_set, \
    countDistinct, count, first, last, kurtosis, mean, \
    skewness, stddev, stddev_samp, stddev_pop, variance, var_pop, \
    sumDistinct, var_samp, max, min, sum
from pyspark.sql.types import StructType, StructField, StringType, IntegerType, ArrayType

# Creation of spark object
spark = SparkSession.builder \
    .master("local[1]") \
    .appName("PySpark Examples") \
    .getOrCreate()


simpleData = [("James", "Sales", 3000),
              ("Michael", "Sales", 4600),
              ("Robert", "Sales", 4100),
              ("Maria", "Finance", 3000),
              ("James", "Sales", 3000),
              ("Scott", "Finance", 3300),
              ("Jen", "Finance", 3900),
              ("Jeff", "Marketing", 3000),
              ("Kumar", "Marketing", 2000),
              ("Saif", "Sales", 4100)
              ]
schema = ["employee_name", "department", "salary"]
df = spark.createDataFrame(data=simpleData, schema = schema)
df.printSchema()
df.show(truncate=False)


#approx_count_distinct Aggregate Function
print("approx_count_distinct: " + \
      str(df.select(approx_count_distinct("salary")).collect()[0][0]))
#Prints approx_count_distinct: 6

#avg (average) Aggregate Function
#avg
print("avg: " + str(df.select(avg("salary")).collect()[0][0]))
#Prints avg: 3400.0

#collect_list Aggregate Function
df.select(collect_list("salary")).show(truncate=False)

#collect_set Aggregate Function
df.select(collect_set("salary")).show(truncate=False)

#countDistinct Aggregate Function
df2 = df.select(countDistinct("department", "salary"))
df2.show(truncate=False)
print("Distinct Count of Department & Salary: "+str(df2.collect()[0][0]))

#count function
print("count: "+str(df.select(count("salary")).collect()[0]))

#grouping function
'''grouping() Indicates whether a given input column is aggregated or not. 
returns 1 for aggregated or 0 for not aggregated in the result. 
If you try grouping directly on the salary column you will get below error.

Exception in thread "main" org.apache.spark.sql.AnalysisException:
// grouping() can only be used with GroupingSets/Cube/Rollup
'''

#first function
df.select(first("salary")).show(truncate=False)


#last function
df.select(last("salary")).show(truncate=False)


#kurtosis function
df.select(kurtosis("salary")).show(truncate=False)

#max function
df.select(max("salary")).show(truncate=False)

#min function
df.select(min("salary")).show(truncate=False)

#mean function
df.select(mean("salary")).show(truncate=False)

#skewness function
df.select(skewness("salary")).show(truncate=False)

#stddev(), stddev_samp() and stddev_pop()
df.select(stddev("salary"), stddev_samp("salary"), \
          stddev_pop("salary")).show(truncate=False)

#sum function
df.select(sum("salary")).show(truncate=False)

#sumDistinct function
df.select(sumDistinct("salary")).show(truncate=False)

#variance(), var_samp(), var_pop()
df.select(variance("salary"),var_samp("salary"),var_pop("salary")) \
    .show(truncate=False)

