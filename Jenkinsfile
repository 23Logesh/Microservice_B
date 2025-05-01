pipeline {
    agent any

    parameters {
        string(name: 'DOCKER_TAG', defaultValue: 'latest', description: 'Docker Image Tag')
    }

    environment {
        IMAGE_NAME = "23logesh/microservice_b:${params.DOCKER_TAG}"
    }

    stages {
        stage('Clone') {
            steps {
        git branch: 'main', url: 'https://github.com/23Logesh/Microservice_B.git'
    }
        }

        stage('Build') {
            steps {
                sh './mvnw clean package -DskipTests'
            }
        }

        stage('Docker Build') {
            steps {
                sh "docker build -t ${IMAGE_NAME} ."
            }
        }

        stage('Docker Push') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'docker-hub-creds', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    sh 'echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin'
                    sh "docker push ${IMAGE_NAME}"
                }
            }
        }

        stage('Clean Workspace') {
            steps {
                cleanWs()
            }
        }
    }

    post {
        success {
            build job: 'CD-Microservice-B', parameters: [string(name: 'DOCKER_TAG', value: "${params.DOCKER_TAG}")]
        }
    }
}
