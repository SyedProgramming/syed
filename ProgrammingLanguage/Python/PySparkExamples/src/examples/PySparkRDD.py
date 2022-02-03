from pyspark.sql import SparkSession
from pyspark import StorageLevel

# Creation of spark object
spark = SparkSession.builder \
    .master("local[1]") \
    .appName("PySpark Examples") \
    .getOrCreate()


#Create RDD from parallelize
data = [1,2,3,4,5,6,7,8,9,10,11,12]
rdd=spark.sparkContext.parallelize(data)
print(rdd.collect())

#using textFile()
rdd2 = spark.sparkContext.textFile("../../config/shakespeare.txt")
print(rdd2.collect())

#wholeTextFiles()
rdd3 = spark.sparkContext.wholeTextFiles("../../config/shakespeare.txt")
print(rdd3.collect())

# Creates empty RDD with no partition
rdd4 = spark.sparkContext.emptyRDD()

#Create empty RDD with partition
rdd5 = spark.sparkContext.parallelize([],10)
print("is Empty RDD : "+str(rdd5.isEmpty()))

#getNumPartitions()
print("Initial Partition Count: " + str(rdd3.getNumPartitions()))
print("Initial Partition Count: " + str(rdd5.getNumPartitions()))

#Repartition and Coalesce
repar_rdd = rdd5.repartition(4)
recol_rdd = repar_rdd.coalesce(2)
print("Partition Count: " + str(repar_rdd.getNumPartitions()))
print("Partition Count: " + str(recol_rdd.getNumPartitions()))

# Transformations
#flatmap()
file = spark.sparkContext.textFile("../../config/shakespeare.txt")
flattened_data = file.flatMap(lambda x : x.split(" "))
print(flattened_data.collect())

#map()
mapped_data = flattened_data.map(lambda x : (x,1))
print(mapped_data.collect())

#reduceByKey()
reduced_data = mapped_data.reduceByKey(lambda a,b : a+b)
print(reduced_data.collect())

#sortByKey()
sorted_data = reduced_data.map(lambda x : (x[1], x[0])).sortByKey()
print(sorted_data.collect())

#filter()
filtered_data = sorted_data.filter(lambda x : 'an' in x[1])
print(filtered_data.collect())

#Actions
#count()
print(filtered_data.count())

#first()
print(filtered_data.first())

#max()
print(filtered_data.max())

#reduce()
total_word_counts = filtered_data.reduce(lambda a,b : (a[0]+b[0], a[1]))
print(total_word_counts[0])

#take()
take_data = filtered_data.take(3)
print(take_data)

#collect()
print(filtered_data.collect())

#saveAsTextFile()
filtered_data.saveAsTextFile("../../config/filtered_data")

# cache() and persist()
cached_rdd = filtered_data.cache()
persist_rdd = filtered_data.persist(StorageLevel.MEMORY_ONLY)

#unpersist()
unpersist_rdd = persist_rdd.unpersist()


#Converts RDD to DataFrame
df_from_rdd = filtered_data.toDF()
#using createDataFrame() - Convert RDD to DataFrame
df = spark.createDataFrame(filtered_data).toDF("key","value")
print(df.show())
# Convert DataFrame to RDD
rdd = df.rdd

#setting property value in programmatical way
spark.conf.set("spark.sql.shuffle.partitions", "500")
#setting property value using spark-submit
#./bin/spark-submit --conf spark.sql.shuffle.partitions=500 --conf spark.default.parallelism=500












