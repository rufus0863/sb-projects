
# rest-client-tomcat

## Overview
This module acts as the REST client within the RxWS Demo architecture.  
It is responsible for communicating with the REST server module using HTTP requests.

## Responsibilities
- Sends REST requests to the `rest-server-service`.
- Handles serialization and deserialization of DTOs.
- Provides utility classes for client-side communication.

## Dependencies
- `shared-dto` (for data transfer objects)
- Spring REST (for HTTP communication)

## Build
```bash
mvn clean install
