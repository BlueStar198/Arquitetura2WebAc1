package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.models.Categoria; 
import com.example.demo.models.Produto; 
import com.example.demo.repositories.CategoriaRepository;
import com.example.demo.repositories.ProdutoRepository;  

import java.util.List;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    @Autowired 
    private ProdutoRepository produtoRepository;

    @Autowired 
    private CategoriaRepository categoriaRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args); 
    }

    @Override
    public void run(String... args) throws Exception {
        

        Categoria eletronicos = new Categoria(null, "Eletrônicos");
        Categoria vestuario = new Categoria(null, "Vestuário");
        categoriaRepository.save(eletronicos); 
        categoriaRepository.save(vestuario);  

        produtoRepository.save(new Produto(null, "Celular", 1500.00, eletronicos));
        produtoRepository.save(new Produto(null, "Notebook", 3000.00, eletronicos));
        produtoRepository.save(new Produto(null, "Camiseta", 50.00, vestuario));

		// Buscar produtos maior que 1000
        System.out.println("Produtos com preço maior que 1000:");
        List<Produto> produtosCaros = produtoRepository.selecionarPrecoMaiorque(1000.00);
        produtosCaros.forEach(p -> System.out.println(p.getNome()));

		// Buscar produtos menor ou igual a 50
        System.out.println("Produtos com preço menor ou igual a 50:");
        List<Produto> produtosBaratos = produtoRepository.selecionarPrecomenorigual(50.00);
        produtosBaratos.forEach(p -> System.out.println(p.getNome()));

        // Buscar por nome que começa com 'C'
        System.out.println("Produtos que começam com 'C':");
        List<Produto> produtosComC = produtoRepository.selecionarNomeespecifico("C");
        produtosComC.forEach(p -> System.out.println(p.getNome()));

        // Buscar categorias que começam com 'E', que no caso seria 'Eletrônicos'
        System.out.println("Categorias que começam com 'E':");
        List<Categoria> categoriasComE = categoriaRepository.findByNomeStartingWith("E");
        categoriasComE.forEach(c -> System.out.println(c.getNome()));

        // Busca categoria com os produtos associados (eletrônicos)
        System.out.println("Categoria com produtos:");
        Categoria categoriaComProdutos = categoriaRepository.findCategoriaFetchProdutos(eletronicos.getId());
        categoriaComProdutos.getProdutos().forEach(p -> System.out.println(p.getNome()));
    }
}
