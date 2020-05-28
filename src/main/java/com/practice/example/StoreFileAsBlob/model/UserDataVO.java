package com.practice.example.StoreFileAsBlob.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "UserData")
public class UserDataVO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "imageName")
	private String imageName;

	@Lob
	@Column(name = "imageFile")
	private byte[] imageFile;

	@Column(name = "textFileName")
	private String textFileName;

	@Lob
	@Column(name = "textFile")
	private byte[] textFile;

	@Column(name = "created_on")
	@CreationTimestamp
	private LocalDateTime createdOn;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public byte[] getImageFile() {
		return imageFile;
	}

	public void setImageFile(byte[] imageFile) {
		this.imageFile = imageFile;
	}

	public String getTextFileName() {
		return textFileName;
	}

	public void setTextFileName(String textFileName) {
		this.textFileName = textFileName;
	}

	public byte[] getTextFile() {
		return textFile;
	}

	public void setTextFile(byte[] textFile) {
		this.textFile = textFile;
	}

}
