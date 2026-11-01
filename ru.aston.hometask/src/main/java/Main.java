import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        try (InputStream is = Main.class.getResourceAsStream("/students.json")) {
            List<Student> students = mapper.readValue(is, new TypeReference<List<Student>>() {
            });

           findYear(students).ifPresentOrElse(
                   System.out::println,
                   ()-> System.out.println("Book not found")
           );
        }
    }

    static Optional<Integer> findYear(List<Student> students){
        return students.stream()
                .peek(System.out::println)
                .flatMap(s -> s.getBooks().stream())
                .filter(b -> b.getYear() > 2000)
                .sorted().distinct()
                .limit(3)
                .map(Book::getYear)
                .findAny();
    }
}

