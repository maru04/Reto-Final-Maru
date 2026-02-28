// services/cartService.js
class CartService {
    constructor() {
        // ID simulado de carrito o sesión para pruebas
        this.cartId = 'test-cart-123';
    }

    async addProduct(productId) {
        // Simula agregar un producto a un carrito
        console.log(`[CartService] Producto agregado al carrito: ${productId}`);
        return { status: 200, productId };
    }

    async getCart() {
        // Devuelve un carrito simulado
        return {
            cartId: this.cartId,
            products: [],
        };
    }
}

module.exports = { CartService };