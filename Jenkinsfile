pipeline {
    agent any

    environment {
        PATH = "/usr/local/bin:$PATH"
        DOCKER_IMAGE = "sumanbando/rider-guru"
        DOCKER_TAG = "latest"
        DOCKER_REGISTRY = "https://registry-1.docker.io/v2/"
        AWS_EC2_PUBLIC_IP = "13.201.230.132"
        DOCKER_HUB_CREDENTIALS = credentials('DOCKER_HUB_CREDENTIALS')
    }

    stages {
        stage('Debug Docker') {
            steps {
                sh 'echo $PATH'
                sh 'sudo -u suman docker --version'
            }
        }

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
                    checkout scm

                    docker.withRegistry(${DOCKER_REGISTRY}, 'DOCKER_HUB_CREDENTIALS') {
                        def customImage = docker.build("${DOCKER_IMAGE}:${DOCKER_TAG}")

                            /* Push the container to the custom Registry */
                            customImage.push()

                    }
                }
            }
        }

        stage('Deploy to AWS EC2') {
            steps {
                script {
                    sshagent(credentials: ['AWS_SSH_KEY']) {
                        sh '''
                        ssh -o StrictHostKeyChecking=no ec2-user@${AWS_EC2_PUBLIC_IP} << EOF
                            set -e  # Exit immediately if a command fails

                            echo "Updating package information..."
                            sudo yum update -y

                            echo "Installing Docker..."
                            sudo yum install -y docker

                            echo "Starting Docker service..."
                            sudo systemctl start docker
                            sudo systemctl enable docker

                            echo "Adding ec2-user to the Docker group..."
                            sudo usermod -aG docker ec2-user

                            echo "Logging in to Docker..."
                            echo "$DOCKER_HUB_CREDENTIALS_PSW" | docker login -u "${DOCKER_HUB_CREDENTIALS_USR}" --password-stdin ${DOCKER_REGISTRY}

                            echo "Pulling the latest Docker image..."
                            docker pull ${DOCKER_IMAGE}:${DOCKER_TAG}

                            echo "Stopping and removing existing container (if any)..."
                            docker stop rider-guru || true
                            docker rm rider-guru || true

                            echo "Starting the new container..."
                            docker run -d --name rider-guru -p 8080:8080 ${DOCKER_IMAGE}:${DOCKER_TAG}

                            echo "Deployment completed successfully!"
                        EOF
                        '''
                    }
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
