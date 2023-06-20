package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Request {
    private int id;
    private Module module;
    private Importance importance;
    private List<Comment> comment = new ArrayList<>();
    private Status status;
    private Date date_create;
    private Date date_update;
    private User user;
    private User responsible;
    private User observer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public Importance getImportance() {
        return importance;
    }

    public void setImportance(Importance importance) {
        this.importance = importance;
    }

    public List<Comment> getComment() {
        return comment;
    }

    public void setComment(List<Comment> comment) {
        this.comment = comment;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getDate_create() {
        return date_create;
    }

    public void setDate_create(Date date_create) {
        this.date_create = date_create;
    }

    public Date getDate_update() {
        return date_update;
    }

    public void setDate_update(Date date_update) {
        this.date_update = date_update;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getResponsible() {
        return responsible;
    }

    public void setResponsible(User responsible) {
        this.responsible = responsible;
    }

    public User getObserver() {
        return observer;
    }

    public void setObserver(User observer) {
        this.observer = observer;
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", module=" + module +
                ", importance=" + importance +
                ", comment=" + comment +
                ", status=" + status +
                ", date_create=" + date_create +
                ", date_update=" + date_update +
                ", user=" + user +
                ", responsible=" + responsible +
                ", observer=" + observer +
                '}';
    }
}