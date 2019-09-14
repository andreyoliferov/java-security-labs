package edu.otib.lab_xss.entity;

import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;

import java.util.Date;

public class Post {
    private int id;
    private String author;
    private String text;
    private Date dateCreated;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return sanitizer(author);
    }

    public void setAuthor(String author) {
        this.author = sanitizer(author);
    }

    public String getText() {
        return sanitizer(text);
    }

    public void setText(String text) {
        this.text = sanitizer(text);
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    /* Защита от уязвимости внедрения XSS */
    private String sanitizer(String data) {
        PolicyFactory sanitizer = Sanitizers.FORMATTING.and(Sanitizers.LINKS).and(Sanitizers.IMAGES);
        return sanitizer.sanitize(data);
    }
}
