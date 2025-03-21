pipeline {
    agent any

    stages {
        stage('Set ChromeDriver Permissions') {
            steps {
                sh 'chmod +x src/test/resources/drivers/chromedriver'
            }
        }
        stage('Run Tests') {
            steps {
                sh 'mvn clean install test'
            }
        } 

        stage('Archivar Screenshots') {
            steps {
                archiveArtifacts artifacts: 'screenshots/*.png', allowEmptyArchive: true
            }
        }
    }
}
