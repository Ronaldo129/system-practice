package taller.course_service.dto;

 import lombok.Data;
@Data       
public class CourseResponse {
     private Long id;
    private String nombre;
    private String descripcion;
    private Integer creditos;
}
