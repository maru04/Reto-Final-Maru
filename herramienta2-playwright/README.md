## Instalación y Ejecución

1.  **Clonar el repositorio:**
    ```bash
    git clone [https://github.com/maru04/Reto-Final-Maru.git](https://github.com/maru04/Reto-Final-Maru.git)
    cd Reto-Final-Maru/herramienta2-playwright
    ```

2.  **Instalar las dependencias:**
    ```bash
    npm install
    ```

3.  **Ejecutar las pruebas:**
    ```bash
    npx cucumber-js
    ```

4.  **Generar reporte de Allure:**
    ```bash
    npx allure generate allure-results --clean -o allure-report
    ```

5.  **Visualizar reporte de Allure:**
    ```bash
    npx allure open allure-report
    ```

##  Automatización en Jenkins
Para configurar este proyecto en Jenkins, utiliza el `Jenkinsfile` ubicado en la raíz del repositorio. Este pipeline realiza los siguientes pasos para Playwright:

1.  **Instalar Dependencias**: Ejecuta `npm install` para instalar Playwright y Cucumber.
2.  **Ejecutar Pruebas**: Ejecuta `npx cucumber-js` para correr los escenarios BDD.
3.  **Reportes**: Genera el reporte de Allure ejecutando `npx allure generate`.