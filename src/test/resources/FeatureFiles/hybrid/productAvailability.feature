@hybrid
Feature: Product Availability


@positive
  Scenario Outline: Product exists via API with correct price and is visible on the storefront
    Given the product "<productName>" with price "<productPrice>" exists via the products API
    When user opens the storefront homepage
    Then "<productName>" should be visible with price "<productPrice>" in the product listing page
    
    Examples:
    |productName      | productPrice |
    |Blue Top         | Rs. 500      |
    |Winter Top       |
    |Summer White Top |
    
    
     @negative
  Scenario: Non-existent product is correctly absent from both API and UI
    Given the product "Nonexistent Product XYZ" does not exist via the products API
    When user opens the storefront homepage
    Then "Nonexistent Product XYZ" should not be visible in the product listing