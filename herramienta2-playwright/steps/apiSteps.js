const { When, Then } = require('@cucumber/cucumber');
const playwright = require('playwright');

let apiContext;

When('envío una petición POST al endpoint {string}', async function (endpoint) {

    apiContext = await playwright.request.newContext({
        baseURL: 'https://api.demoblaze.com'
    });

    this.loginResponse = await apiContext.post(endpoint, {
        data: { username: "UsuarioExiste", password: "123" }
    });

    const body = await this.loginResponse.json().catch(() => ({}));
    this.userCookie = body.Auth_token || undefined;
});

Then('la respuesta debe ser 200', async function () {
    if (this.loginResponse.status() !== 200) {
        throw new Error(`La API respondió con status ${this.loginResponse.status()}`);
    }
});