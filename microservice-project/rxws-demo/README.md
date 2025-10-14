# rest-app-tomcat

## Overview
This module provides the main web application entry point for the RxWS Demo project.  
It acts as a Tomcat-deployable web application (WAR) that integrates the REST client and server modules.

## Responsibilities
- Exposes the main REST API endpoints via Tomcat.
- Connects to the `rest-server-service` module to process requests.
- Uses `shared-dto` for common data transfer objects.

## Dependencies
- `rest-client-tomcat`
- `rest-server-service`
- `shared-dto`

## Build and Deployment
To build the WAR file:
```bash
mvn clean package
