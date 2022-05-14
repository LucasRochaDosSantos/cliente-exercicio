package com.exerciciodslearn.clienteexercicio.dto;

import java.math.BigDecimal;
import java.time.Instant;

import com.exerciciodslearn.clienteexercicio.entities.Cliente;

public class ClienteDTO {
	private Long id;
	private String name;
	private String cpf;
	private BigDecimal income;
	private Instant birthDate;
	private Integer children;
	
	public ClienteDTO() {}
	
	
	public ClienteDTO(Cliente cliente) {

		this.id = cliente.getId();
		this.name = cliente.getName();
		this.cpf = cliente.getCpf();
		this.income = cliente.getIncome();
		this.birthDate = cliente.getBirthDate();
		this.children = cliente.getChildren();
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public BigDecimal getIncome() {
		return income;
	}
	public void setIncome(BigDecimal income) {
		this.income = income;
	}
	public Instant getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Instant birthDate) {
		this.birthDate = birthDate;
	}
	public Integer getChildren() {
		return children;
	}
	public void setChildren(Integer children) {
		this.children = children;
	}
	
	
	
}
