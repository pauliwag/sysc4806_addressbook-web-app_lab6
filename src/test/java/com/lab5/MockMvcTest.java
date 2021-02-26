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

/**
 * @author Paul Roode
 */
@SpringBootTest
@AutoConfigureMockMvc
public class MockMvcTest {

    private static final String VIEW_ADDRESSBOOK_FORM_TEXT = "View AddressBook ID #";

    @Autowired
    private MockMvc mockMvc;

    /**
     * Verifies that the landing page content is initialized properly as per Runner::demo().
     *
     * @throws Exception
     */
    @Test
    public void shouldReturnViewAddressBookFormText() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(VIEW_ADDRESSBOOK_FORM_TEXT)));
    }

}
