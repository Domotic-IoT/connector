# Connector ![](https://github.com/kolorobot/spring-boot-junit5/workflows/tests/badge.svg)
Connector for IoT devices

## Overview
IoT connector is a server application for aggregating and storing information from sensors, making it searchable and offering recommendation to actuators.

Connector subscribes to a set of predefined topics on a [MQTT](https://en.wikipedia.org/wiki/MQTT) broker in the form `sensors/<roomIdentifier>/<measureType>`, expecting to receive environmental measures in the form

```JSON
{
    "deviceIdentifier": "DHT11",
    "value": 24.5,
    "absoluteError": 0.5
}
```

Upon receiving, measures are decorated with type, room and timestamp and stored into a persistence medium. Currently only [MongoDB](https://www.mongodb.com/) is supported.

The connector also acts as a web server, exposing a REST API over HTTP to query measures by specifying room, type, and time span (see [Visualizer](https://github.com/Domotic-IoT/visualizer) for an HTML + JavaScript implementation of a data visualization tool).

The connector also periodically reads a room's state and produces a recommendation for an actuator by sending a message to the MQTT broker, using `actuators/<deviceId>` as topic and a value which depends on the type of actuator as message payload.

### Usage
The recommended way to run the IoT connector is by compiling sources into a single JAR file (see [Installation](#Installation)) to which supply a configuration file `config.properties`:

    java -jar target/IoTConnector-1.0-jar-with-dependencies.jar config.properties

a sample configuration file is available at [src/resources/config.properties.dummy](https://github.com/Domotic-IoT/connector/blob/main/src/resources/config/config.properties.dummy).

Alternatively, IoT connector can be run directly through Maven:

    mvn exec:java

which expects a configuration file at `src/resources/config.properties`.

## Requirements
- JDK 11 or later
- [Apache Maven](https://maven.apache.org/) 3.6 or later

## Installation
To generate a self-contained JAR file, run:

    mvn assembly:assembly

Additionally the following commands are also available:

	// Runs unit tests with JUnit
	mvn test
	
	// Generates documentation with JavaDoc
	mvn javadoc:javadoc