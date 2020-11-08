from pyspark.sql import SparkSession
from pyspark import StorageLevel, Row
from pyspark.sql.functions import col, lit, array_contains, udf, split, row_number, rank, dense_rank, percent_rank, \
    ntile, cume_dist, lag, lead, avg, sum, min, max
from pyspark.sql.types import StructType, StructField, StringType, IntegerType, ArrayType
from pyspark.sql.window import Window


# Creation of spark object
spark = SparkSession.builder \
    .master("local[1]") \
    .appName("PySpark Examples") \
    .getOrCreate()

'''
PySpark Window functions operate on a group of rows (like frame, partition) 
and return a single value for every input row. 
PySpark SQL supports three kinds of window functions:
ranking functions
analytic functions
aggregate functions
'''

simpleData = (("James", "Sales", 3000), \
              ("Michael", "Sales", 4600), \
              ("Robert", "Sales", 4100), \
              ("Maria", "Finance", 3000), \
              ("James", "Sales", 3000), \
              ("Scott", "Finance", 3300), \
              ("Jen", "Finance", 3900), \
              ("Jeff", "Marketing", 3000), \
              ("Kumar", "Marketing", 2000), \
              ("Saif", "Sales", 4100) \
              )

columns= ["employee_name", "department", "salary"]
df = spark.createDataFrame(data = simpleData, schema = columns)
df.printSchema()
df.show(truncate=False)


#row_number Window Function
'''
row_number() window function is used to give the sequential row number starting from 1 to the result of each window partition.
'''

window_spec = Window.partitionBy("department").orderBy("salary")
df.withColumn("row_number", row_number().over(window_spec)).show(truncate=False)

#rank Window Function
'''
rank() window function is used to provide a rank to the result within a window partition. 
This function leaves gaps in rank when there are ties.
'''
df.withColumn("rank",rank().over(window_spec)) \
    .show()


#dense_rank Window Function
'''
dense_rank() window function is used to get the result with rank of rows within a window partition without any gaps. 
This is similar to rank() function difference being rank function leaves gaps in rank when there are ties.
'''
df.withColumn("dense_rank",dense_rank().over(window_spec)) \
    .show()

#percent_rank Window Function
df.withColumn("percent_rank",percent_rank().over(window_spec)) \
    .show()


#ntile Window Function
'''
ntile() window function returns the relative rank of result rows within a window partition. 
In below example we have used 2 as an argument to ntile hence it returns ranking between 2 values (1 and 2)
'''
df.withColumn("ntile",ntile(2).over(window_spec)) \
    .show()


#PySpark Window Analytic functions
#cume_dist Window Function
'''
cume_dist() window function is used to get the cumulative distribution of values within a window partition.
'''
df.withColumn("cume_dist",cume_dist().over(window_spec)) \
    .show()

#lag Window Function
df.withColumn("lag",lag("salary",2).over(window_spec)) \
    .show()

#lead Window Function
df.withColumn("lead",lead("salary",2).over(window_spec)) \
    .show()

#PySpark Window Aggregate Functions
windowSpecAgg  = Window.partitionBy("department")
df.withColumn("row",row_number().over(window_spec)) \
    .withColumn("avg", avg(col("salary")).over(windowSpecAgg)) \
    .withColumn("sum", sum(col("salary")).over(windowSpecAgg)) \
    .withColumn("min", min(col("salary")).over(windowSpecAgg)) \
    .withColumn("max", max(col("salary")).over(windowSpecAgg)) \
    .where(col("row")==1).select("department","avg","sum","min","max") \
    .show()

