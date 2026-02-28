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
                    // Maven usualmente instala dependencias al correr el test, 
                    // pero si necesitas un 'clean install' especial, se pone aquí:
                    sh 'mvn clean install -DskipTests' 
                }
                
                echo 'Instalando dependencias de Playwright...'
                dir('herramienta2-playwright') { sh 'npm install' }
            }
        }
        
        stage('Test Selenium') {
            steps {
                dir('herramienta1-selenium') {
                    // Comando de Maven para ejecutar pruebas
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
    }
}