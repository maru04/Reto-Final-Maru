class ProductPage {
    constructor(page) {
        this.page = page;
        this.addToCartButton = 'a:has-text("Add to cart")';
        this.productNameSelector = '.name';
    }

    async selectProduct(producto) {
        await this.page.click(`a:has-text("${producto}")`);
        await this.page.waitForSelector(this.productNameSelector);
        this.selectedProduct = producto;
        console.log(`[PRODUCT PAGE] Producto seleccionado: ${producto}`);
    }

    async addToCart() {
        const dialogPromise = this.page.waitForEvent('dialog');
        const responsePromise = this.page.waitForResponse(
            response => response.url().includes('addtocart') && response.request().method() === 'POST'
        );

        await this.page.click(this.addToCartButton);

        this.cartResponse = await responsePromise;
        const dialog = await dialogPromise;
        console.log(`[PRODUCT PAGE] Mensaje alert: ${dialog.message()}`);
        await dialog.accept();

        if (this.cartResponse.status() !== 200) {
            throw new Error(`Error en API addtocart. Status: ${this.cartResponse.status()}`);
        }
        console.log(`[PRODUCT PAGE] Producto agregado al carrito correctamente`);
    }
}

module.exports = { ProductPage };