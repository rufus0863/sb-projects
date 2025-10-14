# microservice rx-ws

## Overview
This module provides the main web application entry point for the RxWS Demo project.  
It acts as a Tomcat-deployable web application (WAR) that integrates the REST client and server modules.

## Responsibilities
- Exposes the main REST API endpoints via Tomcat.
- Connects to the `rest-server-service` module to process requests.
- Uses `rest-client-tomcat` module to process external requests.
- Uses `shared-dto` for common data transfer objects.

## Dependencies
- `rest-app-tomcat`
- `rest-client-tomcat`
- `rest-server-service`
- `shared-dto`

