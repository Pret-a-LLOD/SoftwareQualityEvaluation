#!/bin/bash

HERE=`echo $0 | sed -e s/'^[^\/]*$'/'.'/g -e s/'\/[^\/]*$'//`;
ROOT=$HERE/../../..;

set startTime=%time%

$ROOT/run.sh -c _demo-apertium-portable.json -p https://github.com/apertium/apertium-trunk.git en es

echo Start Time: %startTime%
echo Finish Time: %time%


