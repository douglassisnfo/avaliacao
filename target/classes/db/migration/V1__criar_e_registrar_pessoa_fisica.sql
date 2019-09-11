CREATE TABLE PESSO_FISICA(
    CODIGO      SMALLINT       NOT NULL,
    NOME    	VARCHAR(60)      NOT NULL,
    CPF      VARCHAR(11)    NOT NULL,
    DATA_NASCIMENTO		TIMESTAMP,
    PRIMARY KEY(CODIGO)
);


CREATE SEQUENCE sequence_pessoa_fisica
    INCREMENT BY 1
    START WITH 1
    NO MINVALUE
    NO MAXVALUE
    NO CYCLE
    NO CACHE
;

insert into PESSO_FISICA values (sequence_pessoa_fisica.NEXTVAL, 'João da Silva', '46692593859', '2018-12-28 02:00:21');
insert into PESSO_FISICA values (sequence_pessoa_fisica.NEXTVAL, 'Fernando Cardoso', '77934503881', '2018-12-28 02:00:21');
insert into PESSO_FISICA values (sequence_pessoa_fisica.NEXTVAL, 'Gustavo Santos', '02534948857', '2018-12-28 02:00:21');
insert into PESSO_FISICA values (sequence_pessoa_fisica.NEXTVAL, 'Guilherme Bezerra', '19375670872', '2018-12-28 02:00:21');
insert into PESSO_FISICA values (sequence_pessoa_fisica.NEXTVAL, 'Antonio Silveira', '39512532824', '2018-12-28 02:00:21');
