package br.com.pamcary.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pamcary.model.Pessoa;

/**
*
* @author DOUGLAS
*/
public interface PessoaRepository extends JpaRepository<Pessoa, UUID> {
	
	public Optional<Pessoa> findByCpf(String cpf);

}
