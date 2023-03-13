#!/usr/bin/env bash

status=\"$(curl -L -s -o /dev/null -I -w %{http_code} http://localhost:4444/)\"

if [[ ${status//\"/} != "200" ]]; then
    echo "Selenium hub is not working, Please restart the node"
else
    echo "Starting selenium node as default.."
fi

java -jar selenium-server.jar node --detect-drivers true
