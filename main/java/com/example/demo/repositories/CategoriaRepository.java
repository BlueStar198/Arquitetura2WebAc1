package com.example.demo.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.models.Categoria; 

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    // Busca as categorias que começam com o nome informado
    List<Categoria> findByNomeStartingWith(String nome);

    // LEFT JOIN FETCH para pegar a categoria com os produtos
    @Query("SELECT c FROM Categoria c LEFT JOIN FETCH c.produtos WHERE c.id = :id")
    Categoria findCategoriaFetchProdutos(@Param("id") Long id);
}
