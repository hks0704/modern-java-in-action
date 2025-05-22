package ModernJavaInAction.part1.ch2;

public class Student {
    private Integer age;
    private Integer height;
    private String name;
    private Gender gender;

    public int getAge() {
        return age;
    }

    public Integer getHeight() {
        return height;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public Student() {
    }

    public Student(int age, int height, String name, Gender gender) {
        this.age = age;
        this.height = height;
        this.name = name;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", height=" + height +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                '}';
    }

    public enum Gender {MALE, FEMALE}
}
