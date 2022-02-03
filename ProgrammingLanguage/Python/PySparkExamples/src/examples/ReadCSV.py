from pyspark.sql import SparkSession
from pyspark import StorageLevel, Row
from pyspark.sql.functions import col, lit, array_contains, split
from pyspark.sql.types import StructType, StructField, StringType, IntegerType, ArrayType, DoubleType, BooleanType

# Creation of spark object
spark = SparkSession.builder \
    .master("local[1]") \
    .appName("PySpark Examples") \
    .getOrCreate()

file_path = "../../config/weather.csv"
df = spark.read.csv(file_path)
df.printSchema()

df = spark.read.format("csv").load(file_path)
#or
#df = spark.read.format("org.apache.spark.sql.csv").load(file_path)
df.printSchema()

#Using Header record for column names
df2 = spark.read.option("header",True).csv(file_path)

#Read multiple CSV files
#df = spark.read.csv("file_path,file_path,file_path")

#Read all CSV files in a directory
#df = spark.read.csv("Folder path")

#Options while reading CSV file
df3 = spark.read.options(delimiter=',') \
    .csv(file_path)

#inferSchema
df4 = spark.read.options(inferSchema='True',delimiter=',') \
    .csv(file_path)

#or
df4 = spark.read.option("inferSchema",True) \
    .option("delimiter",",") \
    .csv(file_path)

#header
df3 = spark.read.options(header='True', inferSchema='True', delimiter=',') \
    .csv(file_path)


#Reading CSV files with a user-specified custom schema
schema = StructType() \
    .add("RecordNumber",IntegerType(),True) \
    .add("Zipcode",IntegerType(),True) \
    .add("ZipCodeType",StringType(),True) \
    .add("City",StringType(),True) \
    .add("State",StringType(),True) \
    .add("LocationType",StringType(),True) \
    .add("Lat",DoubleType(),True) \
    .add("Long",DoubleType(),True) \
    .add("Xaxis",IntegerType(),True) \
    .add("Yaxis",DoubleType(),True) \
    .add("Zaxis",DoubleType(),True) \
    .add("WorldRegion",StringType(),True) \
    .add("Country",StringType(),True) \
    .add("LocationText",StringType(),True) \
    .add("Location",StringType(),True) \
    .add("Decommisioned",BooleanType(),True) \
    .add("TaxReturnsFiled",StringType(),True) \
    .add("EstimatedPopulation",IntegerType(),True) \
    .add("TotalWages",IntegerType(),True) \
    .add("Notes",StringType(),True)

df_with_schema = spark.read.format("csv") \
    .option("header", True) \
    .schema(schema) \
    .load("../../config/zipcodes.csv")


#Write PySpark DataFrame to CSV file
df.write.option("header",True) \
    .csv("../../config/zipcodes_1.csv")

#Options
df2.write.options(header='True', delimiter=',') \
    .mode('overwrite')\
    .csv("../../config/zipcodes_1.csv")

#Saving modes
df2.write.mode('overwrite').csv("../../config/zipcodes_1.csv")
#you can also use this
df2.write.format("csv").mode('overwrite').save("../../config/zipcodes_1.csv")


