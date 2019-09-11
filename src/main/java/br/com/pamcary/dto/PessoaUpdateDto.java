package br.com.pamcary.dto;

import javax.validation.constraints.Size;

public class PessoaUpdateDto {
	
	@Size(min=10, max=60)
    private String nome;
	
	public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
