from pyspark.sql import SparkSession
from pyspark import StorageLevel, Row
from pyspark.sql.types import StructType, StructField, StringType, IntegerType

# Creation of spark object
spark = SparkSession.builder \
    .master("local[1]") \
    .appName("PySpark Examples") \
    .getOrCreate()

dept = [("Finance",10),
        ("Marketing",20),
        ("Sales",30),
        ("IT",40)
        ]
rdd = spark.sparkContext.parallelize(dept)

df = rdd.toDF()
df.printSchema()
df.show(truncate=False)

deptColumns = ["dept_name","dept_id"]
df2 = rdd.toDF(deptColumns)
df2.printSchema()
df2.show(truncate=False)

deptDF = spark.createDataFrame(data=dept, schema = deptColumns)
deptDF.printSchema()
deptDF.show(truncate=False)

deptSchema = StructType([
    StructField('dept_name', StringType(), True),
    StructField('dept_id', StringType(), True)
])

deptDF1 = spark.createDataFrame(data=dept, schema = deptSchema)
deptDF1.printSchema()
deptDF1.show(truncate=False)