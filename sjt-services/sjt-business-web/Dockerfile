FROM java:8
VOLUME /tmp
# 复制文件到容器
ADD target/sjt-business-web.jar sjt.jar
RUN bash -c "touch /sjt.jar"
# 定义端口
EXPOSE 27000
# 配置启动后执行的命令
ENTRYPOINT ["java","-jar","/sjt.jar","--spring.profiles.active=prod","-Xms256m","-Xmx256m"]
