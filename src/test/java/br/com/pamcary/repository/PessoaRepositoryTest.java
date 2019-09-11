package br.com.pamcary.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.pamcary.model.Pessoa;
import java.time.LocalDateTime;

/**
*
* @author DOUGLAS
*/
@RunWith(SpringRunner.class)
@DataJpaTest
public class PessoaRepositoryTest {
	
	@Autowired
    private TestEntityManager entityManager;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	private Pessoa pessoaBuider() {
		Pessoa pessoa = new Pessoa();
		
		pessoa.setCpf("14762218049");
		pessoa.setNome("Antonio Souza");
		pessoa.setDataNascimento(LocalDateTime.now());
		return pessoa;
	}
	
	@Before
	public void setUp() {
		
		entityManager.persist(pessoaBuider());
	    entityManager.flush();
	}
	
	@Test
	public void whenFindByCpf() {
		
		String cpf = "14762218049";
		String nome = "Antonio Souza";
		Optional<Pessoa> optPessoa = pessoaRepository.findByCpf(cpf);

		assertThat(optPessoa.get().getNome())
	      .isEqualTo(nome);
	}
	
	@Test
	public void whenFindByCpfNotFound() {
		
		String cpf = "63756500039";
		Optional<Pessoa> optPessoa = pessoaRepository.findByCpf(cpf);
		assertFalse(optPessoa.isPresent());
	}
}
