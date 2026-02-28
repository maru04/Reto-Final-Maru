
@ui
Feature: Registro de usuario

  Scenario: Registro exitoso
    Given el usuario navega a la página de registro
    When completa el formulario con datos válidos
    And presiona el botón registrar
    Then el usuario se registra correctamente

  Scenario: Registro fallido por usuario existente
    Given el usuario navega a la página de registro
    When crea un nuevo usuario
    And intenta registrar el mismo usuario nuevamente
    Then se muestra el mensaje de usuario existente



