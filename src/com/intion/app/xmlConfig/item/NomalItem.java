package com.intion.app.xmlConfig.item;

import javax.xml.bind.annotation.XmlAttribute;

public class NomalItem {
	private int id;
	private int name;
	private int kind;
	private int quality;
	private int maxstack;
	
    @XmlAttribute
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@XmlAttribute
	public int getName() {
		return name;
	}
	public void setName(int name) {
		this.name = name;
	}
	@XmlAttribute
	public int getKind() {
		return kind;
	}
	public void setKind(int kind) {
		this.kind = kind;
	}
	@XmlAttribute
	public int getQuality() {
		return quality;
	}
	public void setQuality(int quality) {
		this.quality = quality;
	}
	@XmlAttribute
	public int getMaxstack() {
		return maxstack;
	}
	public void setMaxstack(int maxstack) {
		this.maxstack = maxstack;
	}
}
