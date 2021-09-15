
package com.neewrobert.superuser.controller;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.neewrobert.superuser.service.UserService;

@SpringBootTest(classes = UserController.class)
@ComponentScan("com.neewrobert.superuser")
@EntityScan("com.neewrobert.superuser")
public class UserControllerTest {

	MockMvc mockMvc;

	@Mock
	private UserService userService;

	@InjectMocks()
	private UserController userController;

	public final String REST_SERVICE_URI = "http://localhost:8081/users";

	@BeforeEach
	public void setup() {

		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

	}

	@org.junit.jupiter.api.Test
	public void shouldCreateUser() throws Exception {

		String json = "{\"name\":\"John Doe\",\"birthDate\":\"1991-07-28\",\"profileType\":\"manager\",\"phoneNumber\":\"987411289\",\"email\":\"johndoe@gmail.com\"}";

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post(REST_SERVICE_URI)
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(json);

		mockMvc.perform(mockRequest).andExpect(MockMvcResultMatchers.status().isCreated());

	}

}
