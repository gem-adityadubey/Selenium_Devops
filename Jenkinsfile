pipeline {
    agent any

    environment {
        IMAGE_NAME = "selenium-tests"
    }

    stages {
        stage('Checkout Code') {
            steps {
                echo "Checking out source code..."
                checkout scm
            }
        }

        stage('Build Docker Image') {
            steps {
                echo "Building Docker image..."
                sh 'docker build -t $IMAGE_NAME .'
            }
        }

        stage('Run Selenium Tests in Docker') {
            steps {
                echo "Running tests inside container..."
                sh 'docker run --rm $IMAGE_NAME'
            }
        }

        stage('Post Build Cleanup') {
            steps {
                echo "Cleaning up old images..."
                sh 'docker system prune -f'
            }
        }
    }

    post {
        always {
            echo "Pipeline completed. Check test results above."
        }
    }
}
