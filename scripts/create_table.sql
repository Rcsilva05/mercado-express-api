-- =========================================================
-- Mercado Express - script manual (rodar no SQL Developer
-- se o Hibernate nao tiver permissao de DDL no ORACLE_FIAP)
-- =========================================================

-- Tabela principal
CREATE TABLE TDS_TB_MERCADO (
    ID      NUMBER(19,0) PRIMARY KEY,
    NOME    VARCHAR2(100) NOT NULL,
    TIPO    VARCHAR2(50),
    SETOR   VARCHAR2(50),
    TAMANHO VARCHAR2(30),
    PRECO   NUMBER(10,2)
);

-- Sequence usada pelo Hibernate (GenerationType.SEQUENCE) pra gerar o Id
CREATE SEQUENCE SEQ_TDS_TB_MERCADO
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

-- Alguns registros de exemplo pra testar o GET antes de fazer POST
INSERT INTO TDS_TB_MERCADO (ID, NOME, TIPO, SETOR, TAMANHO, PRECO)
VALUES (SEQ_TDS_TB_MERCADO.NEXTVAL, 'Meia Cano Alto', 'Vestuario', 'Bazar', 'Unico', 12.90);

INSERT INTO TDS_TB_MERCADO (ID, NOME, TIPO, SETOR, TAMANHO, PRECO)
VALUES (SEQ_TDS_TB_MERCADO.NEXTVAL, 'Detergente Neutro', 'Limpeza', 'Higiene', '500ml', 3.49);

INSERT INTO TDS_TB_MERCADO (ID, NOME, TIPO, SETOR, TAMANHO, PRECO)
VALUES (SEQ_TDS_TB_MERCADO.NEXTVAL, 'Maca Fuji', 'Alimento', 'Hortifruti', 'Kg', 8.99);

COMMIT;
