## Hadoop

#### Table of Contents
- [1. Hadoop](#1-hadoop)
    + [Prerequisites](#prerequisites)
- [2. Getting Started with Hadoop](#2-getting-started-with-hadoop)
- [3. HDFS Architecture](#3-hdfs-architecture)
- [4. Map Reduce](#4-map-reduce)
- [5. YARN (MapReduce - 2)](#5-yarn--mapreduce---2-)
- [6. Hive](#6-hive)
  * [6.1. Introduction](#61-introduction)
  * [6.2. HIVE Architecture](#62-hive-architecture)
  * [6.3. Partitioning](#63-partitioning)
    + [6.3.1. Static Partitioning](#631-static-partitioning)
    + [6.3.2. Dynamic Partitioning](#632-dynamic-partitioning)
  * [6.4. Bucketing](#64-bucketing)
  * [6.5. Index](#65-index)
- [7. PIG](#7-pig)
- [8. SQOOP](#8-sqoop)
- [9. HBase](#9-hbase)
- [10. Oozie](#10-oozie)
- [Installation and Setup](#installation-and-setup)
  * [1. Java Installation](#1-java-installation)
  * [2. Hadoop Installation in Pseudo Distributed mode](#2-hadoop-installation-in-pseudo-distributed-mode)
  * [3. MySQL Installation](#3-mysql-installation)
  * [4. Pig Installation](#4-pig-installation)
  * [5. Hive Installation](#5-hive-installation)
  * [6. Hadoop Framework Code Setup](#6-hadoop-framework-code-setup)
    + [Pre-requisite](#pre-requisite)
- [Importing code to eclipse.](#importing-code-to-eclipse)

## 1. Hadoop
#### Prerequisites
  Before starting this tutorial, we assume you know the basics of 
  * Core Java (OOPS concepts and Hands-on coding)
  * Database Concepts and SQL
  * Hands on with any  Linux Operating Systems and commands

## 2. Getting Started with Hadoop
Hadoop is an open-source framework that allows to store and process huge amount of data in a distributed environment across clusters of cheap or commodity hardware. Hadoop is designed to scale up from single servers to thousands of servers, each offering local computation and storage. It is highly fault-tolerant.

Hadoop consists of the below two modules.
  * HDFS (Hadoop Distributed File System)
  * MapReduce

## 3. HDFS Architecture

## 4. Map Reduce

## 5. YARN (MapReduce - 2)

## 6. Hive
Hive is a data warehouse tool to process structured data in Hadoop. It built on top of MapReduce to process Big Data. It makes querying and analyzing as easy as SQL.

In this tutorial we will briefly discuss how to use Apache Hive and HQL (Hive Query Language) with HDFS (Hadoop Distributed File System).


## 7. PIG

## 8. SQOOP

## 9. HBase

## 10. Oozie

## Installation and Setup

### 1. Java Installation

### 2. Hadoop Installation in Pseudo Distributed mode

### 3. MySQL Installation
To install MySQL, you need to update the package index on your system and then install the package with `apt-get`
```sh
sudo apt-get update
sudo apt-get install mysql-server
```

To check the MySQL version
```sh
mysql --version
```

**Configure MySQL**
```sh
sudo mysql_secure_installation
```
This will ask you for the root password you created in the above step. Then it will take you to configurations questions. You can press `ENTER` to set the defaults.

**Checking MySQL Status**
```sh
service mysql status
```

You can start/stop MySQL service by running the following command.
```sh
service mysql start/stop
```

### 4. Pig Installation

### 5. Hive Installation

### 6. Hadoop Framework Code Setup

#### Pre-requisite
* Java 1.8.x or later
* Maven-3.x.x or later

1. Download the source zip file from the below link. Choose the version you want.
http://www-eu.apache.org/dist/hadoop/core/

2. Extract the hadoop-<version>-src.tar.gz  file. and the extracted directory is your hadoop base directory.

3. Install the protobuf compiler by executing any of the below command.
```sh
sudo apt-get install protobuf-compiler -y
sudo apt install protobuf-compiler
```
4. Goto hadoop-maven-plugins directory and build the project.
```sh
cd hadoop-maven-plugins
mvn install
```

5. Come back to hadoop base directory and build the hadoop project.
```sh
cd..
mvn clean package install -DskipTests
```
## Importing code to eclipse.

