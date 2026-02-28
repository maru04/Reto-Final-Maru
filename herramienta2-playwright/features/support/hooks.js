const { Before, After, AfterStep } = require('@cucumber/cucumber');
const { CartService } = require('../../services/cartService');
const playwright = require('playwright');
const fs = require('fs');
const path = require('path');

const { HomePage } = require('../../pages/homePage');
const { LoginPage } = require('../../pages/loginPage');
const { CategoryPage } = require('../../pages/categoryPage');
const { ProductPage } = require('../../pages/productPage');

console.log("HOOKS CARGADOS");

// -----------------------------
// Hook para escenarios UI
Before({ tags: "@ui" }, async function ({ pickle }) {
    console.log(`>>> INICIANDO ESCENARIO: ${pickle.name} <<<`);
    this.browser = await playwright.chromium.launch({ headless: false });
    this.context = await this.browser.newContext();
    this.page = await this.context.newPage();

    // Instanciar páginas
    this.homePage = new HomePage(this.page);
    this.loginPage = new LoginPage(this.page);
    this.categoryPage = new CategoryPage(this.page);
    this.productPage = new ProductPage(this.page);

    console.log(`[HOOKS] Páginas inicializadas`);
});

// -----------------------------
// Hook para escenarios API
Before({ tags: "@api" }, async function () {
    this.cartService = new CartService();
    this.userCookie = this.cartService.cartId;
    console.log(`[HOOKS] Login API simulado. CartId usado como userCookie: ${this.userCookie}`);
});

// -----------------------------
// AfterStep: captura evidencias y logs
AfterStep(async function ({ result, pickle }) {
    const timestamp = Date.now();
    const evidenciaDir = path.join('evidencia');
    if (!fs.existsSync(evidenciaDir)) fs.mkdirSync(evidenciaDir, { recursive: true });

    if (result.status === 'FAILED') {
        console.log(`[HOOKS] Step FAILED en escenario: ${pickle.name}`);

        // Screenshot UI
        if (this.page) {
            const screenshotPath = path.join(evidenciaDir, `screenshot_${timestamp}.png`);
            const buffer = await this.page.screenshot({ path: screenshotPath, fullPage: true });
            this.attach(buffer, 'image/png'); // Esto funciona con Allure
            console.log(`[HOOKS] Screenshot guardado: ${screenshotPath}`);
        }

        // API response
        if (this.cartResponse) {
            const apiDir = path.join(evidenciaDir, 'api_responses');
            if (!fs.existsSync(apiDir)) fs.mkdirSync(apiDir, { recursive: true });
            const body = await this.cartResponse.json().catch(() => ({}));
            const filePath = path.join(apiDir, `response_${timestamp}.json`);
            fs.writeFileSync(filePath, JSON.stringify(body, null, 2));
            this.attach(JSON.stringify(body, null, 2), 'application/json');
            console.log(`[HOOKS] API response guardada: ${filePath}`);
        }
    }
});

// -----------------------------
// After: cerrar navegador y registrar finalización
After(async function ({ pickle }) {
    console.log(`>>> FINALIZANDO ESCENARIO: ${pickle.name} <<<`);

    // Screenshot final UI
    if (this.page) {
        const screenshotBuffer = await this.page.screenshot({ fullPage: true });
        this.attach(screenshotBuffer, 'image/png');
    }

    if (this.context) await this.context.close();
    if (this.browser) await this.browser.close();
    console.log(`[HOOKS] Navegador cerrado`);
});