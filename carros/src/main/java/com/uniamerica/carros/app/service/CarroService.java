package com.uniamerica.carros.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniamerica.carros.app.entity.Carro;
import com.uniamerica.carros.app.entity.Marca;
import com.uniamerica.carros.app.repository.CarroRepository;

@Service
public class CarroService {

	@Autowired
	private CarroRepository carroRepository;

	public String save(Carro carro) {

		this.carroRepository.save(carro);
		return carro.getNome() + " salvo com sucesso";
	}
	
	public List<Carro> listAll(){
		return this.carroRepository.findAll();
	}
	
	public Carro findById(long idCarro) {
		Carro carro = this.carroRepository.findById(idCarro).get();
		return carro;
	
	}
	
	public String delete(long idCarro) {
		this.carroRepository.deleteById(idCarro);
		return "Carro deletada com sucesso!";
	}

	public String update(Carro carro, long idCarro) {
		carro.setId(idCarro);
		this.carroRepository.save(carro);
		return carro.getNome() + " salvo com sucesso";
	}
}
