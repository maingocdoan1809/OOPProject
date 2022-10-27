FROM openjdk:18
COPY ./out/production/Dijkstra/ /tmp
WORKDIR /tmp
ENTRYPOINT ["java","HelloWorld"]