pipeline {
    agent any
    
    environment {
        // Aseguramos que Java y Maven estén en el PATH si es necesario
        PATH = "/usr/local/bin:/usr/bin:/bin:/usr/sbin:/sbin:$PATH"
    }

    stages {
        stage('Instalar Dependencias') {
            steps {
                echo 'Instalando dependencias de Selenium (Maven)...'
                dir('herramienta1-selenium') { 
                    sh 'mvn clean install -DskipTests' 
                }
                
                echo 'Instalando dependencias de Playwright...'
                dir('herramienta2-playwright') { sh 'npm install' }
            }
        }
        
        stage('Test Selenium') {
            steps {
                dir('herramienta1-selenium') {
                    sh 'mvn test'
                }
            }
        }

        stage('Test Playwright') {
            steps {
                dir('herramienta2-playwright') { 
                    sh 'npx cucumber-js --format allure-cucumberjs/reporter --format-options \'{"resultsDir": "allure-results"}\'' 
                }
            }
        }
    } // <--- Cierra Stages

    post {
        always {
            echo 'Generando reportes de Allure...'
            
            // --- Selenium ---
            // Generar reporte estático y archivar
            sh 'allure generate herramienta1-selenium/target/allure-results --clean -o herramienta1-selenium/allure-report || true'
            archiveArtifacts artifacts: 'herramienta1-selenium/allure-report/**', fingerprint: true

            // --- Playwright ---
            // Generar reporte estático y archivar
            sh 'npx allure generate herramienta2-playwright/allure-results --clean -o herramienta2-playwright/allure-report || true'
            archiveArtifacts artifacts: 'herramienta2-playwright/allure-report/**', fingerprint: true
        }
    }
} 