package com.sandrojam.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sandrojam.cursomc.domain.Categoria;
import com.sandrojam.cursomc.domain.Cidade;
import com.sandrojam.cursomc.domain.Estado;
import com.sandrojam.cursomc.domain.Produto;
import com.sandrojam.cursomc.repositories.CategoriaRepository;
import com.sandrojam.cursomc.repositories.CidadeRepository;
import com.sandrojam.cursomc.repositories.EstadoRepository;
import com.sandrojam.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria ctg1 = new Categoria(null, "Informática");
		Categoria ctg2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		ctg1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		ctg2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(ctg1));
		p2.getCategorias().addAll(Arrays.asList(ctg1, ctg2));
		p3.getCategorias().addAll(Arrays.asList(ctg1));
		
		categoriaRepository.saveAll(Arrays.asList(ctg1, ctg2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

		Estado etd1 = new Estado(null, "Bahia");
		Estado etd2 = new Estado(null, "Pernambuco");
				
		Cidade cdd1 = new Cidade(null, "Juazeiro", etd1);
		Cidade cdd2 = new Cidade(null, "Petrolina", etd2);
		Cidade cdd3 = new Cidade(null, "Recife", etd2);
		
		etd1.getCidades().addAll(Arrays.asList(cdd1));
		etd2.getCidades().addAll(Arrays.asList(cdd2, cdd3));
		
        estadoRepository.saveAll(Arrays.asList(etd1, etd2));
        cidadeRepository.saveAll(Arrays.asList(cdd1, cdd2, cdd3));
	}
}
