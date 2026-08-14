package br.com.fiap.mercadoexpress.controller.dto;

import lombok.Data;

/**
 * DTO usado no PATCH. Ao contrario do PUT (que exige o objeto inteiro),
 * aqui todo campo e opcional -> so atualizamos o que vier preenchido
 * no JSON (ver ProdutoService.atualizarParcial).
 */
@Data
public class ProdutoPatchDTO {

    private String nome;
    private String tipo;
    private String setor;
    private String tamanho;
    private Double preco;

}
