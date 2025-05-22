package ModernJavaInAction.part1.ch2;

import java.util.ArrayList;
import java.util.List;

public class Exam2_2 {
    public static void main(String[] args) {
        // 2.2.1 추상적 조건으로 필터링
        // 동작파라미터를 이용한 유연성 확보
        List<Student>sampleStudents=new ArrayList<>();
        sampleStudents.add(new Student(21,163,"Kim", Student.Gender.FEMALE));
        sampleStudents.add(new Student(25,184,"Choi", Student.Gender.MALE));
        sampleStudents.add(new Student(24,181,"Park", Student.Gender.FEMALE));
        sampleStudents.add(new Student(22,170,"Han", Student.Gender.MALE));
        StudentPredicate p1=new StudentMaleGenderPredicate();
        StudentPredicate p2=new StudentMaleGenderPredicate();
        StudentPredicate p3=new StudentFemaleAndShortPredicate();
        System.out.println("Test1 : 남학생만");
        for(Student student:filterStudents(sampleStudents,p1)){
            System.out.println(student);
        }
        System.out.println("Test2 : 키 큰 학생만(180<height)");
        for(Student student:filterStudents(sampleStudents,p2)){
            System.out.println(student);
        }
        System.out.println("Test3 : 키가 작은 여학생만");
        for(Student student:filterStudents(sampleStudents,p3)){
            System.out.println(student);
        }

    }

    /**
     *
     * @param students List<Student>
     * @param p 선택 조건을 결정하는 predicate 객체
     * @return predicate 조건에 맞는 객체만 필터링 된 결과
     */
    public static List<Student> filterStudents(List<Student> students, StudentPredicate p) {
        List<Student> result = new ArrayList<>();
        for(Student student : students) {
            if(p.test(student)) {
                result.add(student);
            }
        }
        return result;
    }
}
