pipeline {
    agent any

    environment {
        DOCKER_IMAGE_NAME = "aicontrol" 
        DOCKER_IMAGE_TAG = "build-${BUILD_NUMBER}" // ใช้ build number เพื่อสร้าง tag ที่ไม่ซ้ำกัน
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out code...'
                git 'https://github.com/dejavusv/bdefaicontroller.git'
            }
        }

        stage('Build Spring Boot App') {
            steps {
                echo 'Building the application...'
                sh './mvnw clean package'
            }
        }

        stage('Build Docker Image') {
            steps {
                echo "Building Docker image: ${DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_TAG}"
                script {
                    // สร้าง image บนเครื่อง local
                    docker.build("${DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_TAG}", ".")
                }
            }
        }

        // --- ไม่ต้องมี Stage 'Push Docker Image' ---

        stage('Deploy') {
            steps {
                echo "Deploying image ${DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_TAG}"
                sh '''
                    # หยุดและลบ container เก่า (ถ้ามี)
                    docker stop aicontrol || true
                    docker rm aicontrol || true

                    # --- ไม่ต้องมี docker pull ---

                    # รัน container ใหม่ด้วย Image ที่เพิ่ง build เสร็จจากในเครื่อง
                    docker run -d --name aicontrol -p 8083:8083 ${DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_TAG}
                '''
            }
        }
    }
    
    post {
        always {
            echo 'Pipeline finished.'
            cleanWs()
        }
    }
}