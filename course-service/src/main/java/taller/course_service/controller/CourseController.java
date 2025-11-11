package taller.course_service.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import taller.course_service.config.ApiRoutes;
import taller.course_service.dto.CourseRequest;
import taller.course_service.dto.CourseResponse;
import taller.course_service.service.CourseService;
import org.springframework.web.bind.annotation.*;   

import java.util.List;

@RestController
@RequestMapping(ApiRoutes.COURSES)
public class CourseController {

    private final CourseService service;
      
    private static final Logger log = 
        LoggerFactory.getLogger(CourseController.class);

    public CourseController(CourseService service) {
        this.service = service;
      
    }

    @GetMapping
    public List<CourseResponse> getAll() {
        log.info("Obteniendo todos los cursos");
        return service.findAll();
    }

    @PostMapping
    public CourseResponse create
    (@RequestBody CourseRequest request) {
        log.info("Creando curso: {}", request.getNombre(), request.getDescripcion(),request.getDescripcion());
        return service.save(request);
    }
    @PutMapping("/{id}")
    public CourseResponse update(@PathVariable Long id, @RequestBody CourseRequest request) {
        log.info("Actualizando curso con ID {}", id);
        return service.update(id, request);
    }   

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        log.info("Eliminando curso con ID {}", id);
        service.delete(id);
    }
}