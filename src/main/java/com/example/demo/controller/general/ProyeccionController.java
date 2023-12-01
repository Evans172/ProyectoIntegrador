package com.example.demo.controller.general;

import static com.example.demo.commons.GlobalConstans.*;

import java.util.List;
import java.util.Optional;

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

import com.example.demo.entity.Proyeccion;
import com.example.demo.serviceImpl.ProyeccionServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping(API_PROYECCION)
@CrossOrigin(origins = "http://localhost:4200")

public class ProyeccionController {
	
	@Autowired
	private ProyeccionServiceImpl proyeccionServiceImpl;
	
	@GetMapping("/readAll")
	public ResponseEntity<List<Proyeccion>> listar() {
		try {
            List<Proyeccion> var = proyeccionServiceImpl.readAll();
                if (var.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
                return new ResponseEntity<>(var, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/save")
    public ResponseEntity<Proyeccion> crear(@Valid @RequestBody Proyeccion proyeccion){
        try {
            Proyeccion _aut = proyeccionServiceImpl.create(proyeccion);
            return new ResponseEntity<Proyeccion>(_aut, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Proyeccion> getProyeccionById(@PathVariable("id") Long id){
		Optional<Proyeccion> carData = proyeccionServiceImpl.read(id);
        if (carData.isPresent()) {
            return new ResponseEntity<Proyeccion>(carData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Proyeccion> delete(@PathVariable("id") Long id){
		try {
			proyeccionServiceImpl.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	@PutMapping("/put/{id}")
	public ResponseEntity<?> updateCarrera(@PathVariable("id") Long id, @Valid @RequestBody Proyeccion proyeccion){
		Optional<Proyeccion> carData = proyeccionServiceImpl.read(id);
        if (carData.isPresent()) {
            Proyeccion dbProyeccion = carData.get();
            dbProyeccion.setNombreProyeccion(proyeccion.getNombreProyeccion());
			dbProyeccion.setDescripcion(proyeccion.getDescripcion());
			dbProyeccion.setEjeEstrategico(proyeccion.getEjeEstrategico());
			dbProyeccion.setObjetivo(proyeccion.getObjetivo());
			dbProyeccion.setZonaIntervencion(proyeccion.getZonaIntervencion());
			dbProyeccion.setArchivoAdjunto(proyeccion.getArchivoAdjunto());
			dbProyeccion.setFechaInicio(proyeccion.getFechaInicio());
			dbProyeccion.setFechaTermino(proyeccion.getFechaTermino());
			//!ManyToOne's
			dbProyeccion.setEstadoProyeccion(proyeccion.getEstadoProyeccion());
			dbProyeccion.setTipoProyeccion(proyeccion.getTipoProyeccion());
			//!OneToMany's
			dbProyeccion.setActividades(proyeccion.getActividades());
            return new ResponseEntity<Proyeccion>(proyeccionServiceImpl.update(dbProyeccion), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

