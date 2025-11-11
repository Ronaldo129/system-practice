package taller.student_service.controller;

import org.springframework.web.bind.annotation.*;
import taller.student_service.dto.StudentRequest;
import taller.student_service.dto.StudentResponse;
import taller.student_service.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping
    public List<StudentResponse> getAll() {
        return service.findAll();
    }

    @PostMapping
    public StudentResponse create(@RequestBody StudentRequest request) {
        return service.save(request);
    }

    @PutMapping("/{id}")
    public StudentResponse update(@PathVariable Long id, @RequestBody StudentRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
