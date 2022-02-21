
image=$1
if $# -ne 2;then
  echo "Usage: $0 image $1 port"
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

docker run -p $2:80 -itd --name material-vue ${image}
