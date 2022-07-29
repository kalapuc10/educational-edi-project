package com.sabetski.edi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sabetski.edi.entity.User;
import com.sabetski.edi.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    UserServiceImpl userServiceImpl;

    @Test
    public void userControllerCreateTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/user/create").param("name", "someName").param("login","someLogin").param("email", "someEmail"))
                .andDo(print()).andExpect(status().isOk()).andReturn();

        ArgumentCaptor<String> nameCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> loginCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> emailCaptor = ArgumentCaptor.forClass(String.class);
        verify(userServiceImpl, times(1)).saveUser(nameCaptor.capture(), loginCaptor.capture(), emailCaptor.capture());

        assertThat(nameCaptor.getValue()).isEqualTo("someName");
        assertThat(loginCaptor.getValue()).isEqualTo("someLogin");
        assertThat(emailCaptor.getValue()).isEqualTo("someEmail");
        assertThat(mvcResult.getResponse().getContentAsString()).isEqualTo("create-success");
    }

    @Test
    public void userControllerReadTest() throws Exception {
        User createdUser = new User("someName", "someLogin", "someEmail");
        when(userServiceImpl.getUserById(1)).thenReturn(createdUser);

        MvcResult mvcResult = mockMvc.perform(get("/user/read").param("id", "1"))
                .andDo(print()).andExpect(status().isOk()).andReturn();

        ArgumentCaptor<Integer> idCaptor = ArgumentCaptor.forClass(Integer.class);
        verify(userServiceImpl, times(1)).getUserById(idCaptor.capture());

        assertThat(idCaptor.getValue()).isEqualTo(1);
        assertThat(mvcResult.getResponse().getContentAsString()).isEqualToIgnoringWhitespace(objectMapper.writeValueAsString(createdUser));
    }

    @Test
    public void userControllerUpdateTest() throws Exception {
        User createdUser = new User("anotherName", "someLogin", "someEmail");
        when(userServiceImpl.updateUser(1, "anotherName", "someLogin", "someEmail")).thenReturn(createdUser);

        MvcResult mvcResult = mockMvc.perform(put("/user/update").param("id", "1").param("name", "anotherName").param("login","someLogin").param("email", "someEmail"))
                .andDo(print()).andExpect(status().isOk()).andReturn();

        ArgumentCaptor<Integer> idCaptor = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<String> nameCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> loginCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> emailCaptor = ArgumentCaptor.forClass(String.class);
        verify(userServiceImpl, times(1)).updateUser(idCaptor.capture(), nameCaptor.capture(), loginCaptor.capture(), emailCaptor.capture());

        assertThat(idCaptor.getValue()).isEqualTo(1);
        assertThat(nameCaptor.getValue()).isEqualTo("anotherName");
        assertThat(loginCaptor.getValue()).isEqualTo("someLogin");
        assertThat(emailCaptor.getValue()).isEqualTo("someEmail");
        assertThat(mvcResult.getResponse().getContentAsString()).isEqualToIgnoringWhitespace(objectMapper.writeValueAsString(createdUser));
    }

    @Test
    public void userControllerDeleteTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(delete("/user/delete").param("id", "1"))
                .andDo(print()).andExpect(status().isOk()).andReturn();

        ArgumentCaptor<Integer> idCaptor = ArgumentCaptor.forClass(Integer.class);
        verify(userServiceImpl, times(1)).deleteUserById(idCaptor.capture());

        assertThat(idCaptor.getValue()).isEqualTo(1);
        assertThat(mvcResult.getResponse().getContentAsString()).isEqualTo("delete-success");
    }
}