FROM java:8

LABEL maintainer="yunzhi"

WORKDIR /pro/mt/

ADD classes /pro/mt/classes
ADD images /data/docker/website/images/
ADD confi /data/docker/website/confi/
ADD generated-sources /pro/mt/generated-sources
ADD maven-archiver /pro/mt/maven-archiver
ADD maven-status /pro/mt/maven-status
ADD website*.jar.original /pro/mt/website.jar.original
ADD website*.jar /pro/mt/website.jar

EXPOSE 8081

ENTRYPOINT [ "java","-jar","website.jar" ]