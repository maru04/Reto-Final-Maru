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
                // Usamos catchError para que el pipeline no se detenga si fallan los tests
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    dir('herramienta1-selenium') {
                        sh 'mvn test'
                    }
                }
            }
        }

        stage('Test Playwright') {
            steps {
                // Usamos catchError para que el pipeline no se detenga si fallan los tests
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    dir('herramienta2-playwright') { 
                        sh 'npx cucumber-js --format allure-cucumberjs/reporter --format-options \'{"resultsDir": "allure-results"}\'' 
                    }
                }
            }
        }

        // --- NUEVA ETAPA: GENERAR REPORTE ---
        stage('Generar Reportes Allure') {
            steps {
                echo 'Generando reporte estático de Allure para Selenium...'
                sh 'allure generate herramienta1-selenium/target/allure-results --clean -o herramienta1-selenium/allure-report || true'
                
                echo 'Generando reporte estático de Allure para Playwright...'
                sh 'npx allure generate herramienta2-playwright/allure-results --clean -o herramienta2-playwright/allure-report || true'
            }
        }

        // --- NUEVA ETAPA: PUBLICAR REPORTE (Lo que te pedía el profe) ---
        stage('Publicar Allure Report Selenium') {
            steps {
                echo 'Publicando reporte Selenium...'
                publishHTML([
                    allowMissing: false,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'herramienta1-selenium/allure-report',
                    reportFiles: 'index.html',
                    reportName: 'Allure Report - Selenium'
                ])
            }
        }

        stage('Publicar Allure Report Playwright') {
            steps {
                echo 'Publicando reporte Playwright...'
                publishHTML([
                    allowMissing: false,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'herramienta2-playwright/allure-report',
                    reportFiles: 'index.html',
                    reportName: 'Allure Report - Playwright'
                ])
            }
        }
    }

    post {
        always {
            echo 'Pipeline Finalizado.'
            // Mantenemos esto por si acaso quieres tener los zips también
            archiveArtifacts artifacts: 'herramienta1-selenium/allure-report/**, herramienta2-playwright/allure-report/**', fingerprint: true
        }
    }
}