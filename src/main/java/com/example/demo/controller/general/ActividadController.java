package com.example.demo.controller.general;

import static com.example.demo.commons.GlobalConstans.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import com.example.demo.dto.ActividadDto;
import com.example.demo.entity.Proyeccion;
import com.example.demo.repository.ActividadRepository;
import com.example.demo.repository.ProyeccionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Actividad;
import com.example.demo.serviceImpl.ActividadServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping(API_ACTIVIDAD)
@CrossOrigin(origins = "http://localhost:4200")

public class ActividadController {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private ProyeccionRepository proyeccionRepository;

	@Autowired
	private ActividadRepository actividadRepository;
	
	@Autowired
	private ActividadServiceImpl actividadServiceImpl;
	
	@GetMapping("/readAll")
	public ResponseEntity<List<Actividad>> listar() {
		try {
			List<Actividad> car = actividadServiceImpl.readAll();
			if (car.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
				return new ResponseEntity<>(car, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
	
	@PostMapping("/save")
	public ResponseEntity<Actividad> crear(@Valid @RequestBody ActividadDto actividadDto){

		Proyeccion proyeccion=proyeccionRepository.findById(actividadDto.getProyeccionId()).orElseThrow(()->new RuntimeException("No se encontro la proyeccion"));

		Actividad actividad1=mapper.map(actividadDto,Actividad.class);
		actividad1.setProyeccionId(proyeccion);

		try {
			Actividad _aut = actividadServiceImpl.create(actividad1);
			return new ResponseEntity<Actividad>(_aut, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Actividad> getActividadById(@PathVariable("id") Long id){
		Optional<Actividad> carData = actividadServiceImpl.read(id);
		if (carData.isPresent()) {
			return new ResponseEntity<Actividad>(carData.get(), HttpStatus.OK);
		} else {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Actividad> delete(@PathVariable("id") Long id){
		try {
			actividadServiceImpl.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping("/put/{id}")
	public ResponseEntity<Actividad> updateCarrera(@PathVariable("id") Long id, @Valid @RequestBody ActividadDto actividadDto){



		Optional<Actividad> carData = actividadServiceImpl.read(id);
		if (carData.isPresent()) {
			Actividad dbActividad = carData.get();
			dbActividad.setNombreActividad(actividadDto.getNombreActividad());
			dbActividad.setFechaInicio( actividadDto.getFechaInicio());
			dbActividad.setFechaTermino( actividadDto.getFechaTermino());

			return new ResponseEntity<>(actividadRepository.save(dbActividad), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
