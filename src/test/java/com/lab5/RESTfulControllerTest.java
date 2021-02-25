package com.lab5;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RESTfulControllerTest {

    private static final String ADDRESS_BOOKS_ROOT_ENDPOINT = "addressBooks";
    private static final String BUDDY_INFOS_ROOT_ENDPOINT = "buddyInfos";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private AddressBookRepository addressBookRepository;

    @Autowired
    private BuddyInfoRepository buddyInfoRepository;

    private AddressBook addressBook;
    private BuddyInfo buddyInfo;

    @Before
    public void setUp() {
        addressBook = new AddressBook();
        buddyInfo = new BuddyInfo("John Doe", "101 John Doe Lane", "177-Doe-John");
        addressBookRepository.save(addressBook);
        buddyInfoRepository.save(buddyInfo);
    }

    @After
    public void tearDown() {
        addressBookRepository.delete(addressBook);
        buddyInfoRepository.delete(buddyInfo);
    }

    @Test
    public void createAddressBook() {
        long count = addressBookRepository.count();
        try {
            mvc.perform(MockMvcRequestBuilders.post("/" + ADDRESS_BOOKS_ROOT_ENDPOINT)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{}"))
                    .andExpect(status().isCreated());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertEquals(count + 1, addressBookRepository.count());
    }

    @Test
    public void deleteAddressBook() {
        try {
            mvc.perform(MockMvcRequestBuilders.delete("/" + ADDRESS_BOOKS_ROOT_ENDPOINT + "/" + addressBook.getId()))
                    .andExpect(status().isNoContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
