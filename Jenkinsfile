pipeline {
    agent any

    tools {
              maven 'maven3.3.9'
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
                echo "${VERSION}"            }
        }
        stage('Build') {
                    steps {
                        sh 'mvn clean install'
                    }
                }
    }
}