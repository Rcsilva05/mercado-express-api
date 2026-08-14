package br.com.fiap.mercadoexpress.controller;

import br.com.fiap.mercadoexpress.model.Produto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Monta os links HATEOAS de cada produto (maturidade nivel 3 - Richardson).
 * Alem do "self", devolvemos o link pra colecao inteira e pro delete, pra
 * quem consumir a API saber quais acoes existem sem precisar consultar a
 * documentacao toda hora.
 */
@Component
public class ProdutoModelAssembler implements RepresentationModelAssembler<Produto, EntityModel<Produto>> {

    @Override
    public EntityModel<Produto> toModel(Produto produto) {
        return EntityModel.of(produto,
                linkTo(methodOn(MercadoController.class).buscarPorId(produto.getId())).withSelfRel(),
                linkTo(methodOn(MercadoController.class).listarTodos()).withRel("mercado"),
                linkTo(methodOn(MercadoController.class).deletar(produto.getId())).withRel("excluir"),
                linkTo(methodOn(MercadoController.class).atualizar(produto.getId(), null)).withRel("atualizar")
        );
    }

}
