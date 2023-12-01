package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Proyeccion;
import com.example.demo.repository.ProyeccionRepository;
import com.example.demo.service.Operaciones;
@Service
public class ProyeccionServiceImpl implements Operaciones<Proyeccion>{
	
	@Autowired
	private ProyeccionRepository proyeccionRepository;

	@Override
	public Proyeccion create(Proyeccion t) {
		return proyeccionRepository.save(t);
	}

	@Override
	public Proyeccion update(Proyeccion t) {
		return proyeccionRepository.save(t);
	}

	@Override
	public void delete(Long id) {
		proyeccionRepository.deleteById(id);
		
	}

	@Override
	public Optional<Proyeccion> read(Long id) {
		return proyeccionRepository.findById(id);
	}

	@Override
	public List<Proyeccion> readAll() {
		return proyeccionRepository.findAll();
	}

}
