package net.javaguides.sms.services;

import net.javaguides.sms.dto.StudentDTO;

import java.util.List;

public interface StudentService {
    void createStudent(StudentDTO student);

    List<StudentDTO> getAllStudents();

    StudentDTO getStudentById(Long studentId);

    void updateStudent(StudentDTO studentDTO);

    void deleteStudent(Long studentId);
}
