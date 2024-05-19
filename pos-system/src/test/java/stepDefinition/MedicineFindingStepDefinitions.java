/*
package stepDefinition;

import com.lifepill.possystem.dto.responseDTO.MedicineGetResponseDTO;
import com.lifepill.possystem.entity.Item;
import com.lifepill.possystem.exception.NotFoundException;
import com.lifepill.possystem.service.MedicineFindingService;
import com.lifepill.possystem.util.StandardResponse;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class MedicineFindingStepDefinitions {

    @Autowired
    private TestRestTemplate restTemplate;

    private ResponseEntity<StandardResponse> responseEntity;



    @Given("a list of existing medicines")
    public void a_list_of_existing_medicines() {
        //  the test database is pre-populated with medicines

    }

    @When("the client requests to find medicines by name {string}")
    public void the_client_requests_to_find_medicines_by_name(String itemName) {
        try {
            responseEntity = restTemplate.getForEntity("/lifepill/v1/medicine-finding/find-medicine?itemName=" + itemName, StandardResponse.class);
        } catch (NotFoundException e) {
            responseEntity = new ResponseEntity<>(new StandardResponse(404, e.getMessage(), null), HttpStatus.NOT_FOUND);
        }
    }

    @Then("the response should contain a list of medicines with the name {string}")
    public void the_response_should_contain_a_list_of_medicines_with_the_name(String itemName) {
        List<MedicineGetResponseDTO> medicines = (List<MedicineGetResponseDTO>) responseEntity.getBody().getData();
        assertFalse(medicines.isEmpty());
        for (MedicineGetResponseDTO medicine : medicines) {
            assertEquals(itemName, medicine.getItemName());
        }
    }

    @Then("the response should contain an error message {string}")
    public void the_response_should_contain_an_error_message(String errorMessage) {
        assertEquals(errorMessage, responseEntity.getBody().getMessage());
    }

    @Then("the response should have a success status code")
    public void the_response_should_have_a_success_status_code() {
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Then("the response should have an error status code")
    public void the_response_should_have_an_error_status_code() {
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
}*/
