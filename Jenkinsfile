PROJECT_VERSION = "1.0.0"

pipeline {
    agent any
    triggers {
    githubPush()
    }
    stages{
        stage('SCM') {
            steps{
                checkout scm
            }
        }
        stage('SonarQube Analysis') {
            steps{
                withSonarQubeEnv() {
                    sh "./gradlew sonarqube"
                }
            }
        }
        stage('Build') {
            steps{
                sh './gradlew build'
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