## Запросы для второго сервиса:

Получение списка групп:
```shell
curl --request GET \
  --url https://localhost:21571/jax-rs-2/killer/teams
```
Создание (обновление группы (ТЗ 1ой лабы)):
```shell
curl --request POST \
  --url https://localhost:21571/jax-rs-2/killer/teams/create/1/first/5/1 \
  --header 'Content-Type: application/json' \
  --data '[1, 2]'
```
Перемещение группы
```shell
curl --request POST \
  --url https://localhost:21571/jax-rs-2/killer/teams/1/move-to-cave/2
```
Получение списка пещер:
```shell
curl --request GET \
  --url https://localhost:21571/jax-rs-2/killer/caves
```
Создание пещеры:
```shell
curl --request POST \
  --url https://localhost:21571/jax-rs-2/killer/caves \
  --header 'Content-Type: application/json' \
  --data '{
	"coordinates" : {
		"x" : "2",
		"y" : "1"
	}
}'
```
Удаление всех групп:
```shell
curl --request DELETE \
  --url https://localhost:21571/jax-rs-2/killer/teams/deleteAll
```
Удаление всех пещер:
```shell
curl --request DELETE \
  --url https://localhost:21571/jax-rs-2/killer/caves/deleteAll
```