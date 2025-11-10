package taller.student_service.service;
import taller.student_service.model.Student;
import taller.student_service.repository.StudentRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public List<Student> findAll() { return repository.findAll(); }

    public Student save(Student student) { return repository.save(student); }
public Student update(Student student) {
    Student existing = repository.findById(student.getId())
        .orElseThrow(() -> new RuntimeException("Student not found"));

    existing.setNombre(student.getNombre());
    existing.setApellido(student.getApellido());
    existing.setCorreo(student.getCorreo());

    return repository.save(existing);
}    public void delete(Long id) { repository.deleteById(id); }
}
