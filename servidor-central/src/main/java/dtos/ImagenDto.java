package dtos;

import java.util.Optional;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
@XmlAccessorType(XmlAccessType.FIELD)
public class ImagenDto {
	private byte[] content;
	private String name;
	
	public ImagenDto() {}
		
	public byte[] getContent() {
		return content;
	}
	
	public String getExtension() {
		return getFileExtension(name).get();
	}
	
	public void setContent(byte[] content) {
		this.content = content;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	private static Optional<String> getFileExtension(String filename) {
	    return Optional.ofNullable(filename)
	      .filter(f -> f.contains("."))
	      .map(f -> f.substring(filename.lastIndexOf(".") + 1));
	}
}
