package com.softpager.cms.entities;

import com.softpager.cms.abstracts.User;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="photos")
public class UserPhoto {

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

    @OneToOne(mappedBy = "photo", cascade = {CascadeType.MERGE,CascadeType.PERSIST,
            CascadeType.DETACH, CascadeType.REFRESH})
    public User user;

    public UserPhoto() {
    }

    public UserPhoto(String photoName, String photoType, byte[] image) {
        this.photoName = photoName;
        this.photoType = photoType;
        this.image = image;


    }   public UserPhoto(String photoName, String photoType, byte[] image, User user) {
        this.photoName = photoName;
        this.photoType = photoType;
        this.image = image;
        this.user =user;
    }
}
