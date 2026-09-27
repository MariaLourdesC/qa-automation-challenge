
Feature: Petstore API - Pet lifecycle

  Background:
    * url baseUrl
    * def petId = java.lang.System.currentTimeMillis()
    * def originalName = 'Buddy'
    * def updatedName = 'Buddy Updated'

  Scenario: Create, retrieve, update and search a pet

    # 1. Add a pet
    Given path 'pet'
    And request
      """
      {
        "id": "#(petId)",
        "name": "#(originalName)",
        "photoUrls": [],
        "status": "available"
      }
      """
    When method post
    Then status 200
    And match response.id == petId
    And match response.name == originalName
    And match response.status == 'available'

    # 2. Retrieve the pet by ID
    Given path 'pet', petId
    When method get
    Then status 200
    And match response.id == petId
    And match response.name == originalName

    # 3. Update the pet's name and status
    Given path 'pet'
    And request
      """
      {
        "id": "#(petId)",
        "name": "#(updatedName)",
        "photoUrls": [],
        "status": "sold"
      }
      """
    When method put
    Then status 200
    And match response.id == petId
    And match response.name == updatedName
    And match response.status == 'sold'

    # Verify the updated pet by ID
    Given path 'pet', petId
    When method get
    Then status 200
    And match response.name == updatedName
    And match response.status == 'sold'

    # 4. Search pets by status
    Given path 'pet', 'findByStatus'
    And param status = 'sold'
    When method get
    Then status 200
    And match response == '#[]'
    And match response contains deep
      """
      {
        "id": "#(petId)",
        "name": "#(updatedName)",
        "status": "sold"
      }
      """
