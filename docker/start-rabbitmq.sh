#!/usr/bin/env bash
docker-compose stop && docker-compose rm -f && docker-compose up -d
sleep 5
curl -i -u guest:guest -H "content-type:application/json" -XPUT -d'{"type":"direct","durable":false}' http://192.168.99.100:15672/api/queues/%2f/fint:hfk.no:employee:in