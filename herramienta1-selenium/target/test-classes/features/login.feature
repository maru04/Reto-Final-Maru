@ui
Feature: Login de usuario

  Scenario: Login exitoso
    Given el usuario navega a la página principal
    When inicia sesión con usuario "UsuarioExiste" y password "123"
    Then el nombre del usuario aparece en la barra superior
