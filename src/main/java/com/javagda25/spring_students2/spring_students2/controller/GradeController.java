package com.javagda25.spring_students2.spring_students2.controller;

import com.javagda25.spring_students2.spring_students2.model.Grade;
import com.javagda25.spring_students2.spring_students2.model.GradeSubject;
import com.javagda25.spring_students2.spring_students2.model.Student;
import com.javagda25.spring_students2.spring_students2.service.GradeService;
import com.javagda25.spring_students2.spring_students2.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping(path = "/grade/")
public class GradeController {
    private final GradeService gradeService;
    private final StudentService studentService;

//    @GetMapping("/add")
//    public String gradeAdd(Model model,
//                           Grade grade){
//        grade.setGrade(2.0);
//        model.addAttribute("studentId", studentService.getAll());
//        model.addAttribute("subjects", GradeSubject.values());
//        model.addAttribute("grade", grade);
//        return "grade-add";
//    }

    //    bez relacji
//    @GetMapping("/add")
//    public String gradeAdd(Model model, Grade grade){
//        grade.setGrade(2.0);
//        model.addAttribute("subjects", GradeSubject.values());
//        model.addAttribute("grade", grade);
//        return "grade-add";
//    }

//    @GetMapping("/add")
//    public String gradeAdd(Model model,
//                           Grade grade,
//                           @RequestParam(name = "id") Long studentId) {
//        grade.setGrade(2.0);
//        Optional<Student> student = studentService.findByStudentId(studentId);
//        model.addAttribute("student", student.get());
//        model.addAttribute("subjects", GradeSubject.values());
//        model.addAttribute("grade", grade);
//        return "grade-add";
//    }

    @GetMapping("/add")
    public String gradeAdd(Model model,
                           Grade grade,
                           @RequestParam(name = "id") Long studentId) {
        grade.setGrade(2.0);
        model.addAttribute("student", studentId);
        model.addAttribute("subjects", GradeSubject.values());
        model.addAttribute("grade", grade);

        return "grade-add";
    }
//    @GetMapping("/add")
//    public String gradeAdd(Model model,
//                           Grade grade) {
//        grade.setGrade(2.0);
//        model.addAttribute("student", studentService.getAll());
//        model.addAttribute("subjects", GradeSubject.values());
//        model.addAttribute("grade", grade);
//        return "grade-add";
//    }


    @PostMapping("/add")
    public String gradeAdd(Grade grade, Long studentId) {
//        grade.getStudent().setId(studentId);
        gradeService.saveGrade(grade, studentId);

        return "redirect:/student/list";
    }

//    bez relacji
//    @PostMapping("/add")
//    public String gradeAdd(Grade grade) {
//        gradeService.saveGrade(grade);
//
//        return "redirect:/student/list";
//    }


//
//    @GetMapping("/list")
//    public String gradeList(Model model){
//        List<Grade> gradeList = gradeService.listAllGrade();
//        model.addAttribute("grades", gradeList);
//        return "grade-list";
//    }

}
