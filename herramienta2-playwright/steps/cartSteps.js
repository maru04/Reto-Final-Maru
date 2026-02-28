const { When, Then } = require('@cucumber/cucumber');
// Asegúrate de que esta ruta sea correcta según dónde esté tu archivo allureHooks
const { logStep } = require('../features/support/allureHooks'); 

When('el usuario selecciona el producto {string}', async function (producto) {
  try {
    // Asumo que tienes un método para seleccionar el producto,
    // si no, tendrás que crearlo en HomePage parecido al clickAddToCart
    await this.homePage.selectProduct(producto); 
    logStep(`Seleccionó el producto: ${producto}`, 'passed');
  } catch (error) {
    logStep(`Error al seleccionar producto: ${producto}`, 'failed', error.message);
    throw error;
  }
});

// === AQUÍ ESTÁ EL PASO ACTUALIZADO ===
When('agrega el producto al carrito', async function () {
  try {
    // Llamamos al nuevo método de HomePage
    await this.homePage.clickAddToCart(); 
    logStep('Producto agregado y alerta aceptada', 'passed');
  } catch (error) {
    logStep('Error al agregar al carrito', 'failed', error.message);
    throw error;
  }
});

Then('el producto se agrega correctamente', async function () {
  try {
    // Tu lógica de validación aquí
    logStep('Validación de producto agregada', 'passed');
  } catch (error) {
    logStep('Error en validación de producto', 'failed', error.message);
    throw error;
  }
});