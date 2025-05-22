package ModernJavaInAction.part1.ch2;

public class StudentMaleGenderPredicate implements StudentPredicate{
    @Override
    public boolean test(Student student) {
        return Student.Gender.MALE.equals(student.getGender());
    }
}
