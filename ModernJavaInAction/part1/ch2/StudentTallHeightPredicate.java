package ModernJavaInAction.part1.ch2;

public class StudentTallHeightPredicate implements StudentPredicate{
    @Override
    public boolean test(Student student) {
        return student.getHeight()>180;
    }
}
