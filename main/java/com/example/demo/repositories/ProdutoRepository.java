package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.models.Produto; 

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    // Consulta para achar os produtos com preço maior que um valor
    @Query("SELECT p FROM Produto p WHERE p.preco > :valor")
    List<Produto> selecionarPrecoMaiorque(@Param("valor") Double valor);

    // Consulta para preço menor ou igual
    @Query("SELECT p FROM Produto p WHERE p.preco <= :valor")
    List<Produto> selecionarPrecomenorigual(@Param("valor") Double valor);

    // Buscar produtos que começam com um nome específico
    @Query("SELECT p FROM Produto p WHERE p.nome LIKE :nome%")
    List<Produto> selecionarNomeespecifico(@Param("nome") String nome);
}
