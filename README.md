## Project Details

This project utilizes the Traveling Salesman Problem (TSP) algorithm and the Google Maps Distance Matrix API to optimize path planning. It is built using various technologies including Exception Handler, Lombok, Swagger, and Postman.

### Google API Key

To use the Google Maps Distance Matrix API, make sure to add your API key in the `application.yml` file.

![apiKey.png](src%2Fmain%2Fresources%2Fimages%2FapiKey.png)

### Swagger Documentation

You can access the Swagger documentation for this project by visiting the following URL: [http://localhost:8090/swagger-ui/index.html#/](http://localhost:8090/swagger-ui/index.html#/)

Request:
`[
    {
        "id": 1,
        "latitude": 6.9355,
        "longitude": 79.8487
    },
    {
        "id": 2,
        "latitude": 6.9345,
        "longitude": 79.8462
    },
    {
        "id": 3,
        "latitude": 6.8806,
        "longitude": 79.8568
    },
    {
        "id": 4,
        "latitude": 6.9061,
        "longitude": 79.8646
    },
    {
        "id": 5,
        "latitude": 6.9104,
        "longitude": 79.8587
    },
    {
        "id": 6,
        "latitude": 6.9312,
        "longitude": 79.8465
    },
    {
        "id": 7,
        "latitude": 6.9093,
        "longitude": 79.8584
    },
    {
        "id": 8,
        "latitude": 6.9102,
        "longitude": 79.8526
    },
    {
        "id": 9,
        "latitude": 6.9179,
        "longitude": 79.8632
    },
    {
        "id": 10,
        "latitude": 6.9146,
        "longitude": 79.8591
    }
]`

response image(Swagger3): ![response.png](src%2Fmain%2Fresources%2Fimages%2Fresponse.png)
optimize path response(Swagger3):![optimize_path.png](src%2Fmain%2Fresources%2Fimages%2Foptimize_path.png)
`{
    "status": "SUCCESS",
    "msg": "Success",
    "data": {
        "locationResponseList": [
            {
                "stopSequenceNumber": 1,
                "startId": 1,
                "destinationId": 6,
                "distance": 953,
                "duration": 241
            },
            {
                "stopSequenceNumber": 2,
                "startId": 6,
                "destinationId": 9,
                "distance": 7890,
                "duration": 1392
            },
            {
                "stopSequenceNumber": 3,
                "startId": 9,
                "destinationId": 4,
                "distance": 4856,
                "duration": 722
            },
            {
                "stopSequenceNumber": 4,
                "startId": 4,
                "destinationId": 3,
                "distance": 2570,
                "duration": 343
            },
            {
                "stopSequenceNumber": 5,
                "startId": 3,
                "destinationId": 7,
                "distance": 3037,
                "duration": 523
            },
            {
                "stopSequenceNumber": 6,
                "startId": 7,
                "destinationId": 5,
                "distance": 3335,
                "duration": 586
            },
            {
                "stopSequenceNumber": 7,
                "startId": 5,
                "destinationId": 8,
                "distance": 1071,
                "duration": 222
            },
            {
                "stopSequenceNumber": 8,
                "startId": 8,
                "destinationId": 10,
                "distance": 2716,
                "duration": 552
            },
            {
                "stopSequenceNumber": 9,
                "startId": 10,
                "destinationId": 2,
                "distance": 1879,
                "duration": 313
            },
            {
                "stopSequenceNumber": 10,
                "startId": 2,
                "destinationId": 1,
                "distance": 4267,
                "duration": 835
            }
        ],
        "path": [
            1,
            6,
            9,
            4,
            3,
            7,
            5,
            8,
            10,
            2,
            1
        ],
        "totalDistance": 20880,
        "totalTime": 5729
    }
}`


### REFERENCES
Google Maps Distance Matrix API: [https://developers.google.com/maps/documentation/distance-matrix/overview](https://developers.google.com/maps/documentation/distance-matrix/overview)