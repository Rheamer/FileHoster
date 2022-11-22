PROJECT_VERSION = "1.0.0"

pipeline {
    agent any
    triggers {
    githubPush()
    }
    stages{

        stage('Build') {
            steps{
                withMaven(maven: "Maven 3.5.2") {
                    sh 'mvn clean install'
                }
            }
        }

        stage('Sonarqube') {
            steps {
                withMaven(maven: "Maven 3.5.2") {
                    sh 'mvn sonar:sonar'
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