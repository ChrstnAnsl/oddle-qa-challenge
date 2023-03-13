#!/usr/bin/env bash

echo "Checking if selenium server exist"

if ! [ -e selenium-server.jar ]; then
    printf "${red}Selenium doesn't exist\n"  
    printf "${yellow}Downloading selenium..\n"
    curl -L -o ./selenium-server.jar https://github.com/SeleniumHQ/selenium/releases/download/selenium-4.8.0/selenium-server-4.8.1.jar
else
  echo "selenium server exist, skipping download"
fi

echo ""

java -jar selenium-server.jar hub
