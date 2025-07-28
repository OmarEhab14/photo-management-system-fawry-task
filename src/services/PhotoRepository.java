package services;

import interfaces.Repository;
import models.Photo;

import java.time.LocalDate;
import java.util.*;

public class PhotoRepository implements Repository {
    private Map<String, List<Photo>> tagToPhotos;
    private Map<LocalDate, List<Photo>> dateToPhotos;
    private Map<String, List<Photo>> locationToPhotos;
    private final Set<Integer> photoIds;

    public PhotoRepository() {
        tagToPhotos = new HashMap<>();
        dateToPhotos = new HashMap<>();
        locationToPhotos = new HashMap<>();
        photoIds = new HashSet<>();
    }

    @Override
    public void store(Photo photo) {
        if (photoIds.contains(photo.getId())) return;
        photoIds.add(photo.getId());
        storePhotoByTags(photo);
        storePhotoByDate(photo);
        storePhotoByLocation(photo);
    }

    @Override
    public List<Photo> searchByTag(String tag) {
        return tagToPhotos.getOrDefault(tag, Collections.emptyList());
    }

    @Override
    public List<Photo> searchByDate(LocalDate date) {
        return dateToPhotos.getOrDefault(date, Collections.emptyList());
    }

    @Override
    public List<Photo> searchByLocation(String location) {
        return locationToPhotos.getOrDefault(location, Collections.emptyList());
    }

    @Override
    public Set<Photo> searchByMultipleTags(Set<String> tags) {
        Set<Photo> photos = new HashSet<>();
        for (String tag : tags) {
            List<Photo> photosFromTag = tagToPhotos.getOrDefault(tag, Collections.emptyList());
            photos.addAll(photosFromTag);
        }
        return photos;
    }

    private void storePhotoByTags(Photo photo) {
        for (String tag : photo.getTags()) {
            List<Photo> photosFromTag = tagToPhotos.getOrDefault(tag, new ArrayList<>());
            photosFromTag.add(photo);
            tagToPhotos.put(tag, photosFromTag);
        }
    }
    private void storePhotoByDate(Photo photo) {
        LocalDate date = photo.getDate();
        List<Photo> photosFromDate = dateToPhotos.getOrDefault(date, new ArrayList<>());
        photosFromDate.add(photo);
        dateToPhotos.put(date, photosFromDate);
    }
    private void storePhotoByLocation(Photo photo) {
        String location = photo.getLocation();
        List<Photo> photosFromLocation = locationToPhotos.getOrDefault(location, new ArrayList<>());
        photosFromLocation.add(photo);
        locationToPhotos.put(location, photosFromLocation);
    }

    public Map<String, List<Photo>> getTagToPhotos() {
        return tagToPhotos;
    }

    public Map<LocalDate, List<Photo>> getDateToPhotos() {
        return dateToPhotos;
    }

    public Map<String, List<Photo>> getLocationToPhotos() {
        return locationToPhotos;
    }
}
