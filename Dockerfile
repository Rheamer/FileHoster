FROM gradle
ENV APP_HOME=/app/
WORKDIR $APP_HOME
COPY build.gradle settings.gradle $APP_HOME
COPY gradle $APP_HOME/gradle
COPY ./src $APP_HOME/src/
RUN gradle --no-daemon --warning-mode all --console=plain build
ENTRYPOINT ["gradle", "bootRun"]