import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.{DataFrame, SQLContext}

object HelloWorld {
  def main(args: Array[String]): Unit = {
    println("HelloWorld")

    val spark = SparkSession.builder().appName("HelloSpark").config("spark.master", "local").config("spark.driver.host","127.0.0.1").getOrCreate()

    val sampSeq =Seq((1,"spark"), (2,"hadoop"))
    val columns = Seq("num","tech")

    val df = spark.createDataFrame(sampSeq).toDF(columns:_*)
//    val newDataDF = sqlContext.read.parquet("Sales.parquet")
    df.show()

//    df.write.mode("overwrite").parquet("./src/main/resources/data")


//    println("Reading parquet file")
//
    val parqDF = spark.read.parquet("src/main/resources/data/*.parquet")
//    val parqDF = spark.read.parquet("/Users/nikolakuzmic/Desktop/scala-spark-hw/src/main/resources/data/part-00000-aa642a6d-fc9d-4c7d-82cd-dff5b8afa1d3-c000.snappy.parquet")

    parqDF.show()
  }

}
