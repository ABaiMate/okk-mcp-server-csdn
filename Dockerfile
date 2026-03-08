# 基础镜像，可以先执行 docker pull openjdk:17-jdk-slim
# 基础镜像：切换至龙蜥社区张家口节点 (解决杭州节点 DNS 解析失败问题)
FROM anolis-registry.cn-zhangjiakou.cr.aliyuncs.com/openanolis/openjdk:17-8.6

# 作者
MAINTAINER abai

# 配置
ENV PARAMS=""

# 时区
ENV TZ=PRC
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# 添加应用
ADD target/mcp-server-csdn-app.jar /mcp-server-csdn-app.jar

# 启动命令
ENTRYPOINT ["sh","-c","java -jar $JAVA_OPTS /mcp-server-csdn-app.jar $PARAMS"]
# 作者
MAINTAINER abai

# 配置
ENV PARAMS=""

# 时区
ENV TZ=PRC
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# 添加应用
ADD target/mcp-server-csdn-app.jar /mcp-server-csdn-app.jar

ENTRYPOINT ["sh","-c","java -jar $JAVA_OPTS /mcp-server-csdn-app.jar $PARAMS"]