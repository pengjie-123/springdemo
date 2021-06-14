pipeline {
    agent any

     environment {
            ENVIRONMENT_ENDPOINT = 'http://192.168.0.100:8081/v1'
            ACCESS_KEY           = '3D4C98E120BB1FB51C74'
            SECRET_KEY           = 'p3tmPXKDxNADMdyjish1FEUUSS89B4ZqH1t8DpQ9'
            STACK_NAME           = 'testapp'
            SERVICE_NAME         = 'springdemo'
            DOCKER_COMPOSE_FILE  = 'docker-compose.yml'
        }

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
                   sh "rancher-compose --url ${env.ENVIRONMENT_ENDPOINT} --access-key ${env.ACCESS_KEY} --secret-key ${env.SECRET_KEY} -f ${workspace}/${env.DOCKER_COMPOSE_FILE}   -p ${env.STACK_NAME} up ${env.SERVICE_NAME} --force-upgrade -p -c -d"
              }
        }
    }
}