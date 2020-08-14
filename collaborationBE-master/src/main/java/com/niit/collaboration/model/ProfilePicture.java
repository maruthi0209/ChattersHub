package com.niit.collaboration.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;
@Component
@Entity
@Table
public class ProfilePicture 
{
	@Id
private String name;
	@Lob
private byte[] image;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	
}