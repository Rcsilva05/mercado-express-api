package br.com.fiap.mercadoexpress.controller;

import br.com.fiap.mercadoexpress.controller.dto.ProdutoPatchDTO;
import br.com.fiap.mercadoexpress.model.Produto;
import br.com.fiap.mercadoexpress.service.ProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Endpoints do Mercado Express.
 *
 * Base: http://localhost:8082/mercado
 *
 * GET    /mercado         -> lista tudo
 * GET    /mercado/{id}    -> busca 1 produto
 * POST   /mercado         -> cria produto novo
 * PUT    /mercado/{id}    -> atualiza o produto inteiro
 * PATCH  /mercado/{id}    -> atualiza so os campos enviados
 * DELETE /mercado/{id}    -> exclui pelo id
 */
@RestController
@RequestMapping("/mercado")
@RequiredArgsConstructor
public class MercadoController {

    private final ProdutoService service;
    private final ProdutoModelAssembler assembler;

    @GetMapping
    public CollectionModel<EntityModel<Produto>> listarTodos() {
        List<EntityModel<Produto>> produtos = service.listarTodos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(produtos,
                linkTo(methodOn(MercadoController.class).listarTodos()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<Produto> buscarPorId(@PathVariable Long id) {
        Produto produto = service.buscarPorId(id);
        return assembler.toModel(produto);
    }

    @PostMapping
    public ResponseEntity<EntityModel<Produto>> criar(@Valid @RequestBody Produto produto) {
        Produto salvo = service.salvar(produto);
        EntityModel<Produto> model = assembler.toModel(salvo);

        URI location = linkTo(methodOn(MercadoController.class).buscarPorId(salvo.getId())).toUri();
        return ResponseEntity.created(location).body(model);
    }

    @PutMapping("/{id}")
    public EntityModel<Produto> atualizar(@PathVariable Long id, @Valid @RequestBody Produto produto) {
        Produto atualizado = service.atualizar(id, produto);
        return assembler.toModel(atualizado);
    }

    @PatchMapping("/{id}")
    public EntityModel<Produto> atualizarParcial(@PathVariable Long id, @RequestBody ProdutoPatchDTO dto) {
        Produto atualizado = service.atualizarParcial(id, dto);
        return assembler.toModel(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
