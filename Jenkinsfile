pipeline {
    agent any 
    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/dhurtado07/abs-jenkins.git', branch: 'main'
            }
        }
        stage('Install Chrome (if needed)') {
            steps {
                sh '''
                if ! command -v google-chrome > /dev/null; then
                    sudo apt-get update
                    sudo apt-get install -y wget unzip libglib2.0-0 libnss3 libgconf-2-4 libfontconfig1
                    sudo wget -q https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb
                    sudo apt-get install -y ./google-chrome-stable_current_amd64.deb
                    sudo rm google-chrome-stable_current_amd64.deb
                fi
                '''
            }
        }
        stage('Set ChromeDriver Permissions') {
            steps {
                sh 'chmod +x src/test/resources/drivers/chromedriver'
            }
        }
        stage('Build and Test') {
            steps {
                sh 'mvn clean test'
            }
        }
        stage('Archive Screenshots') {
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
                reportName: 'TestNG Report'
            ])
        }
    }
}