package net.javaguides.sms.controller;

import jakarta.validation.Valid;
import net.javaguides.sms.dto.StudentDTO;
import net.javaguides.sms.services.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public String listStudents(Model model){
        List<StudentDTO> students=studentService.getAllStudents();
        model.addAttribute("students" ,students);
        return "students";
    }

    // handler method to create a new student request
    @GetMapping("/students/new")
    public String newStudent(Model model){
        StudentDTO studentDTO=new StudentDTO();
        model.addAttribute("student", studentDTO);
        return "create_student";
    }

    // handler method to create a new student
    @PostMapping("/students")
    public String saveStudent(@Valid @ModelAttribute("student") StudentDTO student,
                              BindingResult result,
                              Model model
                              ){
        if(result.hasErrors()){
            model.addAttribute("student",student);
            return "create_student";
        }
        studentService.createStudent(student);
        return "redirect:/students";
    }

    // handler method to handle edit student request
    @GetMapping("/students/{studentId}/edit")
    public String editStudent(@PathVariable("studentId") Long studentId,
                              Model model){
        StudentDTO student=studentService.getStudentById(studentId);
        model.addAttribute("student",student);
        return "edit_student";
    }

    // handler method to handle edit student form submit request
    @PostMapping("/students/{studentId}")
    public String updateStudent(@PathVariable("studentId") Long studentId,
                                @Valid @ModelAttribute("student") StudentDTO studentDTO,
                                BindingResult result,
                                Model model){
        if(result.hasErrors()){
            model.addAttribute("student", studentDTO);
            return "edit_student";
        }
        studentDTO.setId(studentId);
        studentService.updateStudent(studentDTO);
        return "redirect:/students";
    }

    //handler method to handle delete student request
    @GetMapping("students/{studentId}/delete")
    public String deleteStudent(@PathVariable("studentId") Long studentId){
          studentService.deleteStudent(studentId);
          return "redirect:/students";
    }

    //handler method to handle view student request
    @GetMapping("students/{studentId}/view")
    public String viewStudent(@PathVariable("studentId")Long studentId,
                              Model model){
     StudentDTO studentDTO=studentService.getStudentById(studentId);
     model.addAttribute("student",studentDTO);
     return "view_student";
    }
}
