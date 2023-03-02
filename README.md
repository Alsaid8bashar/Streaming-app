Project Description
This project consists of multiple microservices that work together to provide a web application for uploading and streaming videos. The services are deployed using Docker containers and are orchestrated using Docker Compose.


•	The microservices included in the project are:
mysqldb-service: A MySQL database used to store information about users and their videos.
authentication-service: An authentication service that handles user registration and login.
upload-service: A service that allows users to upload videos to the system.
google-cloud-storage-service: A service that interacts with Google Cloud Storage to store uploaded videos.
streaming-service: A service that provides a streaming API for playing videos.
front-end-service: A web application that interacts with the other services to provide a user interface.



•	Running the Application
To run the application, you will need Docker and Docker Compose installed on your system.

1.	Clone the repository to your local machine.
2.	Navigate to the root directory of the project in a terminal.
3.	Run the command docker-compose up to start all of the services.
4.	Once the services are running, you can access the web application by navigating to http://localhost:8080 in a web browser.


•	Service Details
Each service has its own Dockerfile, which is used to build the Docker image for that service. The services are defined in the docker-compose.yml file.

1.	mysqldb-service
This service sets up a MySQL database and initializes it with the necessary tables and data. The database data is stored in a Docker volume, which persists between container restarts.

2.	authentication-service
This service provides an HTTP API for user registration and login. It communicates with the mysqldb-service to store user information.

3.	upload-service
This service provides an HTTP API for uploading videos. It communicates with the mysqldb-service and google-cloud-storage-service to store video metadata and uploaded video files, respectively.

4.	google-cloud-storage-service
This service provides an interface for interacting with Google Cloud Storage. It is used by the upload-service to store uploaded video files.


5.	streaming-service
This service provides an HTTP API for streaming videos. It communicates with the mysqldb-service and google-cloud-storage-service to retrieve video metadata and video files, respectively.

6.	front-end-service
This service provides a web application that allows users to interact with the other services. It communicates with the authentication-service, upload-service, google-cloud-storage-service, and streaming-service to provide a complete user interface.
