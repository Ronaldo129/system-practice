package taller.student_service.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import taller.student_service.config.ApiRoutes;
import taller.student_service.dto.StudentRequest;
import taller.student_service.dto.StudentResponse;
import taller.student_service.service.StudentService;

import java.util.List;

@RestController
@RequestMapping(ApiRoutes.STUDENTS)
public class StudentController {

    private final StudentService service;
    private static final Logger log = 
    LoggerFactory.getLogger(StudentController.class);

    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping
    public List<StudentResponse> getAll() {
        log.info("Obteniendo todos los estudiantes");
        return service.findAll();
    }

    @PostMapping
    public StudentResponse create
    (@RequestBody StudentRequest request) {
        log.info("Creando estudiante: {} {}", request.getNombre(), request.getApellido());
        return service.save(request);
    }

    @PutMapping("/{id}")
    public StudentResponse update(@PathVariable Long id, @RequestBody StudentRequest request) {
        log.info("Actualizando estudiante con ID {}", id);
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        log.info("Eliminando estudiante con ID {}", id);
        service.delete(id);
    }
}
