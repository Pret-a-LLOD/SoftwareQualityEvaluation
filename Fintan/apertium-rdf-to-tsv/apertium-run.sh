#!/bin/bash

HERE=`echo $0 | sed -e s/'^[^\/]*$'/'.'/g -e s/'\/[^\/]*$'//`;
ROOT=$HERE/../../..;


START=$(date +%s)

sudo sh ./run.sh -c apertium-pipeline.json -p https://github.com/apertium/apertium-trunk.git en es >>apertium-RDF-to-TSV-output.tsv

END=$(date +%s)
DIFF=$(( $END - $START ))
echo "ApertiumRDF to ApertiumTSV: $DIFF seconds"
