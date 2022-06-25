#!/bin/bash

HERE=`echo $0 | sed -e s/'^[^\/]*$'/'.'/g -e s/'\/[^\/]*$'//`;
ROOT=$HERE/../../..;

START=$(date +%s)

sudo sh ./run.sh -c conll-small-pipeline.json >> conll-rdf-small-output-actual.ttl

END=$(date +%s)
DIFF=$(( $END - $START ))
echo "conll to rdf(small): $DIFF seconds"

START=$(date +%s)

sudo sh ./run.sh -c conll-large-pipeline.json >> conll-rdf-large-output-actual.ttl

END=$(date +%s)
DIFF=$(( $END - $START ))
echo "conll to rdf(large): $DIFF seconds"



