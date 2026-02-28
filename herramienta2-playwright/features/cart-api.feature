@api
Feature: API del carrito

Scenario: Verificar login API
  When envío una petición POST al endpoint "/login"
  Then la respuesta debe ser 200