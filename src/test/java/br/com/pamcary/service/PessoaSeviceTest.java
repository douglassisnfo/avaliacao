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
import com.sun.source.tree.AssertTree;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.validation.constraints.AssertTrue;
import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Assert;
import static org.junit.Assert.assertFalse;
import org.mockito.internal.matchers.GreaterThan;

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
                
                Optional<Pessoa> optPessoaId = Optional.of(pessoaBuider());
                Mockito.when(pessoaRepository.findById(1)).thenReturn(optPessoaId);
                
                List<Pessoa> listPessoa = new ArrayList<>();
                listPessoa.add(pessoaBuider());
                Mockito.when(pessoaRepository.findAll()).thenReturn(listPessoa);
                
                Optional<Pessoa> optPessoaCpf = Optional.of(pessoaBuider());
                Mockito.when(pessoaRepository.findByCpf(pessoaBuider().getCpf()))
      		.thenReturn(optPessoaCpf);
	}
	
	@Test
	public void whenSavePessoa() {
		String nome = "Antonio Souza";
		Pessoa pessoa = pessoaService.savePessoa(pessoaBuider());
		assertThat(pessoa.getNome()).isEqualTo(nome);
	}
        
        @Test
	public void whenDeletePessoa() {
            Assert.assertTrue(pessoaService.deletePessoa(1));
	}
        
        @Test
	public void whenDeletePessoaNotFound() {
            Assert.assertFalse(pessoaService.deletePessoa(3));
	}
        
        @Test
	public void whenListPessoa() {
            int listSize = pessoaService.listPessoa().size();
            Assert.assertThat(listSize, org.hamcrest.Matchers.greaterThan(0));
	}
        
        @Test
	public void whenFindByCpf() {
            String nome = "Antonio Souza";
            String cpf = "14762218049";
            String newName = pessoaService.findByCpf(cpf).get().getNome();
            
            assertThat(newName).isEqualTo(nome);
	}
        
        @Test
	public void whenFindByCpfNotFound() {
           String cpf = "63756500039";
           assertFalse(pessoaService.findByCpf(cpf).isPresent());
	}
}
