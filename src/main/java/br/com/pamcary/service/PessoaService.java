package br.com.pamcary.service;

import java.util.List;
import java.util.Optional;

import br.com.pamcary.dto.PessoaSaveDto;
import br.com.pamcary.dto.PessoaUpdateDto;
import br.com.pamcary.model.Pessoa;

/**
*
* @author DOUGLAS
*/
public interface PessoaService {
	
	public Pessoa savePessoa(PessoaSaveDto pessoaSaveDto);
	
	public Pessoa updatePessoa(Integer codigo, PessoaUpdateDto pessoaUpdateDto);
	
	public boolean deletePessoa(Integer codigo);
	
	public List<Pessoa> listPessoa();
	
	public Optional<Pessoa> findByCpf(String cpf);
	
}
