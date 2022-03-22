package students;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TeacherNotebook {

    private List<Student> students = new ArrayList<>();

    public List<Student> getStudents() {
        return students;
    }

    public void readFromFile(Path path) {
        try {
            List<String> lines = Files.readAllLines(path);
            fillStudents(lines);
        } catch (IOException ioe) {
            throw new IllegalStateException("Can not read File!");
        }
    }

    private void fillStudents(List<String> lines) {
        for (String line: lines) {
            String[] parts = line.split(";");
            String name = parts[0];
            String className = parts[1];
            Student student= new Student(name, className);
            addGrades(parts, student);
            students.add(student);
        }
    }

    private void addGrades(String[] parts, Student student) {
        for (int i = 2; i < parts.length; i++) {
            student.addGrade(Integer.parseInt(parts[i]));
        }
    }

    public List<String> findFailingStudents() {
        return students.stream().filter(student -> student.getGrades().stream()
                .mapToInt(i -> i).average().orElse(0) < 2).map(Student::getName).toList();
    }
}
