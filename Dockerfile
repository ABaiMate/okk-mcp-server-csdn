# ==========================================
# 第一阶段：编译打包阶段
# ==========================================
FROM maven:3.8.5-openjdk-17-slim AS builder

# 设置工作目录
WORKDIR /build

# 复制 pom.xml 和源代码
COPY pom.xml .
COPY src ./src

# 执行 Maven 打包，跳过测试
RUN mvn clean package -DskipTests

# ==========================================
# 第二阶段：运行环境阶段
# ==========================================
# 基础镜像：切换至龙蜥社区张家口节点 (解决杭州节点 DNS 解析失败问题)
FROM anolis-registry.cn-zhangjiakou.cr.aliyuncs.com/openanolis/openjdk:17-8.6

# 作者信息 (MAINTAINER 已废弃，推荐使用 LABEL)
LABEL maintainer="abai"

# 环境变量配置
ENV PARAMS=""
ENV TZ=PRC
ENV JAVA_OPTS=""

# 设置时区
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# 从第一阶段 (builder) 复制打包好的 jar 文件到当前镜像
COPY --from=builder /build/target/mcp-server-csdn-app.jar /mcp-server-csdn-app.jar

# 启动命令
ENTRYPOINT ["sh","-c","java -jar $JAVA_OPTS /mcp-server-csdn-app.jar $PARAMS"]