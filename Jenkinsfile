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
}
