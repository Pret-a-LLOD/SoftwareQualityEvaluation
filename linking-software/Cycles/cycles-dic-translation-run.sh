#!/bin/bash

HERE=`echo $0 | sed -e s/'^[^\/]*$'/'.'/g -e s/'\/[^\/]*$'//`;
ROOT=$HERE/../../..;


START=$(date +%s)

curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://localhost:8001/translation/en-fr/dog

END=$(date +%s)
DIFF=$(( $END - $START ))
echo "cycels translation entity: $DIFF seconds"
