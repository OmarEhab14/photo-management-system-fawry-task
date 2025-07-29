package services;

import interfaces.Repository;
import models.Photo;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class PhotoManager {
    private Repository photoRepository;
    public PhotoManager(Repository photoRepository) {
        this.photoRepository = photoRepository;
    }

    public void uploadPhoto(Photo photo) {
        photoRepository.store(photo);
    }

    public List<Photo> searchByTag(String tag) {
        tag = tag.toLowerCase();
        return photoRepository.searchByTag(tag);
    }

    public List<Photo> searchByDate(LocalDate date) {
        return photoRepository.searchByDate(date);
    }

    public List<Photo> searchByLocation(String location) {
        location = location.toLowerCase();
        return photoRepository.searchByLocation(location);
    }

    public Set<Photo> searchByMultipleTags(Set<String> tags) {
        return photoRepository.searchByMultipleTags(tags);
    }

    public Repository getPhotoRepository() {
        return photoRepository;
    }

    public void setPhotoRepository(Repository photoRepository) {
        this.photoRepository = photoRepository;
    }
}
