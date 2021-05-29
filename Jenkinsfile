pipeline {
    agent any

    tools {
              maven 'maven'
        }
        parameters {
           string(name: 'VERSION', defaultValue: '2.21-SNAPSHOT', description: 'Which version to build?')
        }

    stages {
        stage('echo version') {
            steps {
                sh 'mvn -version'
                echo "${VERSION}"
            }
        }
        stage('Build maven') {
            steps {
                sh 'mvn clean install'
           }
        }
         stage('Build images and push') {
                    steps {
                        sh 'docker build -t registry.cn-hangzhou.aliyuncs.com/jiepeng/springdemo:${VERSION} .'
                        sh 'docker login --username=我热爱的星球 --password=abc123ABC registry.cn-hangzhou.aliyuncs.com'
                        sh 'docker push registry.cn-hangzhou.aliyuncs.com/jiepeng/springdemo:${VERSION}'
                   }
            }

          stage('Deploy container') {
              steps {
                  sh 'docker-compose up -d'
              }
        }
    }
}