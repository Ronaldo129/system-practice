package taller.course_service.controller;
import taller.course_service.dto.CourseRequest;
import taller.course_service.dto.CourseResponse;
import taller.course_service.model.Course;
import taller.course_service.service.CourseService;
import org.springframework.web.bind.annotation.*;   

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    @GetMapping
    public List<CourseResponse> getAll() {
        return service.findAll();
    }

    @PostMapping
    public CourseResponse create(@RequestBody CourseRequest request) {
        return service.save(request);
    }
    @PutMapping("/{id}")
    public CourseResponse update(@PathVariable Long id, @RequestBody CourseRequest request) {
        return service.update(id, request);
    }   

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}