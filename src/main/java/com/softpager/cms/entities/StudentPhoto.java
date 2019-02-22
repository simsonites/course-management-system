package com.softpager.cms.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="photos")
public class StudentPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="photo_id")
    private long id;

    @Column(name="photo_name")
    private String photoName;

    @Column(name="photo_type")
    private String photoType;

    @Lob
    @Column(name="photo_image")
    private byte[] image;

    @OneToOne(mappedBy = "photo", cascade= {CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
    private Student student;


    public StudentPhoto() {
    }

    public StudentPhoto(String photoName, String photoType, byte[] image) {
        this.photoName = photoName;
        this.photoType = photoType;
        this.image = image;
    }
}
