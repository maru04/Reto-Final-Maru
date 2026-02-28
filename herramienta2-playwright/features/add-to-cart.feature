@ui
Feature: Agregar producto al carrito

Scenario: Agregar un producto
  Given el usuario está en la página principal
  When inicia sesión con usuario "UsuarioExiste" y password "123"
  And el usuario selecciona el producto "Sony vaio i5"
  And agrega el producto al carrito
  Then el producto se agrega correctamente