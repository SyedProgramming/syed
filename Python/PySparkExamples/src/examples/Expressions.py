from pyspark.sql import SparkSession
from pyspark import StorageLevel, Row
from pyspark.sql.types import StructType, StructField, StringType, IntegerType

# Creation of spark object
spark = SparkSession.builder \
    .master("local[1]") \
    .appName("PySpark Examples") \
    .getOrCreate()

data = [("James","Smith","USA","CA"),
        ("Michael","Rose","USA","NY"),
        ("Robert","Williams","USA","CA"),
        ("Maria","Jones","USA","FL")
        ]
columns = ["firstname","lastname","country","state"]
df = spark.createDataFrame(data = data, schema = columns)
df.show(truncate=False)

print(df.select("firstname").show())
print(df.select("firstname","lastname").show())
print(df.select(df.firstname,df.lastname).show())



data = [
    (("James",None,"Smith"),"OH","M"),
    (("Anna","Rose",""),"NY","F"),
    (("Julia","","Williams"),"OH","F"),
    (("Maria","Anne","Jones"),"NY","M"),
    (("Jen","Mary","Brown"),"NY","M"),
    (("Mike","Mary","Williams"),"OH","M")
]

schema = StructType([
    StructField('name', StructType([
        StructField('firstname', StringType(), True),
        StructField('middlename', StringType(), True),
        StructField('lastname', StringType(), True)
    ])),
    StructField('state', StringType(), True),
    StructField('gender', StringType(), True)
])
df2 = spark.createDataFrame(data = data, schema = schema)
df2.printSchema()
df2.show(truncate=False) # shows all columns
print(df2.select("name").show(truncate=False))
print(df2.select("name.firstname","name.lastname").show(truncate=False))

print(df2.select("name.*").show(truncate=False))


