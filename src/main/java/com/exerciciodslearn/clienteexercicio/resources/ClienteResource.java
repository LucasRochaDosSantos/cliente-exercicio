package com.exerciciodslearn.clienteexercicio.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.exerciciodslearn.clienteexercicio.dto.ClienteDTO;
import com.exerciciodslearn.clienteexercicio.services.ClienteService;

@RestController
@RequestMapping(value="/cliente")
public class ClienteResource {
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	public ResponseEntity<Page<ClienteDTO>> findAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "DESC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Page<ClienteDTO> listaClienteDTO = clienteService.findAllPaged(pageRequest);
		return ResponseEntity.ok().body(listaClienteDTO);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> findById(@PathVariable Long id) {
		ClienteDTO clienteDTO = clienteService.findById(id);
		return ResponseEntity.ok().body(clienteDTO);
	}

	@PostMapping
	public ResponseEntity<ClienteDTO> insert(@RequestBody ClienteDTO clienteDTO) {
		clienteDTO = clienteService.insert(clienteDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(clienteDTO.getId())
				.toUri();
		return ResponseEntity.created(uri).body(clienteDTO);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> update(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO) {
		clienteDTO = clienteService.update(id, clienteDTO);
		return ResponseEntity.ok().body(clienteDTO);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> delete(@PathVariable Long id) {
		clienteService.delete(id);
		return ResponseEntity.noContent().build();
	}
	

}
