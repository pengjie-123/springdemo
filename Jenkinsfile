pipeline {
    agent any

    tools {
              maven 'maven'
        }
        parameters {
           string(name: 'VERSION', defaultValue: '2.24-SNAPSHOT', description: 'Which version to build?')
        }

    stages {
        stage('echo version') {
            steps {
                sh 'mvn -version'
                echo 'Hello World'
                echo 'empty stage'
                echo 'hello jie'
                echo "${VERSION}"
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean install'
           }
        }
         stage('Build images') {
                    steps {
                        sh 'docker --version'
                        sh docker build docker.finnplay.net/springdemo:1.0 .
                   }
            }
    }
}