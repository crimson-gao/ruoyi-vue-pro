image=$1
if $# -ne 1;then
  echo "Usage: $0 image"
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

docker run -itd --name material-spring --network ${network} ${image}