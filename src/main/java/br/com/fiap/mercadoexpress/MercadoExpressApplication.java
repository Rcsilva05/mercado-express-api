package br.com.fiap.mercadoexpress;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Checkpoint 4 - Parte 1 (API e Deploy) - TDS FIAP
 *
 * API do "Mercado Express" (meias, produtos de limpeza, frutas etc.)
 * Sobe o Tomcat embutido na porta 8082 (ver application.properties).
 */
@SpringBootApplication
public class MercadoExpressApplication {

    public static void main(String[] args) {
        SpringApplication.run(MercadoExpressApplication.class, args);
        System.out.println(">>> Mercado Express API rodando em http://localhost:8082/mercado");
    }

}
