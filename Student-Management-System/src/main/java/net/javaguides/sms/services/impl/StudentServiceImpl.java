package net.javaguides.sms.services.impl;

import net.javaguides.sms.dto.StudentDTO;
import net.javaguides.sms.entity.Student;
import net.javaguides.sms.mapper.StudentMapper;
import net.javaguides.sms.repository.StudentRepository;
import net.javaguides.sms.services.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        List<Student> students=studentRepository.findAll();
        List<StudentDTO> studentDTOS=students.stream()
                .map((student)-> StudentMapper.mapToStudentDTO(student))
                .collect(Collectors.toList());
        return studentDTOS;
    }

    @Override
    public void createStudent(StudentDTO studentDTO) {
      Student student=StudentMapper.mapToStudent(studentDTO);
      studentRepository.save(student);
    }

    @Override
    public StudentDTO getStudentById(Long studentId) {
        Student student=studentRepository.findById(studentId).get();
        StudentDTO studentDTO=StudentMapper.mapToStudentDTO(student);
        return studentDTO;
    }

    @Override
    public void updateStudent(StudentDTO studentDTO) {
        studentRepository.save(StudentMapper.mapToStudent(studentDTO));
    }

    @Override
    public void deleteStudent(Long studentId) {
        studentRepository.deleteById(studentId);
    }

}
