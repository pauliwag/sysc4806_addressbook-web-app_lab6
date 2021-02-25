package com.lab5;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MockMvcTest {

    private static final String DEFAULT_ADDRESSBOOK_HEADER = "AddressBook ID # 3";

    @Autowired
    private MockMvc mockMvc;

    /**
     * Verifies that the landing page content is initialized properly as per Runner::demo().
     *
     * @throws Exception
     */
    @Test
    public void shouldReturnDefaultAddressBookHeader() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(DEFAULT_ADDRESSBOOK_HEADER)));
    }

}
