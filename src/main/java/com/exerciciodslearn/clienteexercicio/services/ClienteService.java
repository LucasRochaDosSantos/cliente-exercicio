package com.exerciciodslearn.clienteexercicio.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exerciciodslearn.clienteexercicio.dto.ClienteDTO;
import com.exerciciodslearn.clienteexercicio.entities.Cliente;
import com.exerciciodslearn.clienteexercicio.repository.ClienteRepository;
import com.exerciciodslearn.clienteexercicio.services.exception.DataBaseException;
import com.exerciciodslearn.clienteexercicio.services.exception.ResourceEntityNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Transactional(readOnly = true)
	public Page<ClienteDTO> findAllPaged(PageRequest pageRequest) {
		Page<Cliente> listaCliente = clienteRepository.findAll(pageRequest);
		return listaCliente.map(x -> new ClienteDTO(x));
	}

	@Transactional(readOnly = true)
	public ClienteDTO findById(Long id) {
		Optional<Cliente> optionalCliente = clienteRepository.findById(id);
		Cliente cliente = optionalCliente.orElseThrow(() -> new ResourceEntityNotFoundException("Entity Not Found"));
		return new ClienteDTO(cliente);
	}

	@Transactional(readOnly = true)
	public ClienteDTO insert(ClienteDTO clienteDTO) {
		Cliente cliente = new Cliente();
	    copyClienteDTO(clienteDTO, cliente);
		cliente = clienteRepository.save(cliente);
		return new ClienteDTO(cliente);
	}

	@Transactional
	public ClienteDTO update(Long id, ClienteDTO clienteDTO) {
		try {
			Cliente cliente = clienteRepository.getReferenceById(id);
			copyClienteDTO(clienteDTO, cliente);
			cliente = clienteRepository.save(cliente);
			return new ClienteDTO(cliente);
		} catch (ResourceEntityNotFoundException erro) {
			throw new ResourceEntityNotFoundException(erro.getMessage());
		}

	}

	public void delete(Long id) {
		try {
			clienteRepository.deleteById(id);
		} catch (EmptyResultDataAccessException erroEmpty) {
			throw new DataBaseException(erroEmpty.getMessage());
		} catch (DataIntegrityViolationException erroIntegrity) {
			throw new DataBaseException(erroIntegrity.getMessage());

		}

	}
	
	private void copyClienteDTO(ClienteDTO clienteDTO, Cliente cliente){
		cliente.setName(clienteDTO.getName());
		cliente.setCpf(clienteDTO.getCpf());
		cliente.setChildren(clienteDTO.getChildren());
		cliente.setBirthDate(clienteDTO.getBirthDate());
		cliente.setIncome(clienteDTO.getIncome());
		
	}
	
}