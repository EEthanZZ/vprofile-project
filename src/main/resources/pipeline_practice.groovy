pipeline {
    agent any
    
    tools {
        maven "MAVEN"  // Corrected tool name to "maven"
        jdk "OracleJDK11"
    }
    
    stages {
        stage('Fetch Code') {
            steps {
                git branch: 'main', url: 'https://github.com/hkhcoder/vprofile-project'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn install -DskipTests'
            }
            post {
                success {  // Corrected "sucess" to "success"
                    echo 'Archiving artifacts now.'
                    archiveArtifacts artifacts: '**/*.war'
                }
            }
        }

        stage('UNIT TESTS') {
            steps {
                sh 'mvn test'
            }
        }
    }
}
