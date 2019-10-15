package com.javagda25.spring_students2.spring_students2.controller;

import com.javagda25.spring_students2.spring_students2.model.Grade;
import com.javagda25.spring_students2.spring_students2.model.GradeSubject;
import com.javagda25.spring_students2.spring_students2.service.GradeService;
import com.javagda25.spring_students2.spring_students2.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping(path = "/grade/")
public class GradeController {
    private final GradeService gradeService;
    private final StudentService studentService;


    @GetMapping("/add")
    public String gradeAdd(Model model,
                           Grade grade,
                           @RequestParam(name = "id") Long studentId) {
        grade.setGradeVal(2.0);
        grade.setId(null);
        model.addAttribute("grade", grade);
        model.addAttribute("subjects", GradeSubject.values());
        model.addAttribute("student", studentId);

        return "grade-add";
    }


    @PostMapping("/add")
    public String gradeAdd(Grade grade, Long studentId) {
//        gradeVal.getStudent().setId(studentId);
        gradeService.saveGrade(grade, studentId);

        return "redirect:/gradeVal/list";
    }

//    bez relacji
//    @PostMapping("/add")
//    public String gradeAdd(Grade gradeVal) {
//        gradeService.saveGrade(gradeVal);
//
//        return "redirect:/student/list";
//    }



    @GetMapping("/list")
    public String gradeList(Model model){
        List<Grade> gradeList = gradeService.listAllGrade();
        model.addAttribute("grades", gradeList);
        return "grade-list";
    }

}
