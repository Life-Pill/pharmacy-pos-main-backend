pipeline {
    agent any

    tools {
        jdk 'Java17'
        maven 'maven_3_9_6'
        dockerTool 'docker'
    }

    stages {
        stage("Cleanup Workspace") {
            steps {
                cleanWs()
            }
        }

        stage("Checkout from SCM") {
            steps {
                git branch: 'main', credentialsId: 'github', url: 'https://github.com/Life-Pill/pharmacy-pos-backend'
            }
        }

        stage("Build Application") {
            steps {
                dir('pos-system') {
                    sh "mvn clean package"
                }
            }
        }

        stage("Test Application") {
            steps {
                dir('pos-system') {
                    sh "mvn test"
                }
            }
        }

        stage('Build docker image'){
            steps{
                dir('pos-system') {
                    sh 'docker build -t pramithamj/pos-system:latest .'
                }
            }
        }

        stage('Stop and Remove Existing Container') {
            steps {
                sh 'docker stop java_container || true'
                sh 'docker rm java_container || true'
            }
        }

        stage('Run Docker container') {
            steps {
                sh 'docker run -d --name java_container pramithamj/pos-system:latest'
            }
        }

        stage('Check Docker containers') {
            steps {
                sh 'docker ps'
            }
        }

        stage("Unit Tests") {
            steps {
                dir('pos-system') {
                    sh "mvn test"
                }
            }
        }
        stage("SonarQube Analysis"){
            steps{
                withSonarQubeEnv(credentialsId: 'jenkins-sonarqube-token', installationName: 'SonarQube') {
                    dir('pos-system') {
                        sh "mvn sonar:sonar"
                    }
                }
            }
        }
        stage('Qodana') {
            environment {
                QODANA_TOKEN = credentials('qodana-token')
            }
            agent {
                docker {
                    args '''
                        -v "${WORKSPACE}":/data/project
                        --entrypoint=""
                        '''
                    image 'jetbrains/qodana-jvm'
                }
            }
            when {
                branch 'main'
                branch 'dev'
                branch 'mobileDev'
            }
            steps {
                sh '''qodana'''
            }
        }
    }

    post {
        always {
            // Clean up Docker resources
            script {
                sh 'docker rmi --force pramithamj/pos-system:latest || true'
            }
        }
        success {
            echo 'Pipeline succeeded!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}