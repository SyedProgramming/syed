from pyspark.sql import SparkSession
from pyspark import StorageLevel, Row
from pyspark.sql.functions import col, lit, array_contains, udf, split
from pyspark.sql.types import StructType, StructField, StringType, IntegerType, ArrayType

# Creation of spark object
spark = SparkSession.builder \
    .master("local[1]") \
    .appName("PySpark Examples") \
    .getOrCreate()

columns = ["Seqno","Name"]
data = [("1", "john jones"),
        ("2", "tracey smith"),
        ("3", "amy sanders")]

df = spark.createDataFrame(data=data,schema=columns)
df.show(truncate=False)

#Create a Python Function
def convertCase(str):
    return str

#Convert a Python function to PySpark UDF
""" Converting function to UDF """
convertUDF = udf(lambda z: convertCase(z),StringType())

""" 
Note: The default type of the udf() is StringType hence, you can also write the above statement without return type.
Converting function to UDF 
StringType() is by default hence not required 
convertUDF = udf(lambda z: convertCase(z)) 
"""

#Using UDF with PySpark DataFrame select()
df.select(col("Seqno"), \
          convertUDF(col("Name")).alias("Name")) \
    .show(truncate=False)


#Using UDF with PySpark DataFrame withColumn()
def upperCase(str):
    return str

upperCaseUDF = udf(lambda z:upperCase(z),StringType())
df.withColumn("Curated Name", upperCaseUDF(col("Name"))) \
    .show(truncate=False)

#Registering PySpark UDF & use it on SQL
""" Using UDF on SQL """
spark.udf.register("convertUDF", convertCase,StringType())
df.createOrReplaceTempView("NAME_TABLE")
spark.sql("select Seqno, convertUDF(Name) as Name from NAME_TABLE") \
    .show(truncate=False)


#Creating UDF using annotation
@udf(returnType=StringType())
def upperCase(str):
    return str.upper()

df.withColumn("Curated Name", upperCase(col("Name"))) \
    .show(truncate=False)


#Execution order
""" 
No guarantee Name is not null will execute first
If convertUDF(Name) like '%John%' execute first then 
you will get runtime error
"""
spark.sql("select Seqno, convertUDF(Name) as Name from NAME_TABLE " + \
          "where Name is not null and convertUDF(Name) like '%John%'") \
    .show(truncate=False)

#Handling null check
""" null check """

columns = ["Seqno","Name"]
data = [("1", "john jones"),
        ("2", "tracey smith"),
        ("3", "amy sanders"),
        ('4',None)]

df2 = spark.createDataFrame(data=data,schema=columns)
df2.show(truncate=False)
df2.createOrReplaceTempView("NAME_TABLE2")

spark.sql("select convertUDF(Name) from NAME_TABLE2") \
    .show(truncate=False)


spark.udf.register("_nullsafeUDF", lambda str: convertCase(str) if not str is None else "" , StringType())

spark.sql("select _nullsafeUDF(Name) from NAME_TABLE2") \
    .show(truncate=False)

spark.sql("select Seqno, _nullsafeUDF(Name) as Name from NAME_TABLE2 " + \
          " where Name is not null and _nullsafeUDF(Name) like '%John%'") \
    .show(truncate=False)





