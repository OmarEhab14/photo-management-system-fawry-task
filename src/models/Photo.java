package models;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

public class Photo {
    private int id;
    private String name;
    private LocalDate date;
    private String city;
    private Set<String> tags;
    private Location location;
    public Photo(int id, String name, LocalDate date, String city, Set<String> tags, Location location) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.city = city;
        this.tags = tags;
        this.location = location;
    }

    @Override
    public String toString() {
        return "Photo: " + name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCity() {
        return city;
    }

    public void setLocation(String location) {
        this.city = city;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || o.getClass() != this.getClass()) return false;
        return this.id == ((Photo)o).id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
