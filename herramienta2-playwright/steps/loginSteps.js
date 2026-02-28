const { Given, When, Then } = require('@cucumber/cucumber');

Given('el usuario está en la página principal', async function () {
    await this.homePage.goTo();
});

When('inicia sesión con usuario {string} y password {string}', async function (username, password) {
    await this.homePage.openLoginModal();
    await this.loginPage.login(username, password);
});

Then('el nombre del usuario aparece en la barra superior', async function () {
    const texto = await this.homePage.getUsername();
    if (!texto.includes('Welcome UsuarioExiste')) throw new Error('Usuario no autenticado');
});