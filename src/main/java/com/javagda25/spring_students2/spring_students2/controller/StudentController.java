package com.javagda25.spring_students2.spring_students2.controller;

import com.javagda25.spring_students2.spring_students2.model.Student;
import com.javagda25.spring_students2.spring_students2.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

        return "redirect:/student/list";
    }

    @GetMapping("/remove")
    public String studentRemove(HttpServletRequest request,
                                @RequestParam(name = "id") Long id) {
        String referer = request.getHeader("referer");
        studentService.removeStudentById(id);
        if (referer != null) {
            return "redirect:" + referer;
        }
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
        return "redirect:/student/list";
    }

    @GetMapping("/details")
    public String studentDetails(Model model,
                                 HttpServletRequest request,
                                 @RequestParam(name = "id") Long id) {
        Optional<Student> optionalStudent = studentService.findByStudentId(id);
        if (optionalStudent.isPresent()) {
            model.addAttribute("student", optionalStudent.get());
            model.addAttribute("referer", request.getHeader("referer"));
            return "student-details";
        }
        return "redirect:/student/list";
    }
}