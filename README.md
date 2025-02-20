# Decathlon App

## Description : 
Simple CRUD application for record your points turing different sport events.

## Configurations :
Backend : 
- language: Java-17
- port: 8000
- directory: decathlon-api
    
Frontend: 
- framework: Vue
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

> ALLOWED EVENT TYPES [ passed as **eventName** in requests ]:
```
  1. HUNDRED_METERS
  2. LONG_JUMP
  3. SHOT_PUT
  4. HIGH_JUMP
  5. FOUR_HUNDRED_METERS
  6. HUNDRED_TEN_METERS_HURDLES
  7. DISCUS_THROW
  8. POLE_VAULT
  9. JAVELIN_THROW
  10. FIFTEEN_HUNDRED_METERS
```


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