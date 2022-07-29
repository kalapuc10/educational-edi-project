package com.sabetski.edi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sabetski.edi.entity.Document;
import com.sabetski.edi.service.impl.DocumentServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DocumentController.class)
public class DocumentControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    DocumentServiceImpl documentServiceImpl;

    @Test
    public void documentControllerCreateTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/document/create").param("number", "someNumber").param("userId","1"))
                .andDo(print()).andExpect(status().isOk()).andReturn();

        ArgumentCaptor<String> numberCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Integer> userIdCaptor = ArgumentCaptor.forClass(Integer.class);
        verify(documentServiceImpl, times(1)).saveDocument(numberCaptor.capture(), userIdCaptor.capture());

        assertThat(numberCaptor.getValue()).isEqualTo("someNumber");
        assertThat(userIdCaptor.getValue()).isEqualTo(1);
        assertThat(mvcResult.getResponse().getContentAsString()).isEqualTo("create-success");
    }

    @Test
    public void documentControllerReadTest() throws Exception {
        Document createdDocument = new Document("someNumber", null);
        when(documentServiceImpl.getDocumentById(1)).thenReturn(createdDocument);

        MvcResult mvcResult = mockMvc.perform(get("/document/read").param("documentId", "1"))
                .andDo(print()).andExpect(status().isOk()).andReturn();

        ArgumentCaptor<Integer> idCaptor = ArgumentCaptor.forClass(Integer.class);
        verify(documentServiceImpl, times(1)).getDocumentById(idCaptor.capture());

        assertThat(idCaptor.getValue()).isEqualTo(1);
        assertThat(mvcResult.getResponse().getContentAsString()).isEqualToIgnoringWhitespace(objectMapper.writeValueAsString(createdDocument));
    }

    @Test
    public void documentControllerUpdateTest() throws Exception {
        Document createdDocument = new Document("anotherNumber", null);
        when(documentServiceImpl.updateDocument(1, "anotherNumber", 1)).thenReturn(createdDocument);

        MvcResult mvcResult = mockMvc.perform(put("/document/update").param("documentId", "1").param("number", "anotherNumber").param("userId","1"))
                .andDo(print()).andExpect(status().isOk()).andReturn();

        ArgumentCaptor<Integer> idCaptor = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<String> numberCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Integer> userIdCaptor = ArgumentCaptor.forClass(Integer.class);
        verify(documentServiceImpl, times(1)).updateDocument(idCaptor.capture(), numberCaptor.capture(), userIdCaptor.capture());

        assertThat(idCaptor.getValue()).isEqualTo(1);
        assertThat(numberCaptor.getValue()).isEqualTo("anotherNumber");
        assertThat(userIdCaptor.getValue()).isEqualTo(1);
        assertThat(mvcResult.getResponse().getContentAsString()).isEqualToIgnoringWhitespace(objectMapper.writeValueAsString(createdDocument));
    }

    @Test
    public void documentControllerDeleteTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(delete("/document/delete").param("documentId", "1"))
                .andDo(print()).andExpect(status().isOk()).andReturn();

        ArgumentCaptor<Integer> idCaptor = ArgumentCaptor.forClass(Integer.class);
        verify(documentServiceImpl, times(1)).deleteDocumentById(idCaptor.capture());

        assertThat(idCaptor.getValue()).isEqualTo(1);
        assertThat(mvcResult.getResponse().getContentAsString()).isEqualTo("delete-success");
    }
}