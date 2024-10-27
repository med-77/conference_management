package com.example.project1;

import java.sql.Blob;

public class Article {
    private int id;
    private String name;
    private Blob pdfFile;
    private int idParticipant;
    private int idConference;
    private String status;

    public Article(int id, String name, Blob pdfFile, int idParticipant, int idConference, String status) {
        this.id = id;
        this.name = name;
        this.pdfFile = pdfFile;
        this.idParticipant = idParticipant;
        this.idConference = idConference;
        this.status = status;
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

    public Blob getPdfFile() {
        return pdfFile;
    }

    public void setPdfFile(Blob pdfFile) {
        this.pdfFile = pdfFile;
    }

    public int getIdParticipant() {
        return idParticipant;
    }

    public void setIdParticipant(int idParticipant) {
        this.idParticipant = idParticipant;
    }

    public int getIdConference() {
        return idConference;
    }

    public void setIdConference(int idConference) {
        this.idConference = idConference;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", idParticipant=" + idParticipant +
                ", idConference=" + idConference +
                ", status='" + status + '\'' +
                '}';
    }

}
