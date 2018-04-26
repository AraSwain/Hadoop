## Hive
Hive is a data warehouse tool to process structured data in Hadoop. It built on top of MapReduce to process Big Data. It makes querying and analyzing as easy as SQL.

In this tutorial we will briefly discuss how to use Apache Hive and HQL (Hive Query Language) with HDFS (Hadoop Distributed File System).

### 1. Introduction

### 2. HIVE Architecture

### 3. Partitioning
#### 3.1. Static Partitioning

#### 3.2. Dynamic Partitioning
```sh
set hive.exec.dynamic.partition=true;
```
```sh
set hive.exec.dynamic.partition.mode=nonstrict;
```

### 4. Bucketing

### 5. Index