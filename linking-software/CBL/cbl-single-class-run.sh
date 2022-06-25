#!/bin/bash

HERE=`echo $0 | sed -e s/'^[^\/]*$'/'.'/g -e s/'\/[^\/]*$'//`;
ROOT=$HERE/../../..;


START=$(date +%s)

curl -H "Accept: application/json" -H "Content-type: application/json"  --data-binary @inputLex.json -X POST  http://localhost:8001/lexicalization


END=$(date +%s)
DIFF=$(( $END - $START ))
echo "otic indirect translation entity: $DIFF seconds"

