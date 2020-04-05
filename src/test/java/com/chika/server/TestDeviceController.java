package com.chika.server;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ChikaWebServerApplication.class)
@AutoConfigureMockMvc
public class TestDeviceController {

    @Autowired
    private MockMvc mockMvc;

    private final String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNTg2MDEyNzYwLCJleHAiOjE2MTc1NDg3NjB9.LSokqKB1n8G0KEYQsDAwOEvdt1zl4rp_RvD_XLLZnm41CPLDc-ag-AhNLsdAKUJoD0sd20Kre-7PcfFrlUFDjQ";

    @Test
    public void TestGetAllByRoomId() throws Exception {
        String roomId = "b2693580-a57f-4344-a466-ab2ea2aafa1d";
        mockMvc.perform(get("/device/room_id/" + roomId).contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andDo(print());
    }
}
