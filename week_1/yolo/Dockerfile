FROM ubuntu:latest

RUN apt-get update && apt-get install -y \
    build-essential \
    git \
    wget \
    && rm -rf /var/lib/apt/lists/*

RUN git clone https://github.com/pjreddie/darknet.git --depth 1
WORKDIR /darknet
RUN make
RUN wget https://pjreddie.com/media/files/yolov3.weights

CMD ["bash"]