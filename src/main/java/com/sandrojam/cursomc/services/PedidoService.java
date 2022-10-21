package com.sandrojam.cursomc.services;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sandrojam.cursomc.domain.Pedido;
import com.sandrojam.cursomc.repositories.PedidoRepository;
import com.sandrojam.cursomc.services.exceptions.ObjectNotFoundException; //import org.hibernate.ObjectNotFoundException; <-- ERRADO

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;
	
	//Exceção caso o ID não exista
	
	public Pedido buscar(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName(), null));
	}
}