@ui
Feature: Categorías de productos

Scenario: Selección de categoría
  Given el usuario está en la página principal
  When el usuario selecciona la categoría "Laptops"
  Then se muestran productos de la categoría "Laptops"