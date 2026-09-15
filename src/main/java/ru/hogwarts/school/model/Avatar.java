package ru.hogwarts.school.model;

import jakarta.persistence.*;

@Entity
@Table(name = "avatar")
public class Avatar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String filePath;
    private long fileSize;
    private String mediaType;

    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;

    public Avatar() {
    }

    public Avatar(Long id, String filePath, long fileSize, String mediaType) {
        this.id = id;
        this.filePath = filePath;
        this.fileSize = fileSize;
        this.mediaType = mediaType;
    }

    // Геттеры и сеттеры
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFilePath() { return filePath; }
    public void setFilePath(String filePath) { this.filePath = filePath; }

    public long getFileSize() { return fileSize; }
    public void setFileSize(long fileSize) { this.fileSize = fileSize; }

    public String getMediaType() { return mediaType; }
    public void setMediaType(String mediaType) { this.mediaType = mediaType; }

    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }
}