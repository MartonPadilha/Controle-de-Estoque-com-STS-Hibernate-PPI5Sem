package com.marton.trabalho.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marton.trabalho.Model.Produto;
import com.marton.trabalho.Repository.ProdutoRepository;

@RestController
@RequestMapping({"/produtos"})
@CrossOrigin(origins = "*")
public class ProdutoController {
private ProdutoRepository repository;
	
	ProdutoController(ProdutoRepository produtoRepository){
		this.repository = produtoRepository;
	}
	
	@GetMapping
	public List findAll() {
		return repository.findAll();
	}
	
	
	
	@GetMapping(path = {"/{id}"})
	public ResponseEntity findById(@PathVariable int id) {
		return repository.findById(id)
				.map(record -> ResponseEntity.ok(record))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public Produto create(@RequestBody Produto produto) {
		return repository.save(produto);
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity update(@PathVariable("id") int id,
			@RequestBody Produto produto) {
		return repository.findById(id)
				.map(record -> {
					record.setNome(produto.getNome());
					record.setDataEntrada(produto.getDataEntrada());
					record.setDataSaida(produto.getDataSaida());
					record.setQuantidade(produto.getQuantidade());
					record.setValor(produto.getValor());
					Produto updated = repository.save(record);
					return ResponseEntity.ok().body(updated);
				}).orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping(path = {"/{id}"})
	public ResponseEntity<?> delete(@PathVariable int id){
		return repository.findById(id)
				.map(record -> {
					repository.deleteById(id);
					return ResponseEntity.ok().build();
				}).orElse(ResponseEntity.notFound().build());
	}
}
