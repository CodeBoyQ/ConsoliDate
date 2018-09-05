#!/bin/bash

# ConsoliDate
# This script takes a folderPath as parameter and runs the ConsoliDate application

clear 

cd ${0%/*}
java -jar "consoliDateApp.jar" "$1"