pipeline {
    agent any

    stages {
  
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
