## Scala Spark HelloWorld 

Demo how to read a parquet file from resources folder.

**Setup**

- Download spark-hadoop from: https://archive.apache.org/dist/spark/

<img width="645" alt="image" src="https://user-images.githubusercontent.com/26292532/182172609-8bb09380-9e5f-4a7e-b27b-3cb4d6e4a466.png">

- Ensure openjdk is installed & JAVA_HOME + SPARK_HOME are set
```
export JAVA_HOME="/Library/Java/JavaVirtualMachines/jdk1.8.0_202.jdk/Contents/H$
export SPARK_HOME="/Library/Spark/spark-2.4.3-bin-hadoop2.6"
export PATH=$PATH:$JAVA_HOME/bin
export PATH=$PATH:$SPARK_HOME/bin
```

- Check spark-submit setup properly

```
spark-submit --version
```

### Clone Repo

Setup

1. Set openjdk

<img width="852" alt="openjdk" src="https://user-images.githubusercontent.com/26292532/182180595-263b433b-af49-4fa5-8e35-9d21ddb91a8b.png">

2. Add Scala framework support

<img width="404" alt="scala_frameworksupport" src="https://user-images.githubusercontent.com/26292532/182180678-afb9a57c-ce88-47ae-bde9-48337d0113c7.png">

3. Mark source directory

<img width="707" alt="Screen Shot 2022-08-01 at 10 44 00 AM" src="https://user-images.githubusercontent.com/26292532/182180767-71d915da-2394-4bb2-9669-a3802f32bb79.png">



Note: SparkSession -  need to ensure you set spark-config to: *.config("spark.master", "local")*

```
val spark = SparkSession.builder().appName("HelloSpark").config("spark.master", "local").getOrCreate()
```

**Compile Jar**

Note: if need to run on SM, make sure spark dependencies are set to **'provided'** and NOT 'compile' (in case you need to run it locally)

```
mvn package
```

**Run Jar**

```
spark-submit --class HelloWorld /path/to/ScalaSparkHW/target/ScalaSparkHW-1.2-SNAPSHOT-fat.jar

```
**To-do**

For some reason jar is able to load the parquet file when executed using 

```
java -cp /path/to/fatjar HelloWorld
```

but not using spark-submit and throws Java Illegal exception error.
