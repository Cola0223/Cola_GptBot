# 基础镜像
FROM openjdk:8-jre-slim
# 作者
MAINTAINER Cola
# 配置
ENV PARAMS=""
# 时区
ENV TZ=PRC
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
# 添加应用
ADD ./chatbot-api-interfaces/target/Cola_GptBot.jar ./Cola_GptBot.jar
EXPOSE 8080

# 执行镜像；docker run -e
# PARAMS=" --chatbot-api.groupId=你的星球ID
# --chatbot-api.openAiKey=自行申请
# --chatbot-api.cookie=登录cookie信息
# --Cola.PROXY_HOST=代理地址
# --Cola.PROXY_PORT=代理端口
# "
#-p 8080:8080 --name Cola_GptBotL_8080 -d cola-gpt-bot:1.0

ENTRYPOINT ["sh","-c","java -jar $JAVA_OPTS /Cola_GptBot.jar $PARAMS"]