#!/bin/bash

jps | awk '($2=="InnHttpServer"){print $1}' | xargs -I {} kill -15 {}
