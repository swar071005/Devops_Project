# Devops_Project

## Dockerized Book Issue and Return System

A Spring Boot + MySQL application designed as an end-to-end DevOps academic project.

### Technology Stack

- Java 21
- Spring Boot
- Maven
- Thymeleaf
- MySQL
- Git/GitHub
- Jenkins
- Selenium
- Docker
- Docker Hub
- Ansible
- Nginx (deployment stage)

### Current MVP Features

1. Dashboard
2. Add book
3. View books
4. Update book
5. Delete book
6. Search books by title/author
7. Issue book
8. Return book
9. Issue/return history
10. Health endpoint: `/health`

### Run locally

#### 1. Create database

```sql
CREATE DATABASE book_system;
```

#### 2. Configure MySQL

Edit `src/main/resources/application.properties` if your MySQL username/password differ from the defaults.

#### 3. Run

```bash
mvn clean spring-boot:run
```

Open:

```text
http://localhost:8080
```

Health check:

```text
http://localhost:8080/health
```

### Build JAR

```bash
mvn clean package
```

JAR:

```text
target/devops-project.jar
```

### Docker

Build:

```bash
docker build -t devops-project:v1 .
```

Run:

```bash
docker run -d --name devops-book-system -p 8080:8080 ^
  -e DB_URL=jdbc:mysql://host.docker.internal:3306/book_system ^
  -e DB_USERNAME=root ^
  -e DB_PASSWORD=root ^
  devops-project:v1
```

### Docker Compose

```bash
docker compose up --build
```

### DevOps task mapping

| Task | Implementation |
|---|---|
| 1 | Problem definition |
| 2 | Agile backlog and workflow |
| 3 | Architecture and database |
| 4 | Git/GitHub |
| 5 | Feature branch and pull request |
| 6 | MVP, merge conflict, release tag |
| 7 | Jenkins CI |
| 8 | Jenkinsfile/CD |
| 9 | Selenium |
| 10 | Jenkins continuous testing |
| 11 | Docker |
| 12 | Docker Hub + Jenkins CD |
| 13 | Ansible |
| 14 | Provisioning + idempotency |
| 15 | Health check + rollback |

### Important

Before using Jenkins Docker push, replace:

```text
YOUR_DOCKER_USERNAME
```

in `Jenkinsfile` with your Docker Hub username and configure Docker Hub credentials in Jenkins.

### Project evidence

Keep screenshots/logs for:

- Application dashboard
- CRUD operations
- Issue and return
- Git branches
- Pull request
- Merge conflict and resolution
- Jenkins successful/failed builds
- Selenium results
- Docker image/container
- Docker Hub
- Ansible execution
- Health check
- Rollback

## Git Branching Strategy

- `main` - Stable and release-ready code.
- `develop` - Integration branch for completed features.
- `feature/<name>` - Used for developing new features.
- `bugfix/<name>` - Used for fixing defects.
- `hotfix/<name>` - Used for urgent production fixes.

Feature and bugfix branches are merged into `develop` after review.
Release-ready changes are merged into `main`.