package taller.student_service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudentServiceApplication {
    private static final Logger log = LoggerFactory.getLogger(StudentServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(StudentServiceApplication.class, args);
        log.info("--------- Student Service iniciado en puerto 8081");
    }
}
