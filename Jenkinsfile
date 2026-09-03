pipeline {
    agent any
        triggers{
            cron('0 2 * * *')
            pollSCM('H/5 * * * *')
        }
    
    tools {
        maven 'Maven3'
        jdk 'JDK17'
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/manalivijayaher/api-automation-java.git'
            }
        }
        stage('Build') {
            steps {
                bat 'mvn clean install'
            }
        }
        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }
    }
    post {
        success {
            mail to: 'your-primary@gmail.com', 'manaliaher0411@gmail.com',
                 subject: "Build Succeeded: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                 body: "The build passed. Details: ${env.BUILD_URL}"
        }
        failure {
            mail to: 'your-primary@gmail.com','manaliaher0411@gmail.com',
                 subject: "Build Failed: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                 body: "The build failed. Check console output at ${env.BUILD_URL}"
        }
    }
}
