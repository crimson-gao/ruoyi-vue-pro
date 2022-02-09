# mysql 
mysqlDir=/home/crimson/mysql-data
docker run -itd --name material-mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=123456 \
-v ${mysqlDir}:/var/lib/mysql mysql

docker run -itd --name material-redis -p 6379:6379 redis

