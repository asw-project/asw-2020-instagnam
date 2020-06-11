#!/bin/bash
#Start docker-compose

docker-compose up --scale ricette=2 --scale connessioni=2 --scale ricette-seguite=2
echo Docker compose infrastructure is up and running. 