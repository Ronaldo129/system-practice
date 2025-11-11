package     taller.course_service.service;
import taller.course_service.model.Course;
import taller.course_service.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository repository;

    public CourseService(CourseRepository repository) {
        this.repository = repository;
    }

    public List<Course> findAll() {
        return repository.findAll();
    }

    public Course save(Course course) {
        return repository.save(course);
    }
    public Course update(Course course) {
        return repository.save(course);
    }       
        
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
