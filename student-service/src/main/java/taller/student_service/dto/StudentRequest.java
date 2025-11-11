package taller.student_service.dto;

import lombok.Data;

@Data
public class StudentRequest {
    private String nombre;
    private String apellido;
    private String correo;
}