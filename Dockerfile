FROM java:8
VOLUME /tmp
ADD greateapp.jar app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 8031
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]