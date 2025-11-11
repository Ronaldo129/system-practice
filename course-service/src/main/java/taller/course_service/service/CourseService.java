package taller.course_service.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import taller.course_service.dto.CourseRequest;
import taller.course_service.dto.CourseResponse;
import taller.course_service.model.Course;
import taller.course_service.repository.CourseRepository;


import java.util.List;
import java.util.stream.Collectors;
@Service
public class CourseService {

    private final CourseRepository repository;
    private static final Logger log = LoggerFactory.getLogger(CourseService.class);

    public CourseService(CourseRepository repository) {
        this.repository = repository;
    }

    public List<CourseResponse> findAll() {
        log.debug("Buscando todos los cursos en la base de datos");
       return repository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public CourseResponse save(CourseRequest request) {
        Course course = new Course();
        course.setNombre(request.getNombre());
        course.setDescripcion(request.getDescripcion());
        course.setCreditos(request.getCreditos());
        Course saved = repository.save(course);
        log.debug("Curso guardado con ID {}", saved.getId());
       return toResponse(saved);
    }
    public CourseResponse update(Long id,CourseRequest request) {
        Course existng = repository.findById(id)
        .orElseThrow(() -> new RuntimeException("Course not found"+id));
        existng.setNombre(request.getNombre());
        existng.setDescripcion(request.getDescripcion());
        existng.setCreditos(request.getCreditos());
        Course updated = repository.save(existng);
        log.debug("Curso actualizado con ID {}", updated.getId());
        return toResponse(updated);

            }       
        
    public void delete(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado con ID " + id));
         
        repository.deleteById(id);
           log.debug("Curso eliminado con ID {}", id);
    }
     private CourseResponse toResponse(Course course) {
        CourseResponse response = new CourseResponse();
        response.setId(course.getId());
        response.setNombre(course.getNombre());
        response.setDescripcion(course.getDescripcion());
        response.setCreditos(course.getCreditos());
        return response;
    }
}
