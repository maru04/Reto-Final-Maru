class CategoryPage {
    constructor(page) {
        this.page = page;
    }

    async selectCategory(categoria) {
        await this.page.click(`a:has-text("${categoria}")`);
        return true;
    }

    async getProducts() {
        await this.page.waitForSelector('.card-title', { timeout: 5000 });
        return this.page.$$eval('.card-title', nodes => nodes.map(n => n.textContent));
    }
}

module.exports = { CategoryPage };