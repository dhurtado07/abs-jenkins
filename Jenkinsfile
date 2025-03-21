pipeline {
    agent any

    stages {
        stage('Preparar ChromeDriver') {
            steps {
                sh 'chmod +x src/test/resources/drivers/chromedriver'
            }
        }

        stage('Run Tests') {
            steps {
                sh 'mvn clean test'
            }
        }

        stage('Archivar Screenshots') {
            steps {
                archiveArtifacts artifacts: 'screenshots/*.png', allowEmptyArchive: true
            }
        }
    }

    post {
        always {
            publishHTML(target: [
                reportDir: 'target/surefire-reports',
                reportFiles: 'index.html',
                reportName: 'Test Report'
            ])
        }
    }
}
