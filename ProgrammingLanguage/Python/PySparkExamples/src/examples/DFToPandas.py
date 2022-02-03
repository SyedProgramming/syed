from pyspark.sql import SparkSession
from pyspark import StorageLevel, Row
from pyspark.sql.types import StructType, StructField, StringType, IntegerType

# Creation of spark object
spark = SparkSession.builder \
    .master("local[1]") \
    .appName("PySpark Examples") \
    .getOrCreate()

data = [("James","","Smith","36636","M",60000),
        ("Michael","Rose","","40288","M",70000),
        ("Robert","","Williams","42114","",400000),
        ("Maria","Anne","Jones","39192","F",500000),
        ("Jen","Mary","Brown","","F",0)]
columns = ["first_name","middle_name","last_name","dob","gender","salary"]

pysparkDF = spark.createDataFrame(data = data, schema = columns)
pysparkDF.printSchema()
pysparkDF.show(truncate=False)

#PySpark Dataframe to Pandas DataFrame
pandasDF = pysparkDF.toPandas()
print(pandasDF)


#Converting nested struct DataFrame
dataStruct = [(("James","","Smith"),"36636","M","3000"),
              (("Michael","Rose",""),"40288","M","4000"),
              (("Robert","","Williams"),"42114","M","4000"),
              (("Maria","Anne","Jones"),"39192","F","4000"),
              (("Jen","Mary","Brown"),"","F","-1")
              ]

schemaStruct = StructType([
    StructField('name', StructType([
        StructField('firstname', StringType(), True),
        StructField('middlename', StringType(), True),
        StructField('lastname', StringType(), True)
    ])),
    StructField('dob', StringType(), True),
    StructField('gender', StringType(), True),
    StructField('salary', StringType(), True)
])
df = spark.createDataFrame(data=dataStruct, schema = schemaStruct)
df.printSchema()

pandasDF2 = df.toPandas()
print(pandasDF2)
