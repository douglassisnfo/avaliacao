/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pamcary.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Size;

import br.com.pamcary.validacao.CPFCNPFJ;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import javax.validation.constraints.Past;

/**
 *
 * @author DOUGLAS
 */
@Entity(name = "PESSO_FISICA")
public class Pessoa {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="pessoa_sequence")
    @SequenceGenerator(name="pessoa_sequence", sequenceName="sequence_pessoa_fisica")
    private Integer codigo;
    
    @Size(min=10, max=60)
    private String nome;

    @Size(min = 11, max = 14)
    @CPFCNPFJ
    private String cpf;
    
    @Past
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataNascimento;

    public Integer  getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer  codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDateTime getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDateTime dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
