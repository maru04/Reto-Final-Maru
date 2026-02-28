const fs = require('fs');
const path = require('path');

// Carpeta donde se guardan los resultados de Allure
const resultsDir = path.join(process.cwd(), 'allure-results');
if (!fs.existsSync(resultsDir)) fs.mkdirSync(resultsDir);

/**
 * Genera un archivo JSON para Allure por cada step o escenario
 * @param {string} name Nombre del paso/escenario
 * @param {string} status Estado: 'passed' o 'failed'
 * @param {string} expected Texto adicional que quieras adjuntar
 */
function logStep(name, status = 'passed', expected = '') {
  const stepResult = {
    name,
    status,
    expected,
    timestamp: new Date().toISOString(),
  };

  const filename = path.join(
    resultsDir,
    `step-${Date.now()}-${Math.floor(Math.random() * 1000)}.json`
  );
  fs.writeFileSync(filename, JSON.stringify(stepResult, null, 2));
}

/**
 * Hooks globales para escenarios
 */
function attachScenarioHooks({ Before, After }) {
  Before(function (scenario) {
    logStep(`Escenario iniciado: ${scenario.pickle.name}`, 'passed');
  });

  After(function (scenario) {
    const status = scenario.result.status.toLowerCase();
    logStep(`Escenario finalizado: ${scenario.pickle.name}`, status);

    // Opcional: si quieres adjuntar errores en caso de fallo
    if (scenario.result.exception) {
      logStep(
        `Error en escenario: ${scenario.pickle.name}`,
        'failed',
        scenario.result.exception.message
      );
    }
  });
}

module.exports = { logStep, attachScenarioHooks };