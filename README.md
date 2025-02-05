# Ticket sales platform server using OpenAPI and MongoDB

## Client App
- This API server works together with the client App found at [GitHub - Entradas](https://github.com/Bricomaniaco-js/Entradas).


## Supported versions:

- Java 21
- Spring boot 3.2.2
- MongoDB 7.0
- MongoDB Java driver 4.11.1
- Maven 3.8.7
- OpenAPI 3


## API documentation and Swagger

- The Swagger UI can be seen at [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html).
- https://localhost:8443/swagger-ui.html Or here if set up for HTTPS
- Here you can see the different API endpoints and test the API directly from the browser.

## Example API Calls

```bash
curl -X curl -X 'GET' \
  'http://localhost:8080/api/events/getEvents' \
  -H 'accept: */*'
```

```bash
curl -X 'POST' \
  'http://localhost:8080/api/events/user/AddEvent' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "id": "string",
  "name": "string",
  "description": "string",
  "tickets": [
    {
      "id": "string",
      "eventId": "string",
      "userId": "string",
      "valid": true
    }
  ],
  "images": [
    "string"
  ],
  "capacity": 0,
  "price": 0,
  "location": "string",
  "date": "string"
}'
