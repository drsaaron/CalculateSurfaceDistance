#! /bin/sh

echo "Boston to Milwaukee: "
curl -H 'Content-Type: application/json' -X POST -d '{ "firstCoordinate": { "latitude": 43.0389, "longitude": -87.9065 }, "secondCoordinate": { "latitude": 42.3601, "longitude": -71.0589 }, "unit": "miles"}' http://localhost:9080/distance

echo
echo "Brandeis to Marquette: "
curl -H 'Content-Type: application/json' -X POST -d '{ "firstCoordinate": { "latitude": 43.039167, "longitude": -87.9325 }, "secondCoordinate": { "latitude": 42.365664, "longitude": -71.259742 }, "unit": "kilometers"}' http://localhost:9080/distance

echo
