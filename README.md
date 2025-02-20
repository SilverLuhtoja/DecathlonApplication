# Decathlon App

## Description : 
Simple CRUD application for record your points turing different sport events.

## Configurations :
Backend : 
- language: Java-17
- port: 8000
- directory: decathlon-api
    
Frontend: 
- language: Vue
- port: 5173
- directory: decathlon-app

## Prerequisites :
1. Docker [install](https://docs.docker.com/engine/install/)
2. Docker-compose  [install](https://docs.docker.com/compose/install/)

## Usage:
Quick Start: Open a terminal in the project's root directory and run `./start.sh`
Command will create necessary containers for application to run.

## API ENDPOINTS:
All endpoints are prefixed with `/api`.

Running the application locally, the base URL will be: `http://localhost:8000/api`

> **[GET]:** `/api` = Retuns List of Events
```
Response[status=200]:
[
    {
        "eventType": "SHOT_PUT",
        "points": 
    }
]
```

> **[POST]:** `/api` = Creates or updates Event
```
Example:
Request Body:
[
    {
        "eventName": "SHOT_PUT",
        "points": 103
    }
]

Response[status=200]:
[
    {
        "eventType": "SHOT_PUT",
        "points": 
    }
]
```

> **[DELETE]:** `/api/delete/{eventName}` =  Delete record based on pathVariable
```
Request:
/api/delete/SHOT_PUT

Response [status=200]:
"Event : SHOT_PUT has been deleted."
```