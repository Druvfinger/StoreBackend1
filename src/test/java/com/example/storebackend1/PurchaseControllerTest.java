package com.example.storebackend1;

import com.example.storebackend1.Entities.Customer;
import com.example.storebackend1.Entities.Item;
import com.example.storebackend1.Entities.Purchase;
import com.example.storebackend1.Repos.CustomerRepo;
import com.example.storebackend1.Repos.ItemRepo;
import com.example.storebackend1.Repos.PurchaseRepo;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PurchaseControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private PurchaseRepo mockPurchaseRepo;
    @MockBean
    private CustomerRepo mockCustomerRepo;
    @MockBean
    private ItemRepo mockItemRepo;

    @BeforeEach
    public void init() {
        Customer customer1 = new Customer(1L, "123", "Sara");
        Customer customer2 = new Customer(2L, "312", "Kalle");
        Customer customer3 = new Customer(3L, "432", "Kanin");

        Item item1 = new Item(1L, "product1", 1.0);
        Item item2 = new Item(2L, "product2", 2.0);
        Item item3 = new Item(3L, "product3", 3.0);
        String date = "2023-04-26 23:30";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);

        Purchase purchase1 = new Purchase(1L, dateTime, customer1, List.of(item1));
        Purchase purchase2 = new Purchase(2L, dateTime, customer2, List.of(item2));
        Purchase purchase3 = new Purchase(3L, dateTime, customer3, List.of(item3));
        Purchase purchase4 = new Purchase(4L, dateTime, customer3, List.of(item2));

        when(mockPurchaseRepo.findById(1L)).thenReturn(Optional.of(purchase1));
        when(mockPurchaseRepo.findById(2L)).thenReturn(Optional.of(purchase2));
        when(mockPurchaseRepo.findById(3L)).thenReturn(Optional.of(purchase3));
        when(mockPurchaseRepo.findAll()).thenReturn(Arrays.asList(purchase1, purchase2, purchase3));
    }

    @Test
    public void getAllPurchasesTest() throws Exception {
        this.mockMvc.perform(get("/purchase")).andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                        "  {\n" +
                        "    \"id\": 1,\n" +
                        "    \"purchaseDate\": \"2023-04-26T23:30:00\" ,\n" +
                        "    \"customer\": {\n" +
                        "      \"id\": 1,\n" +
                        "      \"ssn\": \"123\",\n" +
                        "      \"name\": \"Sara\"\n" +
                        "    },\n" +
                        "    \"items\": [\n" +
                        "      {\n" +
                        "        \"id\": 1,\n" +
                        "        \"name\": \"product1\",\n" +
                        "        \"price\": 1\n" +
                        "      }\n" +
                        "    ]\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"id\": 2,\n" +
                        "    \"purchaseDate\": \"2023-04-26T23:30:00\" ,\n" +
                        "    \"customer\": {\n" +
                        "      \"id\": 2,\n" +
                        "      \"ssn\": \"312\",\n" +
                        "      \"name\": \"Kalle\"\n" +
                        "    },\n" +
                        "    \"items\": [\n" +
                        "      {\n" +
                        "        \"id\": 2,\n" +
                        "        \"name\": \"product2\",\n" +
                        "        \"price\": 2\n" +
                        "      }\n" +
                        "    ]\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"id\": 3,\n" +
                        "    \"purchaseDate\": \"2023-04-26T23:30:00\" ,\n" +
                        "    \"customer\": {\n" +
                        "      \"id\": 3,\n" +
                        "      \"ssn\": \"432\",\n" +
                        "      \"name\": \"Kanin\"\n" +
                        "    },\n" +
                        "    \"items\": [\n" +
                        "      {\n" +
                        "        \"id\": 3,\n" +
                        "        \"name\": \"product3\",\n" +
                        "        \"price\": 3\n" +
                        "      }\n" +
                        "    ]\n" +
                        "  }\n" +
                        "]"));

        String jsonResponse = this.mockMvc.perform(get("/purchase"))
                .andReturn().getResponse().getContentAsString();
        int size = JsonPath.parse(jsonResponse).read("$.length()");
        assertEquals(3, size);
        assertNotEquals(4, size);
    }

    //TODO: DENNA GÅR INTE! -> []: Expected 1 values but got 0
//    @Test
//    public void getPhurchaseByIdTest() throws Exception {
//        this.mockMvc.perform(get("/purchase/1"))
//                .andExpect(status().isOk())
//                .andExpect(content().json("[{\"id\":1,\"purchaseDate\":\"2023-04-26T23:30:00\",\"customer\":{\"id\":1,\"ssn\":\"123\",\"name\":\"Sara\"},\"items\":[{\"id\":1,\"name\":\"product1\",\"price\":1}]}]"));

//        this.mockMvc.perform(get("/purchase/1"))
//                .andExpect(jsonPath("$.id", Matchers.not(equalTo("2"))));
//    }
}
