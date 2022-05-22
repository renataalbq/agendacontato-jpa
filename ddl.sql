CREATE TABLE CONTATO (ID  SERIAL NOT NULL, NASCIMENTO DATE, NOME VARCHAR(255), VERSAO BIGINT, ENDERECO_ID INTEGER, PRIMARY KEY (ID))
CREATE TABLE TELEFONE (ID  SERIAL NOT NULL, NUMERO VARCHAR(255), VERSAO BIGINT, PRIMARY KEY (ID))
CREATE TABLE ENDERECO (ID  SERIAL NOT NULL, BAIRRO VARCHAR(255), LOGRADOURO TEXT, VERSAO BIGINT, PRIMARY KEY (ID))
CREATE TABLE TELEFONE_CONTATO (contatos_ID INTEGER NOT NULL, telefones_ID INTEGER NOT NULL, PRIMARY KEY (contatos_ID, telefones_ID))
ALTER TABLE CONTATO ADD CONSTRAINT FK_CONTATO_ENDERECO_ID FOREIGN KEY (ENDERECO_ID) REFERENCES ENDERECO (ID)
ALTER TABLE TELEFONE_CONTATO ADD CONSTRAINT FK_TELEFONE_CONTATO_telefones_ID FOREIGN KEY (telefones_ID) REFERENCES TELEFONE (ID)
ALTER TABLE TELEFONE_CONTATO ADD CONSTRAINT FK_TELEFONE_CONTATO_contatos_ID FOREIGN KEY (contatos_ID) REFERENCES CONTATO (ID)
