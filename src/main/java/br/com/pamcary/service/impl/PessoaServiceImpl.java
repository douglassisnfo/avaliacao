package br.com.pamcary.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.pamcary.model.Pessoa;
import br.com.pamcary.repository.PessoaRepository;
import br.com.pamcary.service.PessoaService;

/**
*
* @author DOUGLAS
*/
public class PessoaServiceImpl implements PessoaService{
	
	@Autowired
	PessoaRepository pessoaRepository;
	
	@Override
	public Pessoa savePessoa(Pessoa pessoa) {
		pessoaRepository.save(pessoa);
		return pessoa;
	}

	@Override
	public Pessoa updatePessoa(Pessoa pessoa) {
		pessoaRepository.save(pessoa);
		return pessoa;
	}

	@Override
	public boolean deletePessoa(Integer codigo) {
                Optional<Pessoa> optPessoa = pessoaRepository.findById(codigo);
                if(optPessoa.isPresent()){
                    pessoaRepository.delete(optPessoa.get());
                    return true;
                }
		return false;
	}

	@Override
	public List<Pessoa> listPessoa() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Pessoa> findByCpf() {
		// TODO Auto-generated method stub
		return null;
	}

}
