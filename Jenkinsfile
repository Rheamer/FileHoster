PROJECT_VERSION = "1.0.0"

pipeline {
    agent any
    triggers {
        githubPush()
    }
    stages {
        stage('Build') {
            steps {
                sh "chmod +x ./gradlew"
                sh './gradlew assemble'
            }
        }
        stage('SonarQube') {
            steps{
                withSonarQubeEnv(installationName: 'sonarqube', credentialsId: 'sonarqubbe') {
                        sh "chmod +x ./gradlew"
                        sh "./gradlew sonarqube"
                }
            }
        }

        stage('Test') {
            withAllureUpload(serverId: 'localhost', projectId: '1', results: [[path: 'target/allure-results']]) {
                steps {
                    sh './gradlewle test'
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                sh './gradlew docker'
            }
        }

        stage('Run Docker Image') {
            steps {
                sh './gradlew dockerRun'
            }
        }

    }
}