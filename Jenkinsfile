pipeline {
    agent any

    environment {
        IMAGE_NAME = "YOUR_DOCKER_USERNAME/devops-project"
        IMAGE_TAG = "${BUILD_NUMBER}"
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

        stage('Docker Build') {
            steps {
                bat 'docker build -t %IMAGE_NAME%:%IMAGE_TAG% .'
            }
        }

        stage('Docker Push') {
            steps {
                echo 'Configure Docker Hub credentials in Jenkins before enabling push.'
                // withCredentials([usernamePassword(credentialsId: 'dockerhub',
                // usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                //     bat 'docker login -u %DOCKER_USER% -p %DOCKER_PASS%'
                //     bat 'docker push %IMAGE_NAME%:%IMAGE_TAG%'
                // }
            }
        }

        stage('Deploy') {
            steps {
                echo 'Deployment stage placeholder. Enable after Docker Hub credentials and target server are configured.'
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
        }
    }
}
