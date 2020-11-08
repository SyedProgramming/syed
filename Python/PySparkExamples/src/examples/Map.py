from pyspark.sql import SparkSession
from pyspark import StorageLevel, Row
from pyspark.sql.functions import col, lit, array_contains, udf, split
from pyspark.sql.types import StructType, StructField, StringType, IntegerType, ArrayType

# Creation of spark object
spark = SparkSession.builder \
    .master("local[1]") \
    .appName("PySpark Examples") \
    .getOrCreate()

data = ["Project",
        "Gutenberg’s",
        "Alice’s",
        "Adventures",
        "in",
        "Wonderland",
        "Project",
        "Gutenberg’s",
        "Adventures",
        "in",
        "Wonderland",
        "Project",
        "Gutenberg’s"]

rdd=spark.sparkContext.parallelize(data)

rdd2=rdd.map(lambda x: (x,1))
for element in rdd2.collect():
    print(element)


