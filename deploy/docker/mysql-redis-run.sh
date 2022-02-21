mysqlDir=$1
if $# -ne 1;then
  echo "Usage: $0 mysqlDir"
  exit 1
fi

network="material-net"
# tell if docker network 'material-net' exists
netExists=$(docker network ls | grep $(network))
if [ -z "$netExists" ];then
  echo "Create docker network ${network}"
  docker network create ${network}
else
  echo "Docker network ${network} exists"
fi


# mysql 
docker run -itd --name material-mysql --network ${network} -e MYSQL_ROOT_PASSWORD=123456 \
-v ${mysqlDir}:/var/lib/mysql mysql
# redis
docker run -itd --name material-redis --network ${network} redis

