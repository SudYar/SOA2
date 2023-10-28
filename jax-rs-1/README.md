### cURLs:

curl --request POST \
--url http://localhost:8080/jax-rs-1/api/v1/dragons \
--header 'Content-Type: application/json' \
--data '{
"name" : "Dragon",
"coordinates" : {
"x" : "1",
"y" : "1"
},
"age" : "3",
"color" : "WHITE",
"type" : "FIRE",
"character" : "WISE",
"killer" : "2"
}'

curl --request POST \
--url http://localhost:8080/jax-rs-1/api/v1/persons \
--header 'Content-Type: application/json' \
--data '{
"name" : "John",
"birthday" : "2002-06-27",
"height" : "180",
"passportID" : "3316 417701",
"hairColor" : "WHITE"
}'

curl --request GET \
--url http://localhost:8080/jax-rs-1/api/v1/dragons

curl --request GET \
--url http://localhost:8080/jax-rs-1/api/v1/persons

curl --request PUT \
--url http://localhost:8080/jax-rs-1/api/v1/dragons \
--header 'Content-Type: application/json' \
--data '{
"id" : "2",
"name" : "DragonNew",
"coordinates" : {
"x" : "1",
"y" : "1"
},
"age" : "3",
"color" : "WHITE",
"type" : "FIRE",
"character" : "WISE",
"killer" : "1"
}'

curl --request PUT \
--url http://localhost:8080/jax-rs-1/api/v1/persons \
--header 'Content-Type: application/json' \
--data '{
"id" : "1",
"name" : "Michile",
"birthday" : "2002-06-27",
"height" : "180",
"passportID" : "3316 417701",
"hairColor" : "WHITE"
}'

curl --request DELETE \
--url http://localhost:8080/jax-rs-1/api/v1/dragons/4

curl --request DELETE \
--url http://localhost:8080/jax-rs-1/api/v1/persons/2