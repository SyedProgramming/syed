from pyspark.sql import SparkSession

# Creation of spark object
spark = SparkSession.builder \
        .master("local[1]") \
        .appName("PySpark Examples") \
        .getOrCreate()


#Create RDD from parallelize
dataList = [("Java", 20000), ("Python", 30000), ("Scala", 25000)]
rdd = spark.sparkContext.parallelize(dataList)
print(rdd.collect())

#using textFile()
rdd2 = spark.sparkContext.textFile("../../config/shakespeare.txt")
print(rdd2.collect())

#using createDataFrame()
data = [
    ('Syed','','Hussain','1988-04-01','M',3000),
    ('Abhishek','Sathe','','1986-05-19','M',4000),
    ('Nabeelah','','Harris','2000-09-05','F',4000),
    ('Amit','Himalayas','Chugh','1967-12-01','M',2000),
    ('Sagar','Singh','Pandey','1980-02-17','M',-1)
]
columns = ["first_name", "middle_name", "last_name", "dob", "gender", "salary"]
df = spark.createDataFrame(data=data, schema=columns)
print(df.show())

#DataFrame from external data sources
df = spark.read.csv("../../config/weather.csv")
print(df.show())

#sql
df.createOrReplaceTempView("WEATHER_DATA")
df2 = spark.sql("SELECT * FROM WEATHER_DATA")
#print(df2.printSchema())
print(df2.show())

#group by
df = spark.createDataFrame(data=data, schema=columns)
df.createOrReplaceTempView("PERSON_DATA")
group_df = spark.sql("SELECT gender, count(*) FROM PERSON_DATA GROUP BY gender")
print(group_df.show())

#Streaming from TCP Socket
df = spark.readStream\
     .format("socket")\
     .option("host","localhost")\
     .option("port","9090")\
     .load()
print(df.printSchema())
query = df.writeStream\
        .format("console")\
        .outputMode("complete")\
        .start()\
        .awaitTermination()

#Streaming from Kafka
df = spark.readStream\
     .format("kafka")\
     .option("kafka.bootstrap.servers", "192.168.1.100:9092")\
     .option("subscribe", "json_topic")\
     .option("startingOffsets", "earliest")\
     .load()

df.selectExpr("CAST(id AS STRING) AS key", "to_json(struct(*)) AS value")\
    .writeStream\
    .format("kafka")\
    .outputMode("append") \
    .option("kafka.bootstrap.servers", "192.168.1.100:9092")\
    .option("topic", "josn_data_topic")\
    .start()\
    .awaitTermination()


#newSession()
spark_session = SparkSession.newSession()













