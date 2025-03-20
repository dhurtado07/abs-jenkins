pipeline {
    agent any

    stages {
        stage('Clone Repository') {
            steps {
                sh 'git clone https://github.com/dhurtado07/abs-jenkins.git'
                sh 'cd abs-jenkins'
            }
        }
        stage('Build and Test') {
            steps {
                sh 'cd abs-jenkins && mvn clean test'
            }
        }
        stage('Archive Screenshots') {
            steps {
                archiveArtifacts artifacts: 'abs-jenkins/screenshots/*.png', allowEmptyArchive: true
            }
        }
    }

    post {
        always {
            publishHTML(target: [
                reportDir: 'abs-jenkins/target/surefire-reports',
                reportFiles: 'index.html',
                reportName: 'TestNG Report'
            ])
        }
    }
}
