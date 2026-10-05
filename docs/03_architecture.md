# Task 3 — Architecture

```text
Browser
   |
   v
Spring Boot + Thymeleaf
   |
   +----> Service Layer
   |          |
   |          v
   |      JPA Repository
   |          |
   |          v
   |         MySQL
   |
   +----> Health Endpoint

GitHub -> Jenkins -> Maven/Test -> Docker -> Docker Hub -> Deployment
                                           |
                                           v
                                         Ansible
```

## Main data entities
- Book
- BookIssue

## Main endpoints
- GET /
- GET /books
- GET /books/new
- POST /books/save
- GET /books/edit/{id}
- GET /books/delete/{id}
- GET /issue
- POST /issue
- GET /issues
- POST /return/{id}
- GET /health
