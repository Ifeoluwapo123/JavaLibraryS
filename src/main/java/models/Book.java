package models;

public class Book implements Comparable<Book>, interfaces.Book {

    private int id;
    private int numOfCopies;
    private String author;
    private String country;
    private String category;
    private String imageLink;
    private String language;
    private  String link;
    private  int pages;
    private String title;
    private int year;

    @Override
    public int compareTo(Book book) {
        if(this.id > book.id) return 1;
        else return -1;
    }

    public Book(String title) {
        this.id = 0;
        this.author ="";
        this.numOfCopies = 0;
        this.country = "";
        this.category = "";
        this.imageLink = "";
        this.language = "";
        this.link = "";
        this.pages =0;
        this.title = title;
        this.year = 0;
    }

    /**
     *  This Constructor uses json mapper, to map data from the books.json file
     * */
    public Book() {
        this.id = 0;
        this.author = "";
        this.numOfCopies = 0;
        this.country = "";
        this.category = "";
        this.imageLink = "";
        this.language = "";
        this.link = "";
        this.pages = 0;
        this.title = "";
        this.year = 0;
    }

    public Book(int id, int numOfCopies, String author, String country,String category, String imageLink,
                String language, String link, int pages, String title, int year) {
        this.id = id;
        this.author = author;
        this.numOfCopies = numOfCopies;
        this.country = country;
        this.imageLink = imageLink;
        this.language = language;
        this.category = category;
        this.link = link;
        this.pages = pages;
        this.title = title;
        this.year = year;
    }

    public String getAuthor() {
        return author;
    }

    public String getCountry() {
        return country;
    }

    public String getImageLink() {
        return imageLink;
    }

    public String getLanguage() {
        return language;
    }

    public int getPages() {
        return pages;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public int getNumOfCopies() {
        return numOfCopies;
    }

    public String getCategory() {
        return category;
    }

    public int getYear() {
        return year;
    }

    public String getLink() {
        return link;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", numOfCopies=" + numOfCopies +
                ", author='" + author + '\'' +
                ", country='" + country + '\'' +
                ", category='" + category + '\'' +
                ", imageLink='" + imageLink + '\'' +
                ", language='" + language + '\'' +
                ", link='" + link + '\'' +
                ", pages=" + pages +
                ", title='" + title + '\'' +
                ", year=" + year +
                '}';
    }

}
