@ui
Feature: Login de usuario

Scenario: Usuario inicia sesión
  Given el usuario está en la página principal
  When inicia sesión con usuario "UsuarioExiste" y password "123"
  Then el nombre del usuario aparece en la barra superior