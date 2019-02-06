package net.thumbtack.school.database.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class School {
    private int id;
    private String name;
    private int year;
    private List<Group> groups;

    public School(){
        super();
        this.groups = new ArrayList<>();
    }

    public School(Integer id){
        super();
        this.id = id;
    }

    public School(Integer id, String name, int year, List<Group> groups) {
        super();
        this.id = id;
        this.name = name;
        this.year = year;
        this.groups = groups;
    }

    public School(Integer id, String name, int year) {
        super();
        this.id = id;
        this.name = name;
        this.year = year;
        this.groups = new ArrayList<>();
    }

    public School(String name, int year) {
        super();
        this.name = name;
        this.year = year;
        this.groups = new ArrayList<>();
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
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public List<Group> getGroups() {
        return groups;
    }
    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public void addGroup(Group group){
        this.groups.add(group);
    }
    public void removeGroup(Group group){
        this.groups.remove(group);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof School)) return false;
        School school = (School) o;
        return getId() == school.getId() &&
                getYear() == school.getYear() &&
                Objects.equals(getName(), school.getName()) &&
                Objects.equals(getGroups(), school.getGroups());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getName(), getYear(), getGroups());
    }

    @Override
    public String toString() {
        return "School{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", year=" + year +
                ", groups=" + groups +
                '}';
    }
}
