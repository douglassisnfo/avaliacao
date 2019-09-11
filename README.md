# Avaliação
> Sistema para a valiação que realiza CRUD e busca por cpf.


Sistema baseado em microserviços que realiza um CRUD braseado em um modelo (codigo,nome, cpf, dataNascimento).O sistema dispõe de arquivo V1__criar_e_registrar_pessoa_fisica.sql que cria uma pequena base de dados já com algumas informações.O "código" é inserido de maneria automática com uma sequence contida no arquivo sql.
Na inserção é validado o CPF não permitindo mais de um CPF por cadastro. para a inserção utilizar o PessoaSaveDto. Para a data de nascimento considerar data atual menos um dia. 

Para consultas foi disponibilizado dois endpoints, um para listar todos as pessoas na base e outra para a consulta individual por CPF.
Para deleção é necessário o "código" caso exista o registro e excluído da base, caso contrário registro não encontrado.
Para atualização foi criado o DTO PessoaUpdateDto que permite que apenas nome seja alterado. Os demais dados não podem ser alterados.


## Instalação

```mvn
mvn compile
```
```mvn
mvn test 
```
```mvn
mvn package
```
```mvn
mvn clean install
```


## Exemplo de uso

para instalar em produção é necessário alterar o arquivo de configuração padão do spring.
por padrão está sendo utilizado a porta 8080 e path /pessoa
Usar a collerction Avaliação.postman_collection para acessar os end points

## Configuração para Desenvolvimento

Para instalar em desenvolvimento
Usar a collerction Avaliação.postman_collection para testar os end points


## Histórico de lançamentos

* branch master 
    * Versão atual do sistema 
* branch release 1.0 
    * Primeira versão do sistema 
* branch feature-Controller
    * Criado os endpoints REST seus testes unitários e teste de integração das camadas. 
* branch feature-Servico  
    * Criado algumas regras de negócio e sues teste unitários	
* branch feature-Pessoas
    * Criado o modelo o repositório e seus testes unitários 

## Meta

Douglas marques – douglas.marques@gmail.com

[https://github.com/douglassisnfo/avaliacao](https://github.com/douglassisnfo/avaliacao)


