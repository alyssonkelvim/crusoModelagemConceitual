package com.alysson.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alysson.cursomc.domain.Cidade;
import com.alysson.cursomc.domain.Cliente;
import com.alysson.cursomc.domain.Endereco;
import com.alysson.cursomc.domain.enums.Perfil;
import com.alysson.cursomc.domain.enums.TipoCliente;
import com.alysson.cursomc.dto.ClienteDTO;
import com.alysson.cursomc.dto.ClienteNewDTO;
import com.alysson.cursomc.repositories.ClienteRepository;
import com.alysson.cursomc.repositories.EnderecoRepository;
import com.alysson.cursomc.security.UserSS;
import com.alysson.cursomc.services.exceptions.AuthorizationException;
import com.alysson.cursomc.services.exceptions.DataIntegrityException;
import com.alysson.cursomc.services.exceptions.ObjectNotFoundException;



@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;

	public Cliente find(Integer id) {
		
		UserSS user = UserService.authenticated();
		if(user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso Negado");
		}
		
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
	
	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		enderecoRepository.saveAll(obj.getEnderecos());
		return repo.save(obj);
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
		return new Cliente(null, objDTO.getNome(), objDTO.getEmail(), null, null, null);
	}
	
	public Cliente fromDTO(ClienteNewDTO objDTO) {
		
		Cliente cli =  new Cliente(null, objDTO.getNome(), objDTO.getEmail(), objDTO.getCpfOuCnpj(), TipoCliente.toEnum(objDTO.getTipo()), pe.encode(objDTO.getSenha()));
		Endereco end = new Endereco(null, objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getComplemento(), objDTO.getBairro(), objDTO.getCep(), new Cidade(objDTO.getCidadeId(), null, null), cli);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDTO.getTelefone1());
		if(objDTO.getTelefone2() != null) {
			cli.getTelefones().add(objDTO.getTelefone2());
		}
		if(objDTO.getTelefone3() != null) {
			cli.getTelefones().add(objDTO.getTelefone3());
		}
		return cli;
	}
	
	private Cliente updateData(Cliente obj, Cliente oldObj) {
		oldObj.setEmail(obj.getEmail());
		oldObj.setNome(obj.getNome());
		return oldObj;
	}
}
