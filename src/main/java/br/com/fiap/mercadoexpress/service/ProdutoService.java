package br.com.fiap.mercadoexpress.service;

import br.com.fiap.mercadoexpress.controller.dto.ProdutoPatchDTO;
import br.com.fiap.mercadoexpress.exception.ProdutoNaoEncontradoException;
import br.com.fiap.mercadoexpress.model.Produto;
import br.com.fiap.mercadoexpress.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Regra de negocio do CRUD. O enunciado sugere guardar os dados numa lista
 * antes de persistir -> aqui o proprio JpaRepository.save() ja cuida disso
 * (o Hibernate mantem os objetos gerenciados em memoria no Persistence
 * Context antes do commit no Oracle), entao nao duplicamos essa lista na
 * mao pra nao complicar o codigo.
 */
@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository repository;

    public List<Produto> listarTodos() {
        return repository.findAll();
    }

    public Produto buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(id));
    }

    public Produto salvar(Produto produto) {
        // garante que um POST nao venha "roubando" um id que ja existe
        produto.setId(null);
        return repository.save(produto);
    }

    public Produto atualizar(Long id, Produto produtoAtualizado) {
        Produto existente = buscarPorId(id);

        existente.setNome(produtoAtualizado.getNome());
        existente.setTipo(produtoAtualizado.getTipo());
        existente.setSetor(produtoAtualizado.getSetor());
        existente.setTamanho(produtoAtualizado.getTamanho());
        existente.setPreco(produtoAtualizado.getPreco());

        return repository.save(existente);
    }

    public Produto atualizarParcial(Long id, ProdutoPatchDTO dto) {
        Produto existente = buscarPorId(id);

        if (dto.getNome() != null) existente.setNome(dto.getNome());
        if (dto.getTipo() != null) existente.setTipo(dto.getTipo());
        if (dto.getSetor() != null) existente.setSetor(dto.getSetor());
        if (dto.getTamanho() != null) existente.setTamanho(dto.getTamanho());
        if (dto.getPreco() != null) existente.setPreco(dto.getPreco());

        return repository.save(existente);
    }

    public void deletar(Long id) {
        // se nao existir, avisa antes de tentar excluir (evita erro generico do JPA)
        buscarPorId(id);
        repository.deleteById(id);
    }

}
