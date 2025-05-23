package ModernJavaInAction.part2.ch5;

public class Book {
    private Author author;
    private String title;
    private int year;
    private int pages;

    public Book() {
    }

    public Book(Author author, String title, int year, int pages) {
        this.author = author;
        this.title = title;
        this.year = year;
        this.pages = pages;
    }

    public Author getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public int getPages() {
        return pages;
    }

    @Override
    public String toString() {
        return "Book{" +
                "author=" + author +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", pages=" + pages +
                '}';
    }
}
