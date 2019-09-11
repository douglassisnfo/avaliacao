/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pamcary.controller;

import br.com.pamcary.model.Pessoa;
import br.com.pamcary.service.PessoaService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.BDDMockito.given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @author DOUGLAS
 */
@RunWith(SpringRunner.class)
@WebMvcTest(PessoaController.class)
public class PessoaControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    PessoaService pessoaService;

    private Pessoa pessoaBuider() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	String date = "2019-09-09 22:20:00";
	LocalDateTime localDate = LocalDateTime.parse(date, formatter);
        
        Pessoa pessoa = new Pessoa();
        pessoa.setCodigo(1);
        pessoa.setCpf("14762218049");
        pessoa.setNome("Antonio Souza");
        pessoa.setDataNascimento(localDate);
        return pessoa;
    }
    
    private List<Pessoa> listPessoa = new ArrayList<>();
    
    
    @Before
    public void setUp() {
        given(pessoaService.savePessoa(pessoaBuider())).willReturn(pessoaBuider());
        
       listPessoa.add(pessoaBuider());
       given(pessoaService.listPessoa()).willReturn(listPessoa);
    }
    
    private String savePessoa = "{\n" +
"	\"codigo\":1,\n" +
"	\"nome\":\"Antonio Souza\",\n" +
"	\"cpf\":\"147.622.180-49\",\n" +
"	\"dataNascimento\":\"2019-09-09 22:20:00\"\n" +
"}";
    private String savePessoaInvalidData = "{\n" +
"	\"codigo\":1,\n" +
"	\"nome\":\"Antonio Souza\",\n" +
"	\"cpf\":\"000.622.180-49\",\n" +
"	\"dataNascimento\":\"2019-09-09 22:20:00\"\n" +
"}";
    
    @Test
    public void whenSavePessoa(){
        String url = "/pessoa";
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
                .post(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(savePessoa);
        try {
            this.mvc.perform(builder)
                    .andExpect(status().isCreated())
                    .andReturn();
        } catch (Exception ex) {
            Logger.getLogger(PessoaControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void whenSavePessoaInvalidData(){
        String url = "/pessoa";
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
                .post(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(savePessoaInvalidData);
        try {
            this.mvc.perform(builder)
                    .andExpect(status().isBadRequest())
                    .andReturn();
        } catch (Exception ex) {
            Logger.getLogger(PessoaControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void whenListPessoa(){
        String url = "/pessoa";
        try {
            this.mvc.perform(get(url))
                    .andExpect(status().isOk())
                    .andReturn();
        } catch (Exception ex) {
            Logger.getLogger(PessoaControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
