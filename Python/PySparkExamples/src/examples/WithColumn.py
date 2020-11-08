from pyspark.sql import SparkSession
from pyspark import StorageLevel, Row
from pyspark.sql.functions import col,lit
from pyspark.sql.types import StructType, StructField, StringType, IntegerType

# Creation of spark object
spark = SparkSession.builder \
    .master("local[1]") \
    .appName("PySpark Examples") \
    .getOrCreate()

data = [('James','','Smith','1991-04-01','M',3000),
        ('Michael','Rose','','2000-05-19','M',4000),
        ('Robert','','Williams','1978-09-05','M',4000),
        ('Maria','Anne','Jones','1967-12-01','F',4000),
        ('Jen','Mary','Brown','1980-02-17','F',-1)
        ]

columns = ["firstname","middlename","lastname","dob","gender","salary"]
df = spark.createDataFrame(data=data, schema = columns)

#Change column DataType using PySpark withcolumn
df2 = df.withColumn("salary",col("salary").cast("Integer"))
df2.printSchema()

#Update the value of an existing column
df3 = df.withColumn("salary",col("salary")*100)
df3.printSchema()

#Create a new column from an existing
df4 = df.withColumn("CopiedColumn",col("salary")* -1)
df3.printSchema()

#Add a new column using withColumn()
df5 = df.withColumn("Country", lit("USA"))
df5.printSchema()

df6 = df.withColumn("Country", lit("USA"))\
    .withColumn("anotherColumn",lit("anotherValue"))
df6.printSchema()

#Rename column name
df.withColumnRenamed("gender","sex") \
    .show(truncate=False)

#Drop a column from PySpark DataFrame
df4.drop("CopiedColumn") \
    .show(truncate=False)













