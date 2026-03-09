FROM openjdk:17-jdk-slim

LABEL maintainer="abai"

ENV PARAMS=""
ENV TZ=PRC
ENV JAVA_OPTS=""

RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

ADD dist/mcp-server-csdn-app.jar /mcp-server-csdn-app.jar

ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar /mcp-server-csdn-app.jar $PARAMS"]