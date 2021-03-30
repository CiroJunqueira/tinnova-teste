package br.com.veiculos.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.veiculos.entity.Veiculo;
import br.com.veiculos.entity.to.VeiculoTO;
import br.com.veiculos.service.VeiculoService;

@RestController
@RequestMapping(value = "/veiculos")
public class VeiculoController {
	
	@Autowired
	private VeiculoService veiculoService;

	@GetMapping
	public ResponseEntity<List<Veiculo>> listar(){
		List<Veiculo> veiculos = veiculoService.findAll();
		return ResponseEntity.ok(veiculos);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Veiculo> buscarPeloId(@PathVariable Long id){
		Veiculo veiculo = veiculoService.findById(id);
		return ResponseEntity.ok(veiculo);
	}
	
	@GetMapping("/ultimaSemana")
	public ResponseEntity<List<Veiculo>> buscarUltimaSemana(){
		List<Veiculo> veiculos = veiculoService.buscarUltimaSemana();
		return ResponseEntity.ok(veiculos);
	}
	
	@GetMapping("/find")
	public ResponseEntity<Veiculo> buscarPeloParam(@RequestParam(value = "param") String param){
		Veiculo veiculo = veiculoService.findByParam(param);
		return ResponseEntity.ok(veiculo);
	}
	
	@GetMapping("/decada")
	public ResponseEntity<List<String>> exibirPorDecada(){
		List<String> decadas = veiculoService.exibirPorDecada();
		return ResponseEntity.ok(decadas);
	}
	
	@GetMapping("/fabricante")
	public ResponseEntity<List<String>> exibirPorFabricante(){
		List<String> fabricantes = veiculoService.exibirPorFabricante();
		return ResponseEntity.ok(fabricantes);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<VeiculoTO> criar(@Valid @RequestBody VeiculoTO veiculoTO) throws Exception{
		veiculoService.criar(veiculoTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().buildAndExpand(veiculoTO).toUri();
		return ResponseEntity.created(uri).body(veiculoTO);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> remover(@PathVariable Long id) {
		veiculoService.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Veiculo> atualizar(@PathVariable Long id, @RequestBody VeiculoTO veiculoTO) throws Exception{
		Veiculo veiculoSalvo = veiculoService.atualizarCarro(id, veiculoTO);
		return ResponseEntity.ok(veiculoSalvo);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<Veiculo> atualizarPatch(@PathVariable Long id, @RequestBody VeiculoTO veiculoTO) throws Exception{
		Veiculo veiculoSalvo = veiculoService.atualizarCarro(id, veiculoTO);
		return ResponseEntity.ok(veiculoSalvo);
	}
	
	@GetMapping("/vendas")
	public ResponseEntity<String> exibirEstoque(){
		String qtEstoque = veiculoService.exibirEstoque();
		return ResponseEntity.ok(qtEstoque);
	}
	
	
}
