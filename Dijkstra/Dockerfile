FROM openjdk:19-alpine
COPY . /tmp
WORKDIR /tmp
ENV DISPLAY :99
ENV RESOLUTION 1366x768x24
ENTRYPOINT java -cp mssql-jdbc-11.2.0.jre18.jar:JDiagram.jar:target/classes huce.View.MainApp