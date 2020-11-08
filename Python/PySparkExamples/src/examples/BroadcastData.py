import pyspark
from pyspark.sql import SparkSession

# Creation of spark object
spark = SparkSession.builder \
    .master("local[1]") \
    .appName("PySpark Examples") \
    .getOrCreate()

states = {"NY":"New York", "CA":"California", "FL":"Florida"}
broadcast_states = spark.sparkContext.broadcast(states)

data = [
    ("James","Smith","USA","CA"),
    ("Michael","Rose","USA","NY"),
    ("Robert","Williams","USA","CA"),
    ("Maria","Jones","USA","FL")
]
rdd = spark.sparkContext.parallelize(data)

def state_lookup(code):
    return broadcast_states.value[code]

result = rdd.map(lambda x : (x[0], x[1], x[2], state_lookup(x[3]))).collect()
print(result)

columns = ["firstname","lastname","country","state"]
df = spark.createDataFrame(data = data, schema=columns)
print(df.printSchema())
df.show(truncate=False)

result = df.rdd.map(lambda x : (x[0], x[1], x[2], state_lookup(x[3]))).toDF(columns)
print(result.printSchema())
result.show(truncate=False)