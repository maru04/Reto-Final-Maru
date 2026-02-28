pipeline {
    agent any
    
    // Esto ayuda a que el Mac encuentre los comandos de Node
    environment {
        PATH = "/usr/local/bin:/usr/bin:/bin:/usr/sbin:/sbin:$PATH"
    }

    stages {
        stage('Instalar Dependencias') {
            steps {
                echo 'Instalando dependencias de Selenium...'
                dir('herramienta1-selenium') { sh 'npm install' }
                
                echo 'Instalando dependencias de Playwright...'
                dir('herramienta2-playwright') { sh 'npm install' }
            }
        }
        
        stage('Test Selenium') {
            steps {
                dir('herramienta1-selenium') {
                    // Asegúrate que en tool1/package.json el script se llame "test"
                    sh 'npm test'
                }
            }
        }

        stage('Test Playwright') {
            steps {
                dir('herramienta2-playwright') {
                    // Comando para correr Cucumber y generar reporte Allure
                    sh 'npx cucumber-js --format allure-cucumberjs/reporter --format-options \'{"resultsDir": "allure-results"}\''
                }
            }
        }
    }
}
