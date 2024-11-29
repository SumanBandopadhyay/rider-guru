pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "sumanbando/rider-guru"
        DOCKER_TAG = "latest"
        DOCKER_REGISTRY = "https://index.docker.io/v1/"
        AWS_EC2_PUBLIC_IP = "YOUR_EC2_PUBLIC_IP"
        AWS_SSH_KEY = "your-ssh-key.pem"
        DOCKER_HUB_USERNAME = credentials('DOCKER_HUB_CREDENTIALS')
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/SumanBandopadhyay/rider-guru.git'
            }
        }

        stage('Build') {
            steps {
                sh './mvnw clean package -DskipTests'
            }
        }

        stage('Docker Build') {
            steps {
                script {
                    sh """
                    docker build -t ${DOCKER_IMAGE}:${DOCKER_TAG} .
                    """
                }
            }
        }

        stage('Docker Push') {
            steps {
                script {
                    docker.withRegistry(${DOCKER_REGISTRY}, 'DOCKER_HUB_CREDENTIALS') {
                        sh 'docker push ${DOCKER_IMAGE}:${DOCKER_TAG}'
                    }
                }
            }
        }

        stage('Deploy to AWS EC2') {
            steps {
                script {
                    sh """
                    ssh -o StrictHostKeyChecking=no -i ${AWS_SSH_KEY} ec2-user@${AWS_EC2_PUBLIC_IP} << EOF
                        docker pull ${DOCKER_IMAGE}:${DOCKER_TAG}
                        docker stop rider-guru || true
                        docker rm rider-guru || true
                        docker run -d --name rider-guru -p 8080:8080 ${DOCKER_IMAGE}:${DOCKER_TAG}
                    EOF
                    """
                }
            }
        }
    }

    post {
        always {
            cleanWs()
        }
    }
}
