Build and run

```
docker build -t darknet .

docker run --rm -it -v $(pwd)/data/:/home/data --name darknet darknet
```

Inside docker container shell

```
./darknet detect cfg/yolov3.cfg yolov3.weights /home/data/sample.jpg

mv ./predictions.jpg /home/data/
```
