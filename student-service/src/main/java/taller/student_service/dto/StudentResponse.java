package taller.student_service.dto;

import lombok.Data;

@Data
public class StudentResponse {
    private Long id;
    private String nombre;
    private String apellido;
    private String correo;
}
