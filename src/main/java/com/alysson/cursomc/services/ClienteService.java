package com.alysson.cursomc.services;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.alysson.cursomc.domain.Cliente;
import com.alysson.cursomc.dto.ClienteDTO;
import com.alysson.cursomc.repositories.ClienteRepository;
import com.alysson.cursomc.services.exceptions.DataIntegrityException;
import com.alysson.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;

	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
	
	public Cliente update(Cliente obj) {
		Cliente oldObj = find(obj.getId());
		obj = updateData(obj, oldObj);
		return repo.save(obj);
	}



	public void delete(Integer id) {
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um cliente que possui pedidos");
		}
	}
	
	public List<Cliente> findAll(){
		return repo.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest =  PageRequest.of(page, linesPerPage, Direction.valueOf(direction),orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO objDTO) {
		return new Cliente(null, objDTO.getNome(), objDTO.getEmail(), null, null);
	}
	
	private Cliente updateData(Cliente obj, Cliente oldObj) {
		oldObj.setEmail(obj.getEmail());
		oldObj.setNome(obj.getNome());
		return oldObj;
	}
}
