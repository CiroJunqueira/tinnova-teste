package br.com.veiculos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.veiculos.entity.Veiculo;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long>{

	@Query("select v from Veiculo v where v.veiculo = :param or v.marca = :param")
	Veiculo findByParam(@Param("param") String param);
	
	@Query("select v from Veiculo v where v.ano = :param")
	Veiculo findByAno(@Param("param") Integer param);

}
