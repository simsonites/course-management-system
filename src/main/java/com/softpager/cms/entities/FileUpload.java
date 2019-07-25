package com.softpager.cms.entities;

import com.softpager.cms.abstracts.CMSUser;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "photos")
public class FileUpload {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "photo_id")
    private long id;

    @Column(name = "photo_name")
    private String photoName;

    @Column(name = "photo_type")
    private String photoType;

    @Lob
    @Column(name = "photo_image")
    private byte[] image;

    @OneToOne(mappedBy = "photo")
    public CMSUser user;

    public FileUpload() {
    }

    public FileUpload(String photoName, String photoType, byte[] image) {
        this.photoName = photoName;
        this.photoType = photoType;
        this.image = image;


    }

    public FileUpload(String photoName, String photoType, byte[] image, CMSUser user) {
        this.photoName = photoName;
        this.photoType = photoType;
        this.image = image;
        this.user = user;
    }
}
