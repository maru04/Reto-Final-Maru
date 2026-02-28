@api
Feature: Registro de usuario vía API

  Scenario: Registro exitoso
    When registro un nuevo usuario vía API con username "usuarioTestAPI" y password "password123"
    Then la respuesta de registro debe ser exitosa

  Scenario: Registro fallido por usuario existente
    When registro un nuevo usuario vía API con username "usuarioTestAPI" y password "password123"
    Then la respuesta de registro debe indicar usuario existente