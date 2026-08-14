package br.com.fiap.mercadoexpress.repository;

import br.com.fiap.mercadoexpress.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio JPA do Produto. O Spring Data ja gera o CRUD basico
 * (save, findById, findAll, deleteById...) por baixo dos panos usando
 * o EntityManager, entao nao precisamos escrever SQL na mao aqui.
 */
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    // Consulta extra, so pra facilitar caso queiram filtrar por setor no front
    List<Produto> findBySetorIgnoreCase(String setor);

}
