pipeline {
    
	agent any

	tools {
        maven "MAVEN3"
        jdk "java8"
    }

    environment {
        SNAP-REPO = "vprofile-snapshot"
        NEXUS-USER = "admin"
        NEXUS-PASS = "admin"
        RELEASE-REPO = "vprofile-release"
        CENTRAL-REPO = "vprofile-maven-central-store-dependencies"
        NEXUSIP = "172.31.94.126"
        NEXUS_VERSION = "nexus3"
        NEXUS_PROTOCOL = "http"
        NEXUS_URL = "172.31.94.126:8081"
        NEXUSPORT = "8081"
        NEXUS_REPOSITORY = "vprofile-release"
	    NEXUS-GRP-REPO    = "vprofile-maven-group"
        NEXUS_LOGIN = "nexuslogin"
        ARTVERSION = "${env.BUILD_ID}"
    }
	
    stages{
        
        stage('BUILD'){
            steps {
                sh 'mvn -s settings.xml -DskipTests install'
            }
            post {
                success {
                    echo 'Now Archiving...'
                    archiveArtifacts artifacts: '**/target/*.war'
                }
            }
        }

	
    }


}
