#!/bin/bash

HERE=`echo $0 | sed -e s/'^[^\/]*$'/'.'/g -e s/'\/[^\/]*$'//`;
ROOT=$HERE/../../..;


sudo sh ./run.sh -c apertium-pipeline.json -p https://github.com/apertium/apertium-trunk.git en es >>apertium-RDF-to-TSV-output.tsv


