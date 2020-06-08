#Start and build docker-compose

echo Docker compose infrastructure building in progress..

docker-compose -f docker-compose.yml build

docker-compose up -d

echo Docker compose infrastructure is up and running. 