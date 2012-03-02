#!/bin/bash

mvn clean install -Dmaven.test.skip=true && java -classpath "target/lib/*:target/*" gildedrose.InnHttpServer
