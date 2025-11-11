package taller.student_service.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import taller.student_service.dto.StudentRequest;
import taller.student_service.dto.StudentResponse;
import taller.student_service.model.Student;
import taller.student_service.repository.StudentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository repository;
    private static final Logger log = LoggerFactory.getLogger(StudentService.class);

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public List<StudentResponse> findAll() {
        log.debug("Buscando todos los estudiantes en la base de datos");
        return repository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public StudentResponse save(StudentRequest request) {
        Student student = new Student();
        student.setNombre(request.getNombre());
        student.setApellido(request.getApellido());
        student.setCorreo(request.getCorreo());
        Student saved = repository.save(student);
        log.debug("Estudiante guardado con ID {}", saved.getId());
        return toResponse(saved);
    }

    public StudentResponse update(Long id, StudentRequest request) {
        Student existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con ID " + id));

        existing.setNombre(request.getNombre());
        existing.setApellido(request.getApellido());
        existing.setCorreo(request.getCorreo());

        Student updated = repository.save(existing);
        log.debug("Estudiante actualizado con ID {}", updated.getId());
        return toResponse(updated);
    }

    public void delete(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con ID " + id));
        repository.deleteById(id);
        log.debug("Estudiante eliminado con ID {}", id);
    }

    private StudentResponse toResponse(Student student) {
        StudentResponse response = new StudentResponse();
        response.setId(student.getId());
        response.setNombre(student.getNombre());
        response.setApellido(student.getApellido());
        response.setCorreo(student.getCorreo());
        return response;
    }
}
