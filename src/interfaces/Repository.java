package interfaces;

import models.Photo;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface Repository {
    void store(Photo photo);
    List<Photo> searchByTag(String tag);
    List<Photo> searchByDate(LocalDate date);
    List<Photo> searchByLocation(String location);
    Set<Photo> searchByMultipleTags(Set<String> tags);
}
