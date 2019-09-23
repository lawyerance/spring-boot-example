#/bin/bash

SOURCE_PATH=$(cd `dirname $BASH_SOURCE`;pwd)
export PROJECT_BASE_PATH=${SOURCE_PATH%/*}

echo "The path of the program is: $PROJECT_BASE_PATH"

cd $PROJECT_BASE_PATH

java -jar -Djava.ext.dirs=./lib spring-boot-example.jar
