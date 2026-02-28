class HomePage {
    constructor(page) {
        this.page = page;
        this.url = 'https://www.demoblaze.com/';
        this.loginButton = '#login2';
        this.usernameLabel = '#nameofuser';
    }

    async goTo() {
        if (!this.page) throw new Error('HomePage: page no definida');
        await this.page.goto(this.url);
    }

    async openLoginModal() {
        await this.page.click(this.loginButton);
    }

    async getUsername() {
        await this.page.waitForSelector(this.usernameLabel, { timeout: 5000 });
        return this.page.$eval(this.usernameLabel, el => el.textContent);
    }

    async selectProduct(productName) {
        console.log(`Buscando producto: ${productName}`);
        // DemoBlaze usa enlaces con el nombre del producto
        await this.page.click(`text=${productName}`);
    }

    async clickAddToCart() {
        // Acepta la alerta que sale después de agregar
        this.page.once('dialog', dialog => dialog.accept());
        await this.page.click('text=Add to cart');
        console.log('Botón "Add to cart" presionado');
    }
}

module.exports = { HomePage };