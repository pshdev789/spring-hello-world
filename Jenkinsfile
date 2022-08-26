#!/bin/groovy
pipeline {
    agent { label 'JDK11' } 
    tools {
        jdk 'JDK11'
    }
    options {
        skipDefaultCheckout()
    }

    parameters {
        string(name: 'BRANCH_NAME', defaultValue: 'dev', description: 'What TAG / BRANCH to build?')
        string(name: 'DOMAIN', defaultValue: 'spring-hello-world', description: 'setting for alias \n 9502:stg-shop-bff, 9504:stg-shop-bff-9504, 9508:stg-shop-bff-9508')
    }

//     environment {
//         ENV = 'stg-rwasp'
//         GIT = 'https://git.rakuten-it.com/scm/jrms/shop-bff.git'
//         REL_VERSION = "${'SNAPSHOT-' + ENV.substring(0, ENV.indexOf('-') + 1) + BRANCH_NAME.drop(BRANCH_NAME.lastIndexOf('/') + 1) + BUILD_NUMBER}"
//         IMAGE = "shop-bff:$REL_VERSION"
//         OWNER = 'dev-rms-shop-all'
//         SERVICE_GROUP = 'rms-shop-backend'
//
//     }

    stages {
//         stage('Checkout') {
//             agent any
//             steps {
//                 deleteDir()
//                 checkout scm: [$class: 'GitSCM', branches: [[name: "$BRANCH_NAME"]], userRemoteConfigs: [[credentialsId: 'Git-gep-jenkins', url: "$GIT"]]]
//                 stash(name: 'ws', includes: '**', excludes: '**/.git/**')
//             }
//         } // Checkout
//         stage ('Build') {
//           agent any
//
//           steps {
//             deleteDir()
//             unstash 'ws'
//     echo "image name is,,,,"
//     echo "$IMAGE"
//             script {
//     sh "java -version"
//               CMD_GRADLEW = "chmod +x gradlew; ./gradlew "
//               sh "$CMD_GRADLEW  clean"
//               sh "$CMD_GRADLEW  build -x test"
//               app = docker.build("$IMAGE", " -f Dockerfile .")
//               rwasp.push (app)
//             }
//           }
//         }//build
//         stage ('Deploy') {
//           agent any
//           steps {
//             deleteDir()
//             unstash 'ws'
//             script {
//               Map config = [
//               ENV : ENV,
//               IMAGE : "$IMAGE",
//               Deploy_LOCAL_ENV_CONF : "rwasp/${ENV}/env.json",
//               Deploy_LOCAL_RUN_CONF : "rwasp/${ENV}/runtime.conf",
//               OWNER : OWNER,
//               SERVICE_GROUP : SERVICE_GROUP
//               ]
//
//               rwaspDeployV2 (config)
//               rwaspSwitchV3 (ENV: ENV, Switch_DOMAIN : "${DOMAIN + '.rwasp-stg.hnd2.bdd.local'}")
//             }
//         }
//       } //Deploy

           stage('Test & Jacoco Static Analysis') {
                     junit 'target/surefire-reports/**/*.xml'
                     jacoco()
                 }//jacoco
           stage ('Deploy on this Server') {
                             deploy adapters: [tomcat9(credentialsId: 'TOMCAT_CREDENTIAL_IN_SETTINGS', path: '', url: 'http://localhost:8088')], contextPath: null, war: '**/*.war'
           }//Deploy
  }//stage
}//pipeline
