To start the application you need Java 8+

Application is a Spring Boot REST API written with Jersey.

You'll need to run the app (maybe specify starting class if needed).

Database is PostgreSQL as requested. You'll need to change .yml file's properties to correspond your PostgreSQL database.
You have 2 scenarios to create the database with filled in data:
1. In .yml file hbm2ddl.auto = create and import.sql script is also included in the resource folder, so if you don't change this property, you can just create a database (car) the rest of the database (tables, constraints, inserts) will be ready once you run the project.
2. Alternatively database dump file is also included in the project with initial inserts as requested, you can also create the database with this scenario (but don't forget to set hbm2ddl.auto to empty or auto instead of create and comment import.sql script).

For roles restrictions a VERY DUMMY annotation is implemented with just the user's login in the header
(in the real world example this should be a Token which would've been updated, checked, etc but as this is not the main part of the project I've just implemented a proof of concept that different calls have different access levels depending on the user role).

After running insert scripts (or project if you follow scenario 1) you'll be able to create a car (see curl script for ease)
curl -X POST \
  http://localhost:8989/api/car \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'login: user' \
  -d '{
	"brandId":1,
	"modelId": 2,
	"colorId": 3,
	"year":2013,
	"price":8000,
	"features": [
		{
		"featureId":1,
		"valueId":1
		},
		{
		"featureId":2,
		"valueId":3
		}
		]
}'

After that you can get car listing (see curl code below):
curl -X GET \
  http://localhost:8989/api/car \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json'


Admin users can also add/update features (see below curl scripts)
curl -X POST \
  http://localhost:8989/api/feature \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'login: admin' \
  -d '{
	"name":"zzz",
	"main": "false",
	"values": [
	"FICTION",
		"DETECTIVE",
		"DRAMA",
		"NOVEL"
		]
}'

curl -X PUT \
  http://localhost:8989/api/feature \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'login: admin' \
  -d '{
	"id":1,
	"name":"zzz",
	"main": "false",
	"values": [
		{
			"value":"Robot"
		}
		]
}'