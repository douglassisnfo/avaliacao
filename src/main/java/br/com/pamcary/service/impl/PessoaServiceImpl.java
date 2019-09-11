package br.com.pamcary.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pamcary.dto.PessoaSaveDto;
import br.com.pamcary.dto.PessoaUpdateDto;
import br.com.pamcary.model.Pessoa;
import br.com.pamcary.repository.PessoaRepository;
import br.com.pamcary.service.PessoaService;
import br.com.pamcary.validacao.CPFCNPJValidator;

/**
*
* @author DOUGLAS
*/
@Service
public class PessoaServiceImpl implements PessoaService{
	
	@Autowired
	PessoaRepository pessoaRepository;
	
	private PessoaSaveDto cleanMaskCpf(PessoaSaveDto pessoaSaveDto) {
		String cpf = pessoaSaveDto.getCpf().replace(".", "").replace("-", "");
		pessoaSaveDto.setCpf(cpf);
		return pessoaSaveDto;
	}
	
	private Pessoa pessoaBuiderSave(PessoaSaveDto pessoaSaveDto) {
		pessoaSaveDto = cleanMaskCpf( pessoaSaveDto);
		
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf(pessoaSaveDto.getCpf());
		pessoa.setNome(pessoaSaveDto.getNome());
		pessoa.setDataNascimento(pessoaSaveDto.getDataNascimento());
		return pessoa;
	}
	
	@Override
	public Pessoa savePessoa(PessoaSaveDto pessoaSaveDto) {
		Pessoa pessoa = pessoaBuiderSave(pessoaSaveDto);
		Optional<Pessoa> optPessoa = pessoaRepository.findByCpf(pessoa.getCpf());
		if(!optPessoa.isPresent()) {
			pessoaRepository.save(pessoa);
			return pessoa;
		}
		
		return null;
	}
	
	
	private Pessoa pessoaBuiderUpdate(Pessoa pessoa, PessoaUpdateDto pessoaUpdateDto) {
		pessoa.setNome(pessoaUpdateDto.getNome());
		return pessoa;
	}
	
	@Override
	public Pessoa updatePessoa(Integer codigo, PessoaUpdateDto pessoaUpdateDto) {
		 Optional<Pessoa> optPessoa = pessoaRepository.findById(codigo);
         
		 if(optPessoa.isPresent()){
			 Pessoa newPessoa = pessoaBuiderUpdate(optPessoa.get(), pessoaUpdateDto);
        	 pessoaRepository.save(newPessoa);
        	 return newPessoa;
         }
		
		return null;
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
		return pessoaRepository.findAll();
	}

	@Override
	public Optional<Pessoa> findByCpf(String cpf) {
            CPFCNPJValidator cpfcnpjv = new CPFCNPJValidator();
            if(cpfcnpjv.isValid(cpf, null)){
                return pessoaRepository.findByCpf(cpf);
            }
            return Optional.empty();
	}

}
