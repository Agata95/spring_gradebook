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

    @GetMapping("/add")
    public String gradeAdd(Model model,
                           Grade grade){
        grade.setGrade(2.0);
        model.addAttribute("studentId", studentService.getAll());
        model.addAttribute("subjects", GradeSubject.values());
        model.addAttribute("grade", grade);
        return "grade-add";
    }

    @PostMapping("/add")
    public String gradeAdd(Grade grade, Long studentId){
        gradeService.saveGrade(grade, studentId);

        return "redirect:/student/list";
    }

//    @GetMapping("/grades")
//    public String studentGrades(Model model,
//                                @RequestParam(name = "id") Long studentId) {
//        Optional<Student> studentOptional = studentService.findByStudentId(studentId);
//        if(studentOptional.isPresent()){
//            model.addAttribute("grades", studentOptional.get().getGrades());
//            return "grade-list";
//        }
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
