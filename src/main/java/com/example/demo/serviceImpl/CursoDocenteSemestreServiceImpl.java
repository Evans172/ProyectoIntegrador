package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.CursoDocenteSemestre;
import com.example.demo.repository.CursoDocenteSemestreRepository;
import com.example.demo.service.Operaciones;
@Service
public class CursoDocenteSemestreServiceImpl implements Operaciones<CursoDocenteSemestre>{
	
	@Autowired
	private CursoDocenteSemestreRepository cursoDocenteSemestreRepository;

	@Override
	public CursoDocenteSemestre create(CursoDocenteSemestre t) {
		
		return cursoDocenteSemestreRepository.save(t);
	}

	@Override
	public CursoDocenteSemestre update(CursoDocenteSemestre t) {
		
		return cursoDocenteSemestreRepository.save(t);
	}

	@Override
	public void delete(Long id) {
		
		cursoDocenteSemestreRepository.deleteById(id);
		
	}

	@Override
	public Optional<CursoDocenteSemestre> read(Long id) {
		
		return cursoDocenteSemestreRepository.findById(id);
	}

	@Override
	public List<CursoDocenteSemestre> readAll() {
		
		return cursoDocenteSemestreRepository.findAll();
	}

}
