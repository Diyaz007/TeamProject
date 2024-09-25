package com.example.teamproject.entity;

import lombok.*;

import jakarta.persistence.*;

@Entity
@Table(name = "images")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "ORIGINAL_FILE_NAME")
    private String originalFileName;
    @Column(name = "SIZE")
    private Long size;
    @Column(name = "CONTENT_TYPE")
    private String contentType;
    @Column(name = "IS_PREVIEW_IMAGE")
    private boolean isPreviewImage;
    @Lob
    private byte[] bytes;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Company company;
}
