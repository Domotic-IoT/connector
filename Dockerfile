# Uses latest official MongoDB image
FROM       mongo:latest

# Installs dependencies
RUN apt update && apt install -y mosquitto openjdk-17-jre

# Copies application files
COPY target/IoTConnector-1.0-jar-with-dependencies.jar /app/IoTConnector.jar
COPY src/resources/config/docker-config.properties /app/config.properties
COPY src/resources/classifier.dat /app/classifier.dat

# Sets entry point and start services
ENTRYPOINT service mosquitto start && /bin/mongod --bind_ip_all & sleep 5 && java -jar /app/IoTConnector.jar /app/config.properties
