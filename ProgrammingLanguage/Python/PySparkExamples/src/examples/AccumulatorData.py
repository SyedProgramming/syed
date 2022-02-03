import pyspark
from pyspark.sql import SparkSession

# Creation of spark object
spark = SparkSession.builder \
    .master("local[1]") \
    .appName("PySpark Examples") \
    .getOrCreate()

accum = spark.sparkContext.accumulator(0)
rdd = spark.sparkContext.parallelize([1,2,3,4,5])
rdd.foreach(lambda x : accum.add(x))
print(accum.value)

accuSum=spark.sparkContext.accumulator(0)
def count_fun(x):
    global accuSum
    accuSum+=x
rdd.foreach(count_fun)
print(accuSum.value)

accumCount=spark.sparkContext.accumulator(0)
rdd.foreach(lambda x:accumCount.add(1))
print(accumCount.value)