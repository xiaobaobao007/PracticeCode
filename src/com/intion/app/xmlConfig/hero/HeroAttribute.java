package com.intion.app.xmlConfig.hero;

import javax.xml.bind.annotation.XmlAttribute;

public class HeroAttribute {
	private int id;
	private int heroid;//英雄id
	private int maxhp;
	private int patt;//物理攻击
	private int matt;//法术攻击
	private int pdef;//物防
	private int mdef;//法防
	
	//千分比
	private int critper;//暴击率
	private int critdamper;//爆伤 
	private int coattper;//反伤
	
	private float maxhpgrow;//血量等级成长系数
	private float pattgrow;//物攻等级成长系数
	private float mattgrow;//法攻等级成长系数
	private float pdefgrow;//物防等级成长系数
	private float mdefgrow;//法防等级成长系数
	public int getId() {
		return id;
	}
	@XmlAttribute
	public void setId(int id) {
		this.id = id;
	}
	public int getHeroid() {
		return heroid;
	}
	@XmlAttribute
	public void setHeroid(int heroid) {
		this.heroid = heroid;
	}
	public int getMaxhp() {
		return maxhp;
	}
	@XmlAttribute
	public void setMaxhp(int maxhp) {
		this.maxhp = maxhp;
	}
	public int getPatt() {
		return patt;
	}
	@XmlAttribute
	public void setPatt(int patt) {
		this.patt = patt;
	}
	public int getMatt() {
		return matt;
	}
	@XmlAttribute
	public void setMatt(int matt) {
		this.matt = matt;
	}
	public int getPdef() {
		return pdef;
	}
	@XmlAttribute
	public void setPdef(int pdef) {
		this.pdef = pdef;
	}
	public int getMdef() {
		return mdef;
	}
	@XmlAttribute
	public void setMdef(int mdef) {
		this.mdef = mdef;
	}
	public int getCritper() {
		return critper;
	}
	@XmlAttribute
	public void setCritper(int critper) {
		this.critper = critper;
	}
	public int getCritdamper() {
		return critdamper;
	}
	@XmlAttribute
	public void setCritdamper(int critdamper) {
		this.critdamper = critdamper;
	}
	public int getCoattper() {
		return coattper;
	}
	@XmlAttribute
	public void setCoattper(int coattper) {
		this.coattper = coattper;
	}
	public float getMaxhpgrow() {
		return maxhpgrow;
	}
	@XmlAttribute
	public void setMaxhpgrow(float maxhpgrow) {
		this.maxhpgrow = maxhpgrow;
	}
	public float getPattgrow() {
		return pattgrow;
	}
	@XmlAttribute
	public void setPattgrow(float pattgrow) {
		this.pattgrow = pattgrow;
	}
	public float getMattgrow() {
		return mattgrow;
	}
	@XmlAttribute
	public void setMattgrow(float mattgrow) {
		this.mattgrow = mattgrow;
	}
	public float getPdefgrow() {
		return pdefgrow;
	}
	@XmlAttribute
	public void setPdefgrow(float pdefgrow) {
		this.pdefgrow = pdefgrow;
	}
	public float getMdefgrow() {
		return mdefgrow;
	}
	@XmlAttribute
	public void setMdefgrow(float mdefgrow) {
		this.mdefgrow = mdefgrow;
	}
}
