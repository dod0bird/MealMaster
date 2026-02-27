#!/bin/sh
set -e
exec java -jar ~/Downloads/checkstyle-13.2.0-all.jar -c ./checkstyle.xml ./src/main
