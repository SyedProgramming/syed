from pyspark.sql import SparkSession
from pyspark import StorageLevel, Row
from pyspark.sql.types import StructType, StructField, StringType, IntegerType

# Creation of spark object
spark = SparkSession.builder \
    .master("local[1]") \
    .appName("PySpark Examples") \
    .getOrCreate()

columns = ["language", "users_count"]
data = [("Java","20000"), ("Python", "10000"), ("Scala","3000")]

#create DataFrame from RDD
rdd = spark.sparkContext.parallelize(data)
#toDF() function
df = rdd.toDF()
print(df.printSchema())
df = rdd.toDF(columns)
print(df.printSchema())

#createDataFrame() from SparkSession
df = spark.createDataFrame(rdd).toDF(*columns)
print(df.printSchema())

#createDataFrame() with the Row type
row_data = map(lambda x : Row(*x), data)
df = spark.createDataFrame(row_data, columns)

#create DataFrame with schema
data =  [("James","","Smith","36636","M",3000),
         ("Michael","Rose","","40288","M",4000),
         ("Robert","","Williams","42114","M",4000),
         ("Maria","Anne","Jones","39192","F",4000),
         ("Jen","Mary","Brown","","F",-1)
         ]

schema = StructType([
    StructField("first_name", StringType(), True),
    StructField("middle_name", StringType(), True),
    StructField("last_name", StringType(), True),
    StructField("id", StringType(), True),
    StructField("gender", StringType(), True),
    StructField("salary", IntegerType(), True)
])

df = spark.createDataFrame(data=data, schema=schema)
print(df.printSchema())
print(df.show(truncate=False))

#creating DataFrame from CSV
df = spark.read.csv("../../config/weather.csv")

#creating DataFrame from text file
df = spark.read.text("../../config/shakespeare.txt")

#creating from JSON file
df = spark.read.json(("../../config/countries.json"))
print(df.printSchema())
print(df.show())

#parquet format
df = spark.read.csv("../../config/weather.csv")
#df.write.parquet("../../config/weather.parquet")
parquet_df = spark.read.parquet("../../config/weather.parquet")
print(parquet_df.show())

#Append or Overwrite an existing Parquet file
df.write.mode('append').parquet("../../config/people.parquet")
df.write.mode('overwrite').parquet("../../config/people.parquet")


#Executing SQL queries DataFrame
parquet_df.createOrReplaceTempView("ParquetTable")
parkSQL = spark.sql("select * from ParquetTable")
print(parkSQL.show())


#Creating a table on Parquet file
spark.sql("CREATE TEMPORARY VIEW PERSON USING parquet OPTIONS (path \"../../config/people.parquet\")")
spark.sql("SELECT * FROM PERSON").show()

#partitionBy()
df = spark.createDataFrame(data=data, schema=schema)
df.write.partitionBy("gender","salary").mode("overwrite").parquet("../../config/people2.parquet")

parDF2=spark.read.parquet("../../config/people2.parquet/gender=M")
parDF2.show(truncate=False)


#Creating a table on Partitioned Parquet file
spark.sql("CREATE TEMPORARY VIEW PERSON2 USING parquet OPTIONS (path \"../../config/people2.parquet/gender=F\")")
spark.sql("SELECT * FROM PERSON2" ).show()


