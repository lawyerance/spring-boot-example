#/bin/bash

BASE_PATH=`(cd |pwd $0)`

echo $BASE_PATH && cd ..

echo `pwd`

java -jar -Djava.ext.dirs=./lib spring-boot-example-1.0.0-SNAPSHOT-boot.jar
