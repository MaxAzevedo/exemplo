package br.com.exemplo.controller;


import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.com.exemplo.controller.ClienteController;
import br.com.exemplo.model.Cliente;
import br.com.exemplo.repository.ClienteRepository;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.google.gson.Gson;

@RunWith(SpringRunner.class)
@WebMvcTest(ClienteController.class)
public class ClienteControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private ClienteRepository clienteRepository;
	
	private Cliente cliente1;
	private Cliente cliente2;
	private Cliente cliente3;
	private List<Cliente> clientes;
	
	@Autowired
	private Gson gson;

	@Before
	public void init(){
		cliente1 = new Cliente("Luke Skywalker", "luke@gmail.com", "83988888888");
		cliente2 = new Cliente("Mestre Yoda", "yoda@gmail.com", "83977777777");
		cliente3 = new Cliente("Princesa Leia", "leia@gmail.com", "83966666666");
		clientes = Arrays.asList(
				cliente1, 
				cliente2);
	}

	@Test
	public void listarClientesTest() throws Exception {
		when(clienteRepository.findAll()).thenReturn(clientes);
		mvc.perform(get("/api/clientes")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", hasSize(2)))
		.andExpect(jsonPath("$[0].nome", is("Luke Skywalker")));

	}

	@Test
	public void buscarClienteTest() throws Exception {
		when(clienteRepository.buscarCliente("yoda@gmail.com")).thenReturn(cliente2);
		mvc.perform(get("/api/cliente?email=yoda@gmail.com")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.nome", is("Mestre Yoda")));
	}

	@Test
	public void cadastrarClienteTest() throws Exception {
		
		when(clienteRepository.save(cliente3)).thenReturn(cliente3);
		mvc.perform(
				post("/api/cliente")
				.contentType(MediaType.APPLICATION_JSON)
				.content(gson.toJson(cliente3)))
		.andExpect(status().isOk());

	}
	
	@Test
	public void editarClienteTest() throws Exception {
		Cliente cliente3_alterado = new Cliente("Princesa Leia Sombria", "leia@gmail.com", "83966666666");
		when(clienteRepository.buscarCliente("leia@gmail.com")).thenReturn(cliente3);
		when(clienteRepository.save(cliente3)).thenReturn(cliente3_alterado);
		mvc.perform(
				put("/api/cliente")
				.contentType(MediaType.APPLICATION_JSON)
				.content(gson.toJson(cliente3)))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.nome", is("Princesa Leia Sombria")));
	}
}
