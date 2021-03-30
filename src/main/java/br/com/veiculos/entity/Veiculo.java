package br.com.veiculos.entity;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Veiculo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "VEICULO")
	private String veiculo;
	
	@Column(name = "MARCA")
	private String marca;
	
	@Column(name = "ANO")
	private Integer ano;
	
	@Column(name = "DESCRICAO")
	private String descricao;
	
	@Column(name = "VENDIDO")
	private boolean vendido;
	
	@Column(name = "CREATED")
	@JsonSerialize(as = Date.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate created;
	
	@Column(name = "UPDATED")
	private LocalDate updated;
	
}
