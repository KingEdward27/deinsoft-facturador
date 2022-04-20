package com.deinsoft.efacturador3commons.model;

import javax.persistence.Id;

import org.bson.types.Binary;

@org.springframework.data.mongodb.core.mapping.Document(collection = "documents")
public class Document {

    @Id
    private String id;

    private String title;

    private Binary file;

    public Document() {
        super();
    }

    public Document(String title) {
        super();
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Binary getFile() {
        return file;
    }

    public void setFile(Binary file) {
        this.file = file;
    }

}
