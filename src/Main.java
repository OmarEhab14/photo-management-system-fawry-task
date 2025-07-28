import interfaces.Repository;
import models.Photo;
import services.PhotoManager;
import services.PhotoRepository;

import java.time.LocalDate;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Repository photoRepository = new PhotoRepository();
        PhotoManager manager = new PhotoManager(photoRepository);

        manager.uploadPhoto(new Photo(1, "pyramids.jpg", LocalDate.of(2023, 3, 10),
                "Giza", Set.of("pyramids", "history", "egypt", "tourism")));
        manager.uploadPhoto(new Photo(2, "nile.jpg", LocalDate.of(2023, 4, 22),
                "Cairo", Set.of("nile", "river", "sunset", "egypt")));
        manager.uploadPhoto(new Photo(3, "mosque.jpg", LocalDate.of(2023, 4, 22),
                "Cairo", Set.of("mosque", "islamic", "architecture", "egypt")));
        manager.uploadPhoto(new Photo(4, "museum.jpg", LocalDate.of(2023, 5, 15),
                "Cairo", Set.of("museum", "antiquities", "egypt", "pharaohs")));

        // Search by single tag
        System.out.println("Photos with tag 'egypt':");
        manager.searchByTag("egypt").forEach(System.out::println);

        // Search by date
        System.out.println("Photos taken on 2023-04-22:");
        manager.searchByDate(LocalDate.of(2023, 4, 22)).forEach(System.out::println);

        // Search by location
        System.out.println("Photos taken in Cairo:");
        manager.searchByLocation("Cairo").forEach(System.out::println);

        // Search by multiple tags
        System.out.println("Photos with tags [egypt, museum]:");
        manager.searchByMultipleTags(Set.of("egypt", "museum")).forEach(System.out::println);
    }
}