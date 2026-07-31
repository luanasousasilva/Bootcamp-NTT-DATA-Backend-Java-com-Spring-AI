package com.example.taskmanager.infrastructure.http;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;

@SpringBootTest
@ExtendWith(RestDocumentationExtension.class)
class TaskControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp(RestDocumentationContextProvider restDocumentation) {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation))
                .build();
    }

    @Test
    void shouldManageTasksThroughHttpApi() throws Exception {
        String createResponse = mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Estudar Java\",\"description\":\"Capítulo 3\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Estudar Java"))
                .andDo(document("tasks-create"))
                .andReturn().getResponse().getContentAsString();

        String id = com.jayway.jsonpath.JsonPath.read(createResponse, "$.id");

        mockMvc.perform(get("/tasks/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value("Capítulo 3"))
                .andDo(document("tasks-get-by-id"));

        mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Estudar Java"))
                .andDo(document("tasks-list"));

        mockMvc.perform(put("/tasks/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Estudar Spring\",\"description\":\"API REST\",\"status\":\"IN_PROGRESS\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Estudar Spring"))
                .andExpect(jsonPath("$.status").value("IN_PROGRESS"))
                .andDo(document("tasks-update"));

        mockMvc.perform(delete("/tasks/{id}", id))
                .andExpect(status().isNoContent())
                .andDo(document("tasks-delete"));

        mockMvc.perform(get("/tasks/{id}", id))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Task not found: " + id))
                .andDo(document("tasks-not-found"));
    }
}
