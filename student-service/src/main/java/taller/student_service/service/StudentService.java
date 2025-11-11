package taller.student_service.service;

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

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

      public List<StudentResponse> findAll() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }


    public StudentResponse save(StudentRequest request) {
        Student student = new Student();
        student.setNombre(request.getNombre());
        student.setApellido(request.getApellido());
        student.setCorreo(request.getCorreo());
        return toResponse(repository.save(student));
    }

    public StudentResponse update(Long id, StudentRequest request) {
        Student existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        existing.setNombre(request.getNombre());
        existing.setApellido(request.getApellido());
        existing.setCorreo(request.getCorreo());

        return toResponse(repository.save(existing));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private StudentResponse toResponse(Student student) {
        StudentResponse response = new StudentResponse();
        response.setId(student.getId());
        response.setNombre(student.getNombre());
        response.setApellido(student.getApellido());
        response.setCorreo(student.getCorreo());
        return response;
    }


    public StudentResponse findById(Long id) {
        Student student = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        return toResponse(student);
    }
}