package com.examples

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession
import org.apache.spark.storage.StorageLevel.MEMORY_ONLY

object StorageLevel extends App{

  val spark:SparkSession = SparkSession
    .builder()
    .master("local[1]")
    .appName("Spark-Scala-Examples")
    .getOrCreate()

  val df = spark.range(0,20)
  val rdd = spark.sparkContext.parallelize(Range(0,20))

  val rdd2 = rdd.persist(MEMORY_ONLY)
  //or
  val df2 = df.persist(MEMORY_ONLY)

  //cache
  val rdd3 = rdd2.cache()

  //Persist
  val rddPersist = rdd.persist()

  //Unpersist
  val rddPersist2 = rddPersist.unpersist()

  /*Spark RDD Persist storage levels
  MEMORY_ONLY – This is the default behavior of the RDD cache() method and stores the RDD as deserialized objects to JVM memory. When there is no enough memory available it will not save to RDD of some partitions and these will be re-computed as and when required. This takes more storage but runs faster as it takes few CPU cycles to read from memory.

  MEMORY_ONLY_SER – This is the same as MEMORY_ONLY but the difference being it stores RDD as serialized objects to JVM memory. It takes lesser memory (space-efficient) then MEMORY_ONLY as it saves objects as serialized and takes an additional few more CPU cycles in order to deserialize.

  MEMORY_ONLY_2 – Same as MEMORY_ONLY storage level but replicate each partition to two cluster nodes.

  MEMORY_ONLY_SER_2 – Same as MEMORY_ONLY_SER storage level but replicate each partition to two cluster nodes.

  MEMORY_AND_DISK – In this Storage Level, The RDD will be stored in JVM memory as a deserialized objects. When required storage is greater than available memory, it stores some of the excess partitions in to disk and reads the data from disk when it required. It is slower as there is I/O involved.

  MEMORY_AND_DISK_SER – This is same as MEMORY_AND_DISK storage level difference being it serializes the RDD objects in memory and on disk when space not available.

  MEMORY_AND_DISK_2 – Same as MEMORY_AND_DISK storage level but replicate each partition to two cluster nodes.

  MEMORY_AND_DISK_SER_2 – Same as MEMORY_AND_DISK_SER storage level but replicate each partition to two cluster nodes.

  DISK_ONLY – In this storage level, RDD is stored only on disk and the CPU computation time is high as I/O involved.

  DISK_ONLY_2 – Same as DISK_ONLY storage level but replicate each partition to two cluster nodes.
  */

}
