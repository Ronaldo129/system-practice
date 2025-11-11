package     taller.course_service.service;
import taller.course_service.dto.CourseRequest;
import taller.course_service.dto.CourseResponse;
import taller.course_service.model.Course;
import taller.course_service.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class CourseService {

    private final CourseRepository repository;

    public CourseService(CourseRepository repository) {
        this.repository = repository;
    }

    public List<CourseResponse> findAll() {
       return repository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public CourseResponse save(CourseRequest request) {
        Course course = new Course();
        course.setNombre(request.getNombre());
        course.setDescripcion(request.getDescripcion());
        course.setCreditos(request.getCreditos());
       return toResponse(repository.save(course));
    }
    public CourseResponse update(Long id,CourseRequest request) {
        Course existng = repository.findById(id).orElseThrow(() -> new RuntimeException("Course not found"));
        existng.setNombre(request.getNombre());
        existng.setDescripcion(request.getDescripcion());
        existng.setCreditos(request.getCreditos());
        return toResponse(repository.save(existng));
            }       
        
    public void delete(Long id) {
        repository.deleteById(id);
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
