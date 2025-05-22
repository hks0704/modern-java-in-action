package ModernJavaInAction.part1.ch2;

public class StudentFemaleAndShortPredicate implements StudentPredicate{
    @Override
    public boolean test(Student student) {
        return Student.Gender.FEMALE.equals(student.getGender())&&student.getHeight()<180;
    }
}
