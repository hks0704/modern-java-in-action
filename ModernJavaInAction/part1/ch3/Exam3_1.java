package ModernJavaInAction.part1.ch3;

import ModernJavaInAction.part1.ch2.Student;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Exam3_1 {
    public static void main(String[] args) {
        // 람다 표현식

        // (1) 람다 표현식을 사용하기 이전 코드
//        Comparator<Student> byHeight = new Comparator<Student>() {
//            @Override
//            public int compare(Student s1, Student s2) {
//                return s1.getHeight().compareTo(s2.getHeight()); // Wrapper 클래스에 대해서만 compareTo 사용가능
//            }
//        };

        // (2) 람다 표현식을 이용한 코드
        Comparator<Student> byWeight = (Student s1, Student s2) -> s1.getHeight().compareTo(s2.getHeight());

        List<Student>students = Arrays.asList(
                new Student(25,160,"Choi", Student.Gender.FEMALE),
                new Student(22,175,"Park", Student.Gender.MALE),
                new Student(21,165,"Cho", Student.Gender.FEMALE),
                new Student(28,178,"Lee", Student.Gender.MALE)
        );

        students.sort(byWeight);
        for(Student student : students)
            System.out.println(student);
    }
}
