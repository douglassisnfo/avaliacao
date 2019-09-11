package br.com.pamcary.controller;

import br.com.pamcary.model.Pessoa;
import br.com.pamcary.service.PessoaService;
import ch.qos.logback.core.net.server.Client;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    PessoaService pessoaService;

    @Autowired
    MessageSource messageSource;

    private String errorFields(BindingResult bindingResult) {
        String messages = "Inválid Fields \n";
        messages += bindingResult.getAllErrors().stream().filter((object) -> (object instanceof FieldError))
                .map((object) -> (FieldError) object).map((fieldError)
                -> fieldError.getField() + " - " + messageSource.getMessage(fieldError, null) + "\n")
                .reduce(messages, String::concat);
        return messages;
    }

    @PostMapping
    public ResponseEntity savePessoa(@Valid @RequestBody Pessoa pessoa, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorFields(bindingResult));
        }
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(pessoaService.savePessoa(pessoa));
    }

    @PutMapping
    public ResponseEntity updatePessoa(@Valid @RequestBody Pessoa pessoa, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorFields(bindingResult));
        }
        return null;
    }

    @DeleteMapping
    public ResponseEntity deletePessoa(Integer codigo) {
        if (pessoaService.deletePessoa(codigo)) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/{cpf}")
    public ResponseEntity<Object> findByCpf(@PathVariable String cpf) {
        Optional<Pessoa> optPessoa = pessoaService.findByCpf(cpf);
        if(optPessoa.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(optPessoa.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity listPessoa() {
        List<Pessoa> listPessoa = pessoaService.listPessoa();
        if (listPessoa.size() > 0) {
            return ResponseEntity.status(HttpStatus.OK).body(listPessoa);
        }
        return ResponseEntity.notFound().build();
    }
}
