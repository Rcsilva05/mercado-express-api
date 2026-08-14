package br.com.fiap.mercadoexpress;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Teste basico so pra garantir que o contexto do Spring sobe sem erro
 * (todas as beans - Controller, Service, Repository - se encaixando certinho).
 * Pra rodar de verdade precisa da conexao com o Oracle configurada.
 */
@SpringBootTest
class MercadoExpressApplicationTests {

    @Test
    void contextLoads() {
    }

}
