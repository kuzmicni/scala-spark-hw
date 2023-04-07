import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.functions.{col, udf, when, sum}
import org.apache.spark.sql.{DataFrame, SQLContext, SparkSession}

object HelloWorld {
  def main(args: Array[String]): Unit = {
    println("HelloWorld")

//    val myArg = args(0)
//    println(s"my ARG: $myArg")

    val sparkConf = new SparkConf()
      .setAppName("MySparky")
      .setMaster("local[6]")
      .set("spark.eventLog.enabled", "true")
      .set("spark.eventLog.dir","/Users/nikolakuzmic/spark-events")

//    val sparkContext = new SparkContext(sparkConf)

    //    val spark = SparkSession.builder().appName("HelloSpark").config("spark.master", "local").config("spark.driver.host","127.0.0.1").config("spark.eventLog.dir","/Users/nikolakuzmic/spark-events").getOrCreate()
    val spark = SparkSession.builder().config(sparkConf).getOrCreate()

    val eventLogDir = sparkConf.get("spark.eventLog.dir")
    println(s"Event log directory: $eventLogDir")

    val sampSeq =Seq((1,"spark"), (2,"hadoop"))
    val columns = Seq("num","tech")

    val df = spark.createDataFrame(sampSeq).toDF(columns:_*)
//    val newDataDF = sqlContext.read.parquet("Sales.parquet")
    df.show()

    // Generate 1000 random words
    val words = Seq.fill(300000)(scala.util.Random.alphanumeric.take(10).mkString)

    // Create DataFrame with words and row numbers
    val dfWords = spark.createDataFrame(words.zipWithIndex)
      .toDF("word", "row_num")

    var a = dfWords.count();

    println(s"----------- Num of ROWS: $a ----------------")

    val sumCol = dfWords.agg(sum("row_num")).collect()(0)(0)
    println(s"----------- SUM of ROW NUMBERS: $sumCol ----------------")

    val containsTwoAs = udf((s: String) => s.count(_ == 'U') == 1)
    val dfWY = dfWords.withColumn("y", when(containsTwoAs(col("word")), 1).otherwise(0))
    dfWY.show()
    val sumColU = dfWY.agg(sum("y")).collect()(0)(0)
    println(s"----------- SUM of U WORDS: $sumColU ----------------")

//    df.write.mode("overwrite").parquet("./src/main/resources/data")


//    println("Reading parquet file")
//
    val parqDF = spark.read.parquet("src/main/resources/data/*.parquet")
//    val parqDF = spark.read.parquet("/Users/nikolakuzmic/Desktop/scala-spark-hw/src/main/resources/data/part-00000-aa642a6d-fc9d-4c7d-82cd-dff5b8afa1d3-c000.snappy.parquet")

    parqDF.show()
  }

}
