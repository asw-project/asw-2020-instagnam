#!/bin/bash
# Script per cancellare i contenitori docker, soprattutto in caso di errore

docker image rm $(docker image ls | grep sentence | grep compose | awk '{print $3}')