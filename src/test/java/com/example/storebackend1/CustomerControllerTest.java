package com.example.storebackend1;


import com.example.storebackend1.Entities.Customer;
import com.example.storebackend1.Repos.CustomerRepo;
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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerRepo mockCustomerRepo;

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
    }
    @Test
    public void getCustomerByIdTest() throws Exception{
        this.mockMvc.perform(get("/customers/1")).andExpect(status().isOk()).
                andExpect(content().json("{\"name\":\"Sara\",\"id\":1,\"ssn\": \"123\"}"));
    }
    @Test
    public void addCustomer() throws Exception{
        this.mockMvc.perform(post("/customers/add").contentType(MediaType.APPLICATION_JSON).
                content("{\"name\":\"Dummy\",\"id\":4,\"ssn\": \"567\"}")).andExpect(status().isOk()).andExpect(content()
                .string(equalTo("Customer with name: Dummy added.")));
    }

}
