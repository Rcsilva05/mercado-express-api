package br.com.fiap.mercadoexpress.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidade que representa um item vendido no Mercado Express
 * (meias, produtos de limpeza, frutas, etc).
 *
 * Tabela no banco ORACLE_FIAP: TDS_TB_MERCADO
 * Colunas pedidas no enunciado: Id, Nome, Tipo, Setor, Tamanho, Preco.
 */
@Entity
@Table(name = "TDS_TB_MERCADO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mercado_seq")
    @SequenceGenerator(name = "mercado_seq", sequenceName = "SEQ_TDS_TB_MERCADO", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @NotBlank(message = "nome nao pode ficar em branco")
    @Column(name = "NOME", length = 100, nullable = false)
    private String nome;

    @Column(name = "TIPO", length = 50)
    private String tipo; // ex: alimento, limpeza, vestuario...

    @Column(name = "SETOR", length = 50)
    private String setor; // ex: hortifruti, higiene, bazar...

    @Column(name = "TAMANHO", length = 30)
    private String tamanho; // ex: P/M/G, 1L, 500g, unico...

    @NotNull
    @Positive(message = "preco deve ser maior que zero")
    @Column(name = "PRECO")
    private Double preco;

}
