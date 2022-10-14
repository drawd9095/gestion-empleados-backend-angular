package com.gestion.empleados.controller;

import com.gestion.empleados.exception.ResouceNotFoundException;
import com.gestion.empleados.model.Empleado;
import com.gestion.empleados.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200/")
public class EmpleadoController {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    //Listar empleados
    @GetMapping("/empleados")
    public List<Empleado> listarEmpleados(){
        return empleadoRepository.findAll();
    }

    //obtener empleado por id
    @GetMapping("empleados/{id}")
    public ResponseEntity<Empleado> obtenerEmpleadobyId(@PathVariable Long id){
        Empleado empleado = empleadoRepository
                .findById(id)
                .orElseThrow(()-> new ResouceNotFoundException("No existe emppleado con el id: "+id));

        return ResponseEntity.ok(empleado);
    }

    //Guardar empleado
    @PostMapping("/empleados")
    public Empleado guardarEmpleado(@RequestBody Empleado empleado){
        return empleadoRepository.save(empleado);
    }


    //actualizar empleado
    @PutMapping("empleados/{id}")
    public ResponseEntity<Empleado> actulizarEmpleado(@PathVariable Long id, @RequestBody Empleado request){
        Empleado empleado = empleadoRepository
                .findById(id)
                .orElseThrow(()-> new ResouceNotFoundException("No existe emppleado con el id: "+id));

        empleado.setNombre(request.getNombre());
        empleado.setApellido(request.getApellido());
        empleado.setEmail(request.getEmail());

        return ResponseEntity.ok(empleadoRepository.save(empleado));
    }

    //elminar empleado
    @DeleteMapping("empleados/{id}")
    public void eliminarEmpleado(@PathVariable Long id){
        Empleado empleado = empleadoRepository
                .findById(id)
                .orElseThrow(()-> new ResouceNotFoundException("No existe emppleado con el id: "+id));

        empleadoRepository.delete(empleado);
    }

}
