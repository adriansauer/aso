package com.py.aso.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.py.aso.dto.RoleDTO;
import com.py.aso.dto.detail.UserDetailDTO;
import com.py.aso.service.UserService;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class UserControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService userService;
	
	final String basePath = "/api/users";
	final String basePathById = "/api/users/{id}";
	
	@Test
	@WithMockUser
	void should_find_by_id() throws Exception{
		List<RoleDTO> list = new ArrayList<>();
		final UserDetailDTO dto = new UserDetailDTO();
		dto.setId(1L);
		dto.setName("ASO");
		dto.setLastname("CVB");
		dto.setUsercode("ASO-123");
		dto.setEmail("aso@aso.com.py");
		dto.setCreatedAt(new Date());
		dto.setUpdatedAt(new Date());
		dto.setDetailId(1L);
		dto.setRoles(list);
		
		
		when(this.userService.findById(dto.getId())).thenReturn(dto);
		
		final ResultActions result = this.mockMvc.perform(get(this.basePathById, dto.getId()));
		
		result.andExpect(status().isOk())//
		.andExpect(jsonPath("$.id", is(dto.getId().intValue())));
	}

}
