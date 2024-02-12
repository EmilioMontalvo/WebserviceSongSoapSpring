package com.example.webservicesongsoap.model;


import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Entity(name = "TBL_SONG")
@Data
@XmlRootElement(name = "Song")
public class SongModel {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @XmlElement
    @Column(name = "ID_SONG")
    private int id;
    @XmlElement
    @Column(name = "SONG_NAME")
    private String name;
    @XmlElement
    @Column(name = "SONG_PATH")
    private String path;
    @XmlElement
    @Column(name = "PLAYS")
    private int plays;

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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getPlays() {
        return plays;
    }

    public void setPlays(int plays) {
        this.plays = plays;
    }
}
