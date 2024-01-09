Distributed-systems-project

Start mysql database as a container: 
docker run -d --name mysql-farmers -e MYSQL_ROOT_PASSWORD=pass123 -e MYSQL_DATABASE=farmers -p 3306:3306 mysql