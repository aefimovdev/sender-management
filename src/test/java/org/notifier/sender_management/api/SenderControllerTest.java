package org.notifier.sender_management.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.notifier.sender_management.entity.Sender;
import org.notifier.sender_management.service.ISenderService;
import org.notifier.sender_management.service.type.SenderStateType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@WebMvcTest(SenderController.class)
public class SenderControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ISenderService senderServiceMock;

    @Test
    public void getSender() throws Exception {
        Sender sender = new Sender();
        sender.setId(1L);
        sender.setState(SenderStateType.VALIDATED);
        sender.setEmail("mail@mail.ru");
        sender.setName("OOO");

        given(senderServiceMock.findById(sender.getId()))
                .willReturn(Optional.ofNullable(sender));

        mvc.perform(get("/sender/{id}", sender.getId())
                .contentType(APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(jsonPath("state", is(sender.getState().name())))
                .andExpect(jsonPath("email", is(sender.getEmail())))
                .andExpect(jsonPath("name", is(sender.getName())));

        verify(senderServiceMock, times(1)).findById(sender.getId());
        verifyNoMoreInteractions(senderServiceMock);

    }

    @Test
    public void getAll() {
    }

    @Test
    public void create() {
    }

    @Test
    public void validate() {
    }
}