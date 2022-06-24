#!/bin/bash

HERE=`echo $0 | sed -e s/'^[^\/]*$'/'.'/g -e s/'\/[^\/]*$'//`;
ROOT=$HERE/../../..;

set startTime=%time%

sudo sh ./run.sh -c conll-small-pipeline.json >> conll-rdf-small-output-actual.ttl

sudo sh ./run.sh -c conll-large-pipeline.json >> conll-rdf-large-output-actual.ttl

echo Start Time: %startTime%
echo Finish Time: %time%


