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

        stage('TEST') {
            steps {
                sh 'mvn test'
            }
        }
        stage ('Checkstyle Analysis') {
            steps {
                sh 'mvn checkstyle:checkstyle'
            }
        }
        stage ('Sonar Analysis') {
            environment {
                scannerHome = tool 'sonar4.7'
            }
            steps {
                withSonarQubeEnv('sonar') {
                    sh '''${scannerHome}/bin/sonar-scanner
                    -Dsonar.projectKey=vprofile \
                    -Dsonar.projectName=vprofile \
                    -Dsonar.projectVersion=1.0 \
                    -Dsonar.sources=src/ \
                    -Dsonar.java.binaries=target/test-classes/com/visualpathit/account/controllerTest \
                    -Dsonar.junit.reportPath=target/surefile-reports \
                    -Dsonar.coverage.jacoco.reportPath=target/jacoco/jacoco.exec \
                    -Dsonar.checkstyle.reportPaths=target/checkstyle-report.xml'''
                }
            }
        }
    }
}
