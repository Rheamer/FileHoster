PROJECT_VERSION = "1.0.0"

pipeline {
    agent any
    triggers {
    githubPush()
    }
    stages{

        stage('SonarQube Analysis') {
            steps{
                withSonarQubeEnv('sonarq') {
                    sh "./gradlew sonarqube"
                }
            }
        }
        stage('Build') {
            steps{
               withAllureUpload(serverId: 'localhost', projectId: '1', results: [[path: 'target/allure-results']]) {
                 sh './gradlew clean build'
               }

            }
        }


        stage('Deploy') {
            steps {
                script {
                    def container = docker.build()
                }
            }
        }





    }
}