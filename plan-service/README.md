### EndPoints For Plan Services

## Get all plan

***

    GET /api/v1/plans HTTP/1.1
    Host: localhost:8083
    Authorization: Basic cmFuZG9tU2VjdXJlS2V5VXNlcm5hbWUhOnJhbmRvbVNlY3VyZUtleVBhc3N3b3JkIQ==
    Content-Type: application/json
    Content-Length: 92

***

## Get plan by id

***

    GET /api/v1/plans/5 HTTP/1.1
    Host: localhost:8083
    Authorization: Basic cmFuZG9tU2VjdXJlS2V5VXNlcm5hbWUhOnJhbmRvbVNlY3VyZUtleVBhc3N3b3JkIQ==
    Content-Type: application/json
    Content-Length: 92

***

### Save plan

***
    POST /api/v1/plans HTTP/1.1
    Host: localhost:8083
    Authorization: Basic cmFuZG9tU2VjdXJlS2V5VXNlcm5hbWUhOnJhbmRvbVNlY3VyZUtleVBhc3N3b3JkIQ==
    Content-Type: application/json
    Content-Length: 92
    
    {
    "title": "lone",
    "subtitle": "This is for a single user",
    "price": 900.0
    }
***


## Update plan by id

***

    PUT /api/v1/plans/5 HTTP/1.1
    Host: localhost:8083
    Authorization: Basic cmFuZG9tU2VjdXJlS2V5VXNlcm5hbWUhOnJhbmRvbVNlY3VyZUtleVBhc3N3b3JkIQ==
    Content-Type: application/json
    Content-Length: 92
    
    {
    "title": "lone",
    "subtitle": "This is for a single user",
    "price": 900.0
    }

***
