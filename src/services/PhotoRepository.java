package services;

import interfaces.Repository;
import models.CityLocations;
import models.LocationZone;
import models.Photo;

import java.time.LocalDate;
import java.util.*;

public class PhotoRepository implements Repository {
    private List<Photo> photos;
    private Map<String, List<Photo>> tagToPhotos;
    private Map<LocalDate, List<Photo>> dateToPhotos;
    private final Set<Integer> photoIds;

    public PhotoRepository() {
        photos = new ArrayList<>();
        tagToPhotos = new HashMap<>();
        dateToPhotos = new HashMap<>();
        photoIds = new HashSet<>();
    }

    @Override
    public void store(Photo photo) {
        if (photoIds.contains(photo.getId())) return;
        photos.add(photo);
        photoIds.add(photo.getId());
        storePhotoByTags(photo);
        storePhotoByDate(photo);
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
    public List<Photo> searchByLocation(String city) {
        List<Photo> result = new ArrayList<>();
        LocationZone zone = CityLocations.get(city);

        if (zone == null) return result;

        for (Photo photo : photos) {
            double distance = haversine(
                    zone.getLatitude(), zone.getLongitude(),
                    photo.getLocation().getLatitude(), photo.getLocation().getLongitude()
            );

            if (distance <= zone.getRadius()) {
                result.add(photo);
            }
        }

        return result;
    }

    private double haversine(double lat1, double lon1, double lat2, double lon2) {
        final int EARTH_RADIUS = 6371;

        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(lat1) * Math.cos(lat2) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c;
    }

    @Override
    public Set<Photo> searchByMultipleTags(Set<String> tags) {
        Set<Photo> photos = new HashSet<>();
        for (String tag : tags) {
            tag = tag.toLowerCase();
            List<Photo> photosFromTag = tagToPhotos.getOrDefault(tag, Collections.emptyList());
            photos.addAll(photosFromTag);
        }
        return photos;
    }

    private void storePhotoByTags(Photo photo) {
        for (String tag : photo.getTags()) {
            tag = tag.toLowerCase();
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

    public Map<String, List<Photo>> getTagToPhotos() {
        return tagToPhotos;
    }

    public Map<LocalDate, List<Photo>> getDateToPhotos() {
        return dateToPhotos;
    }
}
