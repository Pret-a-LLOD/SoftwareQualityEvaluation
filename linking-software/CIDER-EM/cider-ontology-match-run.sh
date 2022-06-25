#!/bin/bash

HERE=`echo $0 | sed -e s/'^[^\/]*$'/'.'/g -e s/'\/[^\/]*$'//`;
ROOT=$HERE/../../..;


START=$(date +%s)

curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET uri1="http://oaei.ontologymatching.org/2011/benchmarks/101/onto.rdf"&uri2="http://oaei.ontologymatching.org/2011/benchmarks/203/onto.rdf"

END=$(date +%s)
DIFF=$(( $END - $START ))
echo "cider entity matching: $DIFF seconds"

