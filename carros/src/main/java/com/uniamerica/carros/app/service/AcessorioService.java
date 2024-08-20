package com.uniamerica.carros.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniamerica.carros.app.entity.Acessorio;
import com.uniamerica.carros.app.entity.Carro;
import com.uniamerica.carros.app.repository.AcessorioRepository;

@Service
public class AcessorioService {

	@Autowired
	private AcessorioRepository acessorioRepository;

	public String save(Acessorio acessorio) {

		this.acessorioRepository.save(acessorio);
		return acessorio.getNome() + " salvo com sucesso";
	}
	
	public List<Acessorio> listAll(){
		return this.acessorioRepository.findAll();
	}
	
	public Acessorio findById(long idAcessorio) {
		Acessorio acessorio = this.acessorioRepository.findById(idAcessorio).get();
		return acessorio;
	
	}
	
	public String delete(long idAcessorio) {
		this.acessorioRepository.deleteById(idAcessorio);
		return "Acessorio deletada com sucesso!";
	}
	
	public String update(Acessorio acessorio, long idAcessorio) {
		acessorio.setId(idAcessorio);
		this.acessorioRepository.save(acessorio);
		return acessorio.getNome() + " salvo com sucesso";
	}
}
