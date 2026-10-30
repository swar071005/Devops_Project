pipeline {
    agent any

    environment {
        DOCKER = 'C:\\Users\\swars\\AppData\\Local\\Programs\\DockerDesktop\\resources\\bin\\docker.exe'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Unit Test') {
            steps {
                bat 'mvn clean test'
            }
        }

        stage('Package') {
            steps {
                bat 'mvn package -DskipTests'
            }
        }

        stage('Docker Environment Check') {
            steps {
                bat '"%DOCKER%" --version'
                bat '"%DOCKER%" compose version'
                bat '"%DOCKER%" info'
            }
        }

        stage('Build and Deploy') {
            steps {
                bat '"%DOCKER%" compose up -d --build'
            }
        }

        stage('Verify Deployment') {
            steps {
                bat '"%DOCKER%" compose ps'
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'target/*.jar',
                             fingerprint: true
        }
    }
}
