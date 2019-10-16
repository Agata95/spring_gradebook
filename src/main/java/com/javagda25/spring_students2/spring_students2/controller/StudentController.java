package com.javagda25.spring_students2.spring_students2.controller;

import com.javagda25.spring_students2.spring_students2.model.Student;
import com.javagda25.spring_students2.spring_students2.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping(path = "/student/")
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/list")
    public String studentList(Model model) {
        List<Student> students = studentService.listAllStudents();
        model.addAttribute("students", students);

        return "student-list";
    }


    @GetMapping("/add")
    public String studentAdd(Model model, Student student) {
        model.addAttribute("student", student);

        return "student-add";
    }

    @PostMapping("/add")
    public String studentAdd(Student student) {
        studentService.saveStudent(student);

        return "redirect:/student/details?id=" + student.getId();
    }

    @GetMapping("/remove")
    public String studentRemove(@RequestParam(name = "id") Long id) {

        studentService.removeStudentById(id);

        return "redirect:/student/list";
    }

    @GetMapping("/edit")
    public String studentEdit(Model model,
                              @RequestParam(name = "id") Long id) {
        Optional<Student> optionalStudent = studentService.findByStudentId(id);
        if (optionalStudent.isPresent()) {
            model.addAttribute("student", optionalStudent.get());
            return "student-add";
        }
        return "redirect:/student/details";
    }

    @GetMapping("/details")
    public String studentDetails(Model model,
                                 @RequestParam(name = "id") Long id) {
        Optional<Student> optionalStudent = studentService.findByStudentId(id);
        if (optionalStudent.isPresent()) {
            model.addAttribute("student", optionalStudent.get());
            return "student-details";
        }
        return "redirect:/student/details";
    }

    @GetMapping("/grades")
    public String grades(Model model,
                         @RequestParam(name = "id") Long id) {
        Optional<Student> studentOptional = studentService.findByStudentId(id);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();

            model.addAttribute("grades", student.getGrades());
            model.addAttribute("student", id);

            return "grade-list";
        }
        return "redirect:/student/list";
    }
}
