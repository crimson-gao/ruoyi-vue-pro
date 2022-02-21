TAG?=latest
REPO?=""
dataDir?=/home/crimson/mysql-data_run

.PHONY: vue_build vue_image spring_image data_run spring_run vue_run

vue_build:
	cd ../yudao-ui-admin && npm run build:prod

vue_image: vue_build
	rm -rf vue/dist
	cp -rf ../yudao-ui-admin/dist vue/ 
	cd vue && docker build -t ${REPO}material-vue:${TAG} .


spring_image:
	cp ../yudao-server/target/yudao-server.jar spring/
	cd spring && docker build -t ${REPO}material-spring:${TAG} .

data_run:
	./docker/mysql-redis-run.sh ${dataDir}

spring_run:
	./spring/run.sh ${REPO}material-spring:${TAG}

vue_run:
	./vue/run.sh ${REPO}material-vue:${TAG} 8080

init_mysql:
	if [ ! -f sql/already-inited ]; then echo "mysql already inited!"; exit 1; fi
  touch sql/already-inited
	./sql/init-mysql.sh