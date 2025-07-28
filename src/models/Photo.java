package models;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

public class Photo {
    private int id;
    private String name;
    private LocalDate date;
    private String location;
    private Set<String> tags;
    public Photo(int id, String name, LocalDate date, String location, Set<String> tags) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.location = location;
        this.tags = tags;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
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
