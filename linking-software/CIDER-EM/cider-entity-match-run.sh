#!/bin/bash

HERE=`echo $0 | sed -e s/'^[^\/]*$'/'.'/g -e s/'\/[^\/]*$'//`;
ROOT=$HERE/../../..;


START=$(date +%s)

curl -H "Accept: application/json" -H "Content-type: application/json"  --data-binary @entity-match-input.json -X POST  http://localhost:8001/align/entities

END=$(date +%s)
DIFF=$(( $END - $START ))
echo "cider entity matching: $DIFF seconds"

