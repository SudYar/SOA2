### cURLs:

```shell
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
```

```shell
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
```

```shell
curl --request GET \
--url http://localhost:8080/jax-rs-1/api/v1/dragons
```

```shell
curl --request GET \
--url http://localhost:8080/jax-rs-1/api/v1/persons
```

```shell
curl --request GET \
--url http://localhost:8080/jax-rs-1/api/v1/dragons/1
```

```shell
curl --request GET \
--url http://localhost:8080/jax-rs-1/api/v1/persons/1
```

```shell
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
```

```shell
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
```

```shell
curl --request DELETE \
--url http://localhost:8080/jax-rs-1/api/v1/dragons/4
```

```shell
curl --request DELETE \
--url http://localhost:8080/jax-rs-1/api/v1/persons/2
```

-----------------------------------------------------------------------

```shell
curl --request GET \
--url http://localhost:8080/jax-rs-1/api/v1/dragons/delete-killed \
--header 'Content-Type: application/json' \
--data '{
"id" : "1"
}'
```

```shell
curl --request GET \
--url http://localhost:8080/jax-rs-1/api/v1/dragons/delete-by-type \
--header 'Content-Type: application/json' \
--data '{
"value" : "AIR"
}'
```

```shell
curl --request GET \
--url http://localhost:8080/jax-rs-1/api/v1/dragons/get-by-max-color
```