#!/bin/groovy
pipeline {
    agent any
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



    stages {

           stage('Checkout') {
               steps {
                       //bat "https://git.rakuten-it.com/users/ts-pushpabh.kodikara/repos/spring-hello-world"
             	 git branch: 'dev', url: 'https://github.com/pshdev789/spring-hello-world.git'
               }
            }
           stage('Build') {
                       steps {
                           sh './gradlew assemble'
                           sh '''
                             env | grep -e PATH -e JAVA_HOME
                             which java
                             java -version
                           '''
                       }
                   }
                   stage('Test') {
                       steps {
                           sh './gradlew test'
                       }
                   }

           stage('Test & Jacoco Static Analysis') {
             steps{
                    // junit '*/build/test-results/test/*.xml'
                     jacoco()
                 }//jacoco
           }
           stage ('Deploy on this Server') {
             steps{
                             deploy adapters: [tomcat10(credentialsId: 'TOMCAT_CREDENTIAL_IN_SETTINGS', path: '', url: 'http://localhost:8088')], contextPath: null, war: '**/*.war'
                }
             }//Deploy
  }//stages
}//pipeline
