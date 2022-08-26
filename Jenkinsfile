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



    stages {

           stage('Checkout') {
                       bat "https://git.rakuten-it.com/users/ts-pushpabh.kodikara/repos/spring-hello-world"
            }
           stage('Build') {
                       steps {
                           sh './gradlew assemble'
                       }
                   }
                   stage('Test') {
                       steps {
                           sh './gradlew test'
                       }
                   }

           stage('Test & Jacoco Static Analysis') {
                     junit 'target/surefire-reports/**/*.xml'
                     jacoco()
                 }//jacoco
           stage ('Deploy on this Server') {
                             deploy adapters: [tomcat9(credentialsId: 'TOMCAT_CREDENTIAL_IN_SETTINGS', path: '', url: 'http://localhost:8088')], contextPath: null, war: '**/*.war'
           }//
           Deploy
  }//stage
}//pipeline
