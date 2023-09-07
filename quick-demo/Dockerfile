FROM hub.tebonlocal.cn/common/openjdk:11.0.3-slim-fonts
VOLUME /tmp
WORKDIR /home
ENV TZ=Asia/Shanghai
RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
ADD target/dap-push-api.jar ./
ENTRYPOINT java $JAVA_OPTS -jar dap-push-api.jar $ARGS --spring.profiles.active=$PROFILE
