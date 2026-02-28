// cucumber.mjs
export default {
  paths: ['features/**/*.feature'],
  require: ['features/**/*.js', 'steps/**/*.js'],
  // DEBE SER UN ARRAY DE STRINGS
  format: [
    'allure-cucumberjs/reporter',
    'summary'
  ],
  formatOptions: {
    resultsDir: 'allure-results'
  }
};