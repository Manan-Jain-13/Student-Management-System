package net.javaguides.sms.mapper;

import net.javaguides.sms.dto.StudentDTO;
import net.javaguides.sms.entity.Student;

public class StudentMapper {

    public static StudentDTO mapToStudentDTO(Student student){
        StudentDTO studentDTO=new StudentDTO(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail()
        );
        return studentDTO;
    }

    public static Student mapToStudent(StudentDTO studentDTO){
        Student student=new Student(
          studentDTO.getId(),
          studentDTO.getFirstName(),
          studentDTO.getLastName(),
          studentDTO.getEmail()
        );
        return student;
    }
}
