package br.com.fiap.mercadoexpress.exception;

/**
 * Disparada quando o Postman/Insomnia pede um produto (GET, PUT, PATCH ou
 * DELETE) com um Id que nao existe na TDS_TB_MERCADO.
 */
public class ProdutoNaoEncontradoException extends RuntimeException {

    public ProdutoNaoEncontradoException(Long id) {
        super("Produto com id " + id + " nao encontrado no Mercado Express");
    }

}
