import java.sql.SQLOutput;
import java.util.ArrayList;

public class StudentManagement {
    int counter = 0;
    Student[] students = new Student[100];

    public static boolean sameGroup(Student s1, Student s2) {
        return s1.getGroup().equals(s2.getGroup());
    }

    public void addStudent(Student newStudent) {
        students[counter] = newStudent;
        counter++;


    }

    public String studentsByGroup() {
        StringBuilder result = new StringBuilder();
        ArrayList<String> groups = new ArrayList<>();
        for (Student student : students) {
            if (student != null) {
                groups.add(student.getGroup());
            }
        }
        ArrayList<String> uniqueGroup = new ArrayList<>();
        for (String group : groups) {
            if (!uniqueGroup.contains(group)) {
                uniqueGroup.add(group);
            }
        }
        for (String group : uniqueGroup) {
            result.append(group).append("\n");
            for (Student student : students) {
                if (student != null) {
                    if (student.getGroup().equals(group)) {
                        result.append(student.getInfo()).append("\n");
                    }
                }
            }
        }
        return result.toString();


    }

    public void removeStudent(String id) {
        for (int i = 0; i < counter; i++) {
            if (students[i].getId().equals(id)) {
                for (int j = i; j < counter - 1; j++) {
                    students[j] = students[j + 1];
                }
                students[counter - 1] = null; // Set the last element to null
                counter--;
                break;
            }
        }
    }



}