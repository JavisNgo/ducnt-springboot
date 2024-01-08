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
                sh 'mvn --version'
                sh 'java -version'
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Packaging/Pushing image') {
            withDockerRegistry(credentialsId: 'dockerhub', url: 'https://index.docker.io/v1/') {
                sh 'docker build -t ducnt/springboot'
                sh 'docker push ducnt/springboot'
            }
        }

        stage('Deploy to DEV') {
            steps {
                echo 'Deploying...'
                sh 'docker -v'
                sh 'docker compose up -d'
            }
        }

    }

    post {
        always {
            cleanWs()
        }
    }
}