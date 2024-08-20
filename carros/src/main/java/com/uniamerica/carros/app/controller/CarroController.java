package com.uniamerica.carros.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uniamerica.carros.app.entity.Carro;
import com.uniamerica.carros.app.entity.Marca;
import com.uniamerica.carros.app.service.CarroService;


@RestController
//@CrossOrigin(origins = "*")
@RequestMapping("/api/carro")
public class CarroController {

	@Autowired
	private CarroService carroService;

	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody Carro carro) {
		try {
			String mensagem = this.carroService.save(carro);
			return new ResponseEntity<String>(mensagem, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Deu esse erro ao salvar: " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@GetMapping("/listAll")
	public ResponseEntity<List<Carro>> listAll() {
		try {
			List<Carro> lista = this.carroService.listAll();
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

		}
	}

	@GetMapping("/findById/{idCarro}")
	public ResponseEntity<Carro> findById(@PathVariable long idCarro) {
		try {
			Carro carro = this.carroService.findById(idCarro);
			return new ResponseEntity<>(carro, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

		}	
	}

	@DeleteMapping("/delete/{idCarro}")
	public ResponseEntity<String> delete(@PathVariable long idCarro) {
		try {
			String mensagem = this.carroService.delete(idCarro);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Deu erro ao deletar", HttpStatus.BAD_REQUEST);

		}
	}
	
	@PutMapping("/update/{idCarro}")
	public ResponseEntity<String> update(@RequestBody Carro carro, @PathVariable long idCarro) {
		try {
			String mensagem = this.carroService.update(carro, idCarro);
			return new ResponseEntity<String>(mensagem, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Deu esse erro ao salvar: " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
}

	

