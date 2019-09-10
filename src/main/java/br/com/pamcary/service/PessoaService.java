package br.com.pamcary.service;

import java.util.List;
import java.util.Optional;

import br.com.pamcary.model.Pessoa;

/**
*
* @author DOUGLAS
*/
public interface PessoaService {
	
	public Pessoa savePessoa(Pessoa pessoa);
	
	public Pessoa updatePessoa(Pessoa pessoa);
	
	public boolean deletePessoa(Integer codigo);
	
	public List<Pessoa> listPessoa();
	
	public Optional<Pessoa> findByCpf(String cpf);
	
}
