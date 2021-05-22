pipeline {
    agent any

    stages {
        stage('pull code') {
            steps {
                echo 'Hello World'
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[credentialsId: 'acdde7ea-64bf-469d-9652-0f797f92a509', url: 'https://github.com/pengjie-123/springdemo.git']]])
                echo 'hello jie'
            }
        }
    }
}