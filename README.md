# Devops_Project

## Dockerized Book Issue and Return System

A web-based library management application developed using Spring Boot and MySQL. This project demonstrates DevOps practices, including version control, build automation, testing, containerization, deployment, and configuration management.

### Project Links

- **GitHub Repository:** https://github.com/swar071005/Devops_Project
- **Live Application:** [Click here to open the Book System](https://cuddly-computing-machine-gxq4w77v7643pw6v-8080.app.github.dev/)

## Technology Stack

- Java 21
- Spring Boot
- Maven
- Thymeleaf
- MySQL
- Git and GitHub
- Jenkins
- Selenium
- Docker and Docker Compose
- Docker Hub
- Ansible
- Nginx (deployment stage)

## Current MVP Features

1. Dashboard
2. Add book
3. View books
4. Update book
5. Delete book
6. Search books by title or author
7. Issue book
8. Return book
9. Issue and return history
10. Health endpoint: `/health`

## Prerequisites

Install or configure the following tools:

- Java 21
- Apache Maven
- MySQL 8.x
- Git
- Docker
- Docker Compose

Verify the installations:

```bash
java -version
mvn -version
docker --version
docker compose version

Running Locally
1. Clone the Repository
git clone https://github.com/swar071005/Devops_Project.git
cd Devops_Project


2. Create the Database
Log in to MySQL and execute:
CREATE DATABASE book_system;


3. Configure MySQL
Open src/main/resources/application.properties and verify the database connection settings.
Example configuration:
spring.datasource.url=jdbc:mysql://localhost:3306/book_system
spring.datasource.username=root
spring.datasource.password=YOUR_MYSQL_PASSWORD


Replace YOUR_MYSQL_PASSWORD with your actual MySQL password. Do not commit real credentials to GitHub.
4. Build and Test
mvn clean test


5. Run the Application
mvn spring-boot:run


Open the application:
- Home page: http://localhost:8080/
- Health check: http://localhost:8080/health
Building the JAR
Build the application:
mvn clean package


The generated JAR file will be available in the target/ directory.
To package without running tests:
mvn clean package -DskipTests


Running with Docker
1. Build the Docker Image
docker build -t devops-project:v1 .


2. Run the Container
Example for Docker Desktop with MySQL running on the host:
docker run -d \
  --name devops-book-system \
  -p 8080:8080 \
  -e DB_URL=jdbc:mysql://host.docker.internal:3306/book_system \
  -e DB_USERNAME=root \
  -e DB_PASSWORD=YOUR_MYSQL_PASSWORD \
  devops-project:v1


Ensure that these environment variable names match the application's configuration.
Check the container:
docker ps
docker logs devops-book-system


Stop and remove the container:
docker stop devops-book-system
docker rm devops-book-system


Running with Docker Compose
1. Build and Start Services
docker compose up --build -d


2. Check Container Status
docker compose ps


3. View Logs
docker compose logs --tail=100


Follow application logs:
docker compose logs -f app


Replace app with the application service name defined in docker-compose.yml, if different.
4. Verify the Application
curl -i http://localhost:8080/health


5. Stop Services
docker compose down


This does not intentionally delete persistent database volumes.
Running in GitHub Codespaces
1. Open the Project Directory
cd /workspaces/Devops_Project


2. Check the Environment
java -version
mvn -version
docker --version
docker compose version


3. Build and Test
mvn clean test


4. Start the Application and Database
docker compose up --build -d


5. Verify Services
docker compose ps
docker compose logs --tail=100


Test the health endpoint:
curl -i http://localhost:8080/health


Open the Ports tab in Codespaces and access the forwarded URL for port 8080 after the application starts successfully.
DevOps Task Mapping
Task	Description
1	Problem Definition and Scope
2	Agile Planning and DevOps Workflow
3	Requirements, Architecture and Technology Setup
4	Git/GitHub Repository Initialization
5	Feature Development with Branching
6	MVP Completion and Git Collaboration
7	Jenkins Installation and CI Job
8	Pipeline as Code and Server Deployment
9	Selenium Test Design and Local Execution
10	Continuous Testing in Jenkins
11	Docker Image and Container Lifecycle
12	Jenkins-Docker Continuous Deployment
13	Configuration Management using Ansible
14	Automated Provisioning and Reliability Validation
Jenkins and Docker Hub Configuration
Before enabling Docker image publishing:
1. Replace YOUR_DOCKER_USERNAME in the relevant Jenkins configuration.
2. Configure Docker Hub credentials securely in Jenkins.
3. Verify Docker access from the Jenkins environment.
4. Run the pipeline and confirm that the image is published successfully.
Do not store passwords or access tokens directly in the repository.
Project Evidence
Maintain screenshots, logs, and reports for:
- Application dashboard and CRUD operations
- Book issue and return
- Git branches and commit history
- Pull requests and code reviews
- Merge conflict resolution
- Maven build and test results
- Jenkins successful and failed builds
- Selenium test reports
- Docker image and running container
- Docker Hub image publication
- Ansible execution
- Health checks
- Recovery or rollback demonstration
Troubleshooting
Maven Build Failure
mvn clean test


Review the error output and verify Java and Maven versions.
Docker Container Failure
docker compose ps
docker compose logs --tail=100


Database Connection Failure
Verify the database service, connection URL, credentials, port, and network configuration.
Port 8080 Is Unavailable
Check whether another process or container is using port 8080.
Important: Avoid destructive Docker cleanup commands or deleting database volumes during troubleshooting unless you have verified backups and intend to remove the stored data.
Future Enhancements
- Role-based authentication and authorization
- Book reservation functionality
- Fine management
- Email notifications
- Advanced reporting and analytics
- Improved deployment automation and monitoring
Conclusion
The Dockerized Book Issue and Return System demonstrates how web application development can be integrated with DevOps practices. It combines library-management functionality with version control, automated builds and tests, containerization, deployment, and configuration management.
