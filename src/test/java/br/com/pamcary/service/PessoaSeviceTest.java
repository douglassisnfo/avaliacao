package br.com.pamcary.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.pamcary.model.Pessoa;
import br.com.pamcary.repository.PessoaRepository;
import br.com.pamcary.service.impl.PessoaServiceImpl;

/**
*
* @author DOUGLAS
*/
@RunWith(SpringRunner.class)
public class PessoaSeviceTest {
	
	@TestConfiguration
    static class PessoaSeviceTestContextConfiguration {
        @Bean
        public PessoaService pessoaService() {
            return new PessoaServiceImpl();
        }
    }
	
	@Autowired
    private PessoaService pessoaService;
	
	@MockBean
    private PessoaRepository pessoaRepository;
	
	private Pessoa pessoaBuider() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCodigo(1);
		pessoa.setCpf("14762218049");
		pessoa.setNome("Antonio Souza");
		pessoa.setDataNascimento(LocalDate.now());
		return pessoa;
	}
	
	
	@Before
	public void setUp() {
		Pessoa pSave = pessoaBuider();
		Mockito.when(pessoaRepository.save(pSave)).thenReturn(pSave);
	}
	
	
	@Test
	public void whenSavePessoa() {
		String nome = "Antonio Souza";
		Pessoa pessoa = pessoaService.savePessoa(pessoaBuider());
		assertThat(pessoa.getNome()).isEqualTo(nome);
	}
}
