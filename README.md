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

-Check spark-submit setup properly

```
spark-submit --version
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

