
# rest-server-service

## Overview
This module implements the REST server-side services for the RxWS Demo application.  
It exposes RESTful endpoints and performs the core business logic.

## Responsibilities
- Provides server-side REST APIs.
- Contains service and repository logic.
- Uses `shared-dto` for data exchange with clients.

## Dependencies
- `shared-dto`
- Spring Boot / Spring MVC (for REST API exposure)

## Build and Run
To build:
```bash
mvn clean package
