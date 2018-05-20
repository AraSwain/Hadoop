![hadoop_logo_200px](https://user-images.githubusercontent.com/16832588/39321308-488f04c4-49a4-11e8-897a-7cdb64d9e72c.png)
### Table of Contents
- [1. Hadoop](#1-hadoop)
    + [Prerequisites](#prerequisites)
- [2. Getting Started with Hadoop](#2-getting-started-with-hadoop)
- [3. HDFS Architecture](#3-hdfs-architecture)
- [4. Map Reduce](#4-map-reduce)
- [5. YARN (MapReduce - 2)](#5-yarn--mapreduce---2-)
- [6. Hive](#6-hive)
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

[Deteiled Hive Tutorial and scripts](https://github.com/AraSwain/Hadoop/tree/master/HIVE)

## 7. PIG

## 8. SQOOP

## 9. HBase

## 10. Oozie

## Installation and Setup (with Ubuntu 16.04 OS)

### 1. Java Installation
  - To install JDK 8 in Ubuntu, run the below commands on console
    ```sh
    sudo apt-add-repository ppa:webupd8team/java
    sudo apt-get update
    sudo apt-get install oracle-java8-installer
    ```
  - Most of Java based application’s uses environment variables to work. Use following commands to set these environment variable. It’s also good to add following commands to the start-up script like `~/.bashrc` or `~/.bash_profile`.
    ```sh
    export JAVA_HOME=/usr/lib/jvm/java-8-oracle
    export JRE_HOME=/usr/lib/jvm/java-8-oracle/jre
    
    export PATH=$PATH:$JAVA_HOME/bin:$JRE_HOME/bin
    ```

### 2. Hadoop Installation in Pseudo Distributed mode
Hadoop can also be run on a single-node in a pseudo-distributed mode where each Hadoop daemon runs in a separate Java process.
#### 2.1 Prerequisite
   - **Java™** must be installed.
        - If you have note intstalled Java, [click here to check java installation steps](https://github.com/AraSwain/Hadoop#1-java-installation)
   - **ssh** must be installed and sshd must be running to use the Hadoop scripts that manage remote Hadoop daemons.
        - If you have not installed ssh, then run the below commands
        ```sh
         $ sudo apt-get install ssh
	 $ sudo apt-get install rsync
        ```
#### 2.2 Add a User (Optional)
It is recommended to create a normal (not root) linux account for hadoop. So create a user account using following command. Run the below commands from a super/root user.
	```sh
	# adduser aravind  
	# passwd aravind
	```

#### 2.3 Setup passphraseless ssh
- To check if you can ssh to the localhost without a passphrase, run the below command:
	```sh
	$ ssh localhost
	```
- If you cannot ssh to localhost without a passphrase, run the following commands:
	```sh
	$ ssh-keygen -t rsa -P '' -f ~/.ssh/id_rsa
	$ cat ~/.ssh/id_rsa.pub >> ~/.ssh/authorized_keys
	$ chmod 0600 ~/.ssh/authorized_keys
	```
#### 2.4 Download and Unpack Hadoop package
To get a Hadoop distribution, download a recent stable release from one of the [Apache Download Mirrors](http://www.apache.org/dyn/closer.cgi/hadoop/common/)

I have downloaded and configured **hadoop-2.9.1**. If you want to install a different version of Hadoop, change the version in the commands accordingly.
	```sh
	$ wget http://redrockdigimark.com/apachemirror/hadoop/common/stable/hadoop-2.9.1.tar.gz
	$ tar -xzvf hadoop-2.9.1.tar.gz
	$ mv hadoop-2.9.1 /home/aravind/hadoop
	```
**NOTE** : *Here aravind is the linux user. If you have a different username, update it accordingly.*
#### 2.5 Set Environment Variables
- First we need to set environment variable used by hadoop. Edit ~/.bashrc file and append following values at end of file.
	```sh
	#Hadoop environment variable
 	export HADOOP_HOME=/home/aravind/hadoop //path where hadoop is installed
 	export HADOOP_INSTALL=$HADOOP_HOME
 	export HADOOP_MAPRED_HOME=$HADOOP_HOME
 	export HADOOP_COMMON_HOME=$HADOOP_HOME
 	export HADOOP_HDFS_HOME=$HADOOP_HOME
 	export YARN_HOME=$HADOOP_HOME
 	export HADOOP_COMMON_LIB_NATIVE_DIR=$HADOOP_HOME/lib/native
 	export PATH=$PATH:$HADOOP_HOME/sbin:$HADOOP_HOME/bin
	```
- Source the `.bashrc` file to take effect immediately. Run the below command
	```sh
	$ source ~/.bashrc
	```
- Set the Java path to be used for the Hadoop. Edit the file `$HADOOP_HOME/etc/hadoop/hadoop-env.sh` and add the below parameters to the file. (or update if already exists.)
	```sh
	export JAVA_HOME=/usr/lib/jvm/java-8-oracle
	```
- Now run the below command, to test hadoop installation.
	```sh
	$ hadoop
	```
This will display the usage documentation for the hadoop script.

#### 2.6 Configuring HDFS
- Edit the `$HADOOP_HOME/etc/hadoop/core-site.xml` file and add the below property.
	```sh
	<configuration>
    		<property>
        		<name>fs.defaultFS</name>
        		<value>hdfs://localhost:9000</value>
    		</property>
	</configuration>
	```
- Edit the `$HADOOP_HOME/etc/hadoop/hdfs-site.xml` file and add the below property
	```sh
	<configuration>
    		<property>
        		<name>dfs.replication</name>
        		<value>1</value>
    		</property>
	</configuration>
	```
- Format the filesystem.
	```sh
	$ hdfs namenode -format
	```
- Start NameNode daemon and DataNode daemon
	```sh
	$ start-dfs.sh
	```
*The hadoop daemon log output is written to the $HADOOP_LOG_DIR directory (defaults to $HADOOP_HOME/logs).*
- Open the web interface for the NameNode. By default it is accessible at:
	```sh
	NameNode - http://localhost:50070/
	```

#### 2.7 Configuring YARN
- To configure YARN, edit the `$HADOOP_HOME/etc/hadoop/mapred-site.xml` file and add the below property.
	```sh
	<configuration>
    		<property>
        		<name>mapreduce.framework.name</name>
        		<value>yarn</value>
    		</property>
	</configuration>
	```
- And edit `$HADOOP_HOME/etc/hadoop/yarn-site.xml` file and add the below property.
	```sh
	<configuration>
    		<property>
        		<name>yarn.nodemanager.aux-services</name>
        		<value>mapreduce_shuffle</value>
    		</property>
	</configuration>
	```
- Start ResourceManager and NodeManager daemon.
	```sh
	$ start-yarn.sh
	```
- OPen the web interface for the ResourceManager. By default it is accessible at
	```sh
	ResourceManager - http://localhost:8088/
	```

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

