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

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
        gradeService.saveGrade(grade, studentId);

        return "redirect:/student/grades?id=" + studentId;
    }


    @GetMapping("/list")
    public String gradeList(Model model) {
        List<Grade> gradeList = gradeService.listAllGrade();
        model.addAttribute("grades", gradeList);
        return "grade-list";
    }

    @GetMapping("/remove")
    public String gradeRemove(HttpServletRequest request,
                              @RequestParam(name = "id") Long gradeId) {
        String referer = request.getHeader("referer");

        gradeService.remove(gradeId);

        if (referer != null) {
            return "redirect:" + referer;
        }

        return "redirect:/student/list";
    }

    @GetMapping("/edit")
    public String gradeEdit(Model model,
                            @RequestParam(name = "id") Long gradeId) {

        Long studentId = gradeService.findByGradeId(gradeId).get().getStudent().getId();

        Optional<Grade> optionalGrade = gradeService.findByGradeId(gradeId);
        if (optionalGrade.isPresent()) {
            model.addAttribute("grade", optionalGrade.get());
            model.addAttribute("subjects", GradeSubject.values());
            model.addAttribute("student", studentId);

            return "grade-add";
        }

        return "redirect:/student/list";
    }

}
