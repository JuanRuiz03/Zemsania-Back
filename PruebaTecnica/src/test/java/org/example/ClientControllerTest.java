package org.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClientController.class)
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static final String documentNumber = "23445322";
    private static final String JsonInformation = "{\n" +
            "    \"firstName\": \"Nicole\",\n" +
            "    \"address\": \"Calle sin fin\",\n" +
            "    \"phone\": \"3003000000\",\n" +
            "    \"city\": \"Bogotá\",\n" +
            "    \"secondSurname\": \"Ruiz\",\n" +
            "    \"firstSurname\": \"Rodriguez\",\n" +
            "    \"secondName\": \"Tatiana\"\n" +
            "}";

    @Test
    public void testGetClientSuccess() throws Exception {
        mockMvc.perform(get("/client/info?type=C&documentNumber="+documentNumber))
                .andExpect(status().isOk())
                .andExpect(content().json(JsonInformation))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testGetClientBadRequestInvalidType() throws Exception {
        mockMvc.perform(get("/client/info?type=O&documentNumber="+documentNumber))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Invalid document type"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testGetClientBadRequestMissingParam() throws Exception {
        mockMvc.perform(get("/client/info?documentNumber="+documentNumber))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Missing type or document number"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testGetClientNotFound() throws Exception {
        mockMvc.perform(get("/client/info?type=c&documentNumber="+12334234))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Client not found"))
                .andDo(MockMvcResultHandlers.print());
    }



}
