pipeline {
    agent any

    tools {
        maven "my-maven"
    }
    environment {
        POSTGRES_ROOT_LOGIN = credentials('postgres-root-login')
    }
    stages {

        stage('Build with maven') {
            steps {
                bat 'mvn --version'
                bat 'java -version'
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Packaging/Pushing image') {
            withDockerRegistry(credentialsId: 'dockerhub', url: 'https://index.docker.io/v1/') {
                bat 'docker build -t ngotriduc/springboot'
                bat 'docker push ngotriduc/springboot'
            }
        }

        stage('Deploy to DEV') {
            steps {
                echo 'Deploying...'
                bat 'docker -v'
                bat 'docker compose up -d'
            }
        }

    }

    post {
        always {
            cleanWs()
        }
    }
}