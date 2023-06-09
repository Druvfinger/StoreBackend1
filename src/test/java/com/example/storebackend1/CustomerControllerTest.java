package com.example.storebackend1;

import com.example.storebackend1.Models.Customer;
import com.example.storebackend1.Repos.CustomerRepo;
import com.example.storebackend1.Repos.PurchaseRepo;
import com.jayway.jsonpath.JsonPath;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerRepo mockCustomerRepo;

    @MockBean
    private PurchaseRepo mockPurchaseRepo;

    @BeforeEach
    public void init(){
        Customer customer1 = new Customer(1L, "123","Sara");
        Customer customer2 = new Customer(2L, "312", "Kalle");
        Customer customer3 = new Customer(3L, "432","Kanin");

        when(mockCustomerRepo.findById(1L)).thenReturn(Optional.of(customer1));
        when(mockCustomerRepo.findById(2L)).thenReturn(Optional.of(customer2));
        when(mockCustomerRepo.findById(3L)).thenReturn(Optional.of(customer3));
        when(mockCustomerRepo.findAll()).thenReturn(Arrays.asList(customer1,customer2,customer3));

    }
    @Test
    public void getAllCustomersTest() throws Exception {
        this.mockMvc.perform(get("/customers")).andExpect(status().isOk()).andExpect(content().
                json("[{\"name\":\"Sara\",\"id\":1,\"ssn\": \"123\"}," +
                "{\"name\":\"Kalle\",\"id\":2,\"ssn\": \"312\"},"+ "{\"name\":\"Kanin\",\"id\":3,\"ssn\": \"432\"}]"));

        String jsonResponse = this.mockMvc.perform(get("/customers"))
                .andReturn().getResponse().getContentAsString();
        int size = JsonPath.parse(jsonResponse).read("$.length()");
        assertEquals(3, size);
        assertNotEquals(4, size);
    }
    @Test
    public void getCustomerByIdTest() throws Exception{
        this.mockMvc.perform(get("/customers/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"name\":\"Sara\",\"id\":1,\"ssn\": \"123\"}"));

        this.mockMvc.perform(get("/customers/1"))
                .andExpect(jsonPath("$.ssn", Matchers.not(equalTo("321"))));
    }
    @Test
    public void addCustomer() throws Exception{
        this.mockMvc.perform(post("/customers/add").contentType(MediaType.APPLICATION_JSON).
                content("{\"name\":\"Dummy\",\"id\":4,\"ssn\": \"567\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Customer with name: Dummy added.")));

        this.mockMvc.perform(post("/customers/add").contentType(MediaType.APPLICATION_JSON).
                content("{\"name\":null,\"id\":4,\"ssn\": \"567\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Could not add customer!")));
    }

}
