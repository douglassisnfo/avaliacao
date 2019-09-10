/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pamcary.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.pamcary.validacao.CPFCNPFJ;

/**
 *
 * @author DOUGLAS
 */
@Entity(name = "PESSO_FISICA")
public class Pessoa {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="pessoa_sequence")
    @SequenceGenerator(name="pessoa_sequence", sequenceName="sequence_pessoa_fisica")
    private int codigo;
    
    @Size(min=10, max=60)
    private String nome;

    @Size(min = 11, max = 14)
    @CPFCNPFJ
    private String cpf;
    
    @DateTimeFormat(pattern = "yyyy/MM/dd hh:mm:ss")
    private LocalDate dataNascimento;

    public int  getCodigo() {
        return codigo;
    }

    public void setCodigo(int  codigo) {
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

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
