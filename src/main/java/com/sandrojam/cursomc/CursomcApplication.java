package com.sandrojam.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sandrojam.cursomc.domain.Categoria;
import com.sandrojam.cursomc.domain.Cidade;
import com.sandrojam.cursomc.domain.Cliente;
import com.sandrojam.cursomc.domain.Endereco;
import com.sandrojam.cursomc.domain.Estado;
import com.sandrojam.cursomc.domain.Pagamento;
import com.sandrojam.cursomc.domain.PagamentoComBoleto;
import com.sandrojam.cursomc.domain.PagamentoComCartao;
import com.sandrojam.cursomc.domain.Pedido;
import com.sandrojam.cursomc.domain.Produto;
import com.sandrojam.cursomc.domain.enums.EstadoPagamento;
import com.sandrojam.cursomc.domain.enums.TipoCliente;
import com.sandrojam.cursomc.repositories.CategoriaRepository;
import com.sandrojam.cursomc.repositories.CidadeRepository;
import com.sandrojam.cursomc.repositories.ClienteRepository;
import com.sandrojam.cursomc.repositories.EnderecoRepository;
import com.sandrojam.cursomc.repositories.EstadoRepository;
import com.sandrojam.cursomc.repositories.PagamentoRepository;
import com.sandrojam.cursomc.repositories.PedidoRepository;
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
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
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
        
        Cliente c1 = new Cliente(null, "Sandro Muniz", "sandro.mnz@gmail.com", "111222333-00", TipoCliente.PESSOAFISICA);
        
        c1.getTelefones().addAll(Arrays.asList("81988889999", "81977775555"));
        
        Endereco e1 = new Endereco(null, "Rua J A Bezerra", "100", "Galpao", "Guararapes", "54325610", c1, cdd1);
        Endereco e2 = new Endereco(null, "Rua M Luisa", "30", "1-Andar", "Bandeirante", "53340100", c1, cdd2);
        
        c1.getEnderecos().addAll(Arrays.asList(e1, e2));
        
        clienteRepository.saveAll(Arrays.asList(c1));
        enderecoRepository.saveAll(Arrays.asList(e1, e2));
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm"); //Instancear um objeto auxiliar para formatar uma data
        
        Pedido ped1 = new Pedido(null, sdf.parse("14/10/2022 12:50"), c1, e1);
        Pedido ped2 = new Pedido(null, sdf.parse("14/10/2022 13:25"), c1, e2);
        
        Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 3);
        ped1.setPagamento(pagto1);
        
        Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("14/11/2022 00:00"), null);
        ped2.setPagamento(pagto2);
        
        c1.getPedidos().addAll(Arrays.asList(ped1, ped2));
        
        pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
        pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
        
	}
}
