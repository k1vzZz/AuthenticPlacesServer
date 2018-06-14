package com.developer.server.authenticplaces.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "login")
    private String login;

    @Column(name = "image_url")
    private String url;

    @OneToMany(mappedBy = "creator", fetch = FetchType.LAZY)
    private List<Marker> markers;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<Comment> comments;

    public User() {
    }

    public User(String login) {
        this.login = login;
    }

    public User(Integer id, String login, String urlImage) {
        this.id = id;
        this.login = login;
        this.url = urlImage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public List<Marker> getMarkers() {
        return markers;
    }

    public void setMarkers(List<Marker> markers) {
        this.markers = markers;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
