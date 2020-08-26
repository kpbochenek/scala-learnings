
## Running all

Download kafka:
https://www.apache.org/dyn/closer.cgi?path=/kafka/2.6.0/kafka_2.13-2.6.0.tgz

Start zookeeper service:
bin/zookeeper-server-start.sh config/zookeeper.properties

Start kafka service:
bin/kafka-server-start.sh config/server.properties

Create example kafka topic 'news'
bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic news

Topic can only be created once. Creating another topic with already existing name will throw error.

Executing Producer/Consumer via bloop:
bloop run apachekafka -m com.kpbochenek.apachekafka.KProducer
bloop run apachekafka -m com.kpbochenek.apachekafka.KConsumer


alias start_zookeeper="bin/zookeeper-server-start.sh config/zookeeper.properties"
alias start_kafka="bin/kafka-server-start.sh config/server.properties"
alias kafka_topic="bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic $1"

