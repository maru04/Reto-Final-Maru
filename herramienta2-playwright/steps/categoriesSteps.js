const { When, Then } = require('@cucumber/cucumber');

When('el usuario selecciona la categoría {string}', async function (categoria) {
    this.categoryResponse = await this.categoryPage.selectCategory(categoria);
});

Then('se muestran productos de la categoría {string}', async function (categoria) {
    // Usamos la instancia creada en los hooks
    if (!this.categoryPage) {
        throw new Error('categoryPage no está definida en el World');
    }

    const productos = await this.categoryPage.getProducts();

    if (!productos || productos.length === 0) {
        throw new Error(`No se mostraron productos para la categoría ${categoria}`);
    }

    console.log(`[CATEGORIES] Productos mostrados: ${productos.length}`);
});