package br.com.veiculos.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.veiculos.entity.Veiculo;
import br.com.veiculos.entity.to.VeiculoTO;
import br.com.veiculos.exception.ErroException;
import br.com.veiculos.repository.VeiculoRepository;

@Service
public class VeiculoService {

	@Autowired
	private VeiculoRepository veiculoRepository;

	public Veiculo atualizarCarro(Long id, VeiculoTO veiculoTO) {
		Veiculo veiculoSalvo = veiculoRepository.findById(id)
				.orElseThrow(() -> new ErroException("Veículo não encontrado!"));
		BeanUtils.copyProperties(veiculoTO, veiculoSalvo, "id");
		veiculoSalvo.setUpdated(LocalDate.now());
		return veiculoRepository.save(veiculoSalvo);

	}

	public Veiculo findById(Long id) {
		return veiculoRepository.findById(id)
				.orElseThrow(() -> new ErroException("Veículo não encontrado!"));
	}

	public Veiculo findByParam(String param) {
		Veiculo veiculo = null;
		if (!NumberUtils.isCreatable(param)) {
			veiculo = veiculoRepository.findByParam(param);
		} else {
			veiculo = veiculoRepository.findByAno(Integer.parseInt(param));
		}

		if (null == veiculo) {
			throw new ErroException("Veículo não encontrado!");
		}
		return veiculo;
	}

	public void criar(@Valid VeiculoTO veiculoTO) throws Exception {
		verificarMarca(veiculoTO);
		Veiculo veiculo = new Veiculo();
		BeanUtils.copyProperties(veiculoTO, veiculo, "id", "vendido", "created", "updated");
		veiculo.setCreated(LocalDate.now());
		veiculoRepository.save(veiculo);

	}

	private void verificarMarca(@Valid VeiculoTO veiculoTO) throws Exception {
		if (!veiculoTO.getMarca().equals("Volkswagen") && !veiculoTO.getMarca().equals("Ford") && !veiculoTO.getMarca().equals("Hyundai")
				&& !veiculoTO.getMarca().equals("Renault") && !veiculoTO.getMarca().equals("Honda") && !veiculoTO.getMarca().equals("Chevrolet") && !veiculoTO.getMarca().equals("Fiat")){
			throw new ErroException("Marca inválida! ");
		}

	}

	public List<String> exibirPorDecada() {
		List<Veiculo> veiculos = veiculoRepository.findAll();
		List<String> response = new ArrayList<>();
		response.add("Década de 1990 -> " + veiculos.stream().filter(v -> v.getAno() < 2000).count() + " veículos");
		response.add("Década de 2000 -> " + veiculos.stream().filter(v -> v.getAno() >= 2000 && v.getAno() < 2010).count() + " veículos");
		response.add("Década de 2010 -> " + veiculos.stream().filter(v -> v.getAno() >= 2010 && v.getAno() < 2020).count() + " veículos");
		response.add("Década de 2020 -> " + veiculos.stream().filter(v -> v.getAno() >= 2020).count() + " veículos");
		return response;
	}

	public List<String> exibirPorFabricante() {
		List<Veiculo> veiculos = veiculoRepository.findAll();
		List<String> response = new ArrayList<>();
		response.add("Ford -> " + veiculos.stream().filter(v -> v.getMarca().equals("Ford")).count() + " veículos");
		response.add("Volkswagen -> " + veiculos.stream().filter(v -> v.getMarca().equals("Volkswagen")).count() + " veículos");
		response.add("Hyundai -> " + veiculos.stream().filter(v -> v.getMarca().equals("Hyundai")).count() + " veículos");
		response.add("Renault -> " + veiculos.stream().filter(v -> v.getMarca().equals("Renault")).count() + " veículos");
		response.add("Honda -> " + veiculos.stream().filter(v -> v.getMarca().equals("Honda")).count() + " veículos");
		response.add("Chevrolet -> " + veiculos.stream().filter(v -> v.getMarca().equals("Chevrolet")).count() + " veículos");
		response.add("Fiat -> " + veiculos.stream().filter(v -> v.getMarca().equals("Fiat")).count() + " veículos");
		return response;
	}

	public List<Veiculo> buscarUltimaSemana() {
		List<Veiculo> veiculos = veiculoRepository.findAll();
		return veiculos.stream().filter(v -> LocalDate.now().minusDays(7).compareTo(v.getCreated()) < 1
				|| LocalDate.now().compareTo(v.getCreated()) == 0).collect(Collectors.toList());
	}
	
	public String exibirEstoque(){
		List<Veiculo> veiculos =  veiculoRepository.findAll();
		Long qtEstoque = veiculos.stream().filter(v -> v.isVendido() == false).count();
		return "Número de veículos no estoque: " + qtEstoque;
	}

	public List<Veiculo> findAll() {
		return veiculoRepository.findAll();
	}

	public void deleteById(Long id) {
		veiculoRepository.deleteById(id);
	}

}
