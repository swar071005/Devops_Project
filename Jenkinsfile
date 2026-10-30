
pipeline {
    agent any

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
                bat 'docker --version'
                bat 'docker compose version'
                bat 'docker info'
            }
        }

        stage('Build and Deploy') {
            steps {
                bat 'docker compose up -d --build'
            }
        }

        stage('Verify Deployment') {
            steps {
                bat 'docker compose ps'
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
