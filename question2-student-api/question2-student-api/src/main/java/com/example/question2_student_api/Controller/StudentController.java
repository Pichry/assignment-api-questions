package com.example.question2_student_api.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.question2_student_api.model.Student;

public class StudentController {
    private List<Student> students = new ArrayList<>(Arrays.asList(
            new Student(1L, "Alice", "Smith", "alice@mail.com", "Computer Science", 3.8),
            new Student(2L, "Bob", "Brown", "bob@mail.com", "Business", 3.2),
            new Student(3L, "Carol", "Jones", "carol@mail.com", "Computer Science", 3.6),
            new Student(4L, "David", "Lee", "david@mail.com", "Engineering", 3.1),
            new Student(5L, "Eva", "White", "eva@mail.com", "IT", 3.9)
    ));

    @GetMapping
    public List<Student> getAllStudents() {
        return students;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getById(@PathVariable Long id) {
        return students.stream()
                .filter(s -> s.getStudentId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/major/{major}")
    public List<Student> getByMajor(@PathVariable String major) {
        List<Student> result = new ArrayList<>();
        for (Student s : students) {
            if (s.getMajor().equalsIgnoreCase(major)) {
                result.add(s);
            }
        }
        return result;
    }

    @GetMapping("/filter")
    public List<Student> filterByGpa(@RequestParam Double gpa) {
        List<Student> result = new ArrayList<>();
        for (Student s : students) {
            if (s.getGpa() >= gpa) {
                result.add(s);
            }
        }
        return result;
    }

    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        students.add(student);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id,
                                                 @RequestBody Student updated) {
        for (Student s : students) {
            if (s.getStudentId().equals(id)) {
                s.setFirstName(updated.getFirstName());
                s.setLastName(updated.getLastName());
                s.setEmail(updated.getEmail());
                s.setMajor(updated.getMajor());
                s.setGpa(updated.getGpa());
                return ResponseEntity.ok(s);
            }
        }
        return ResponseEntity.notFound().build();
    }

}
