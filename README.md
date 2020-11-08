# Test task
uniq values counter

sbt assembly 
docker build -t counter .
docker run  -p 8080:8080 -d counter