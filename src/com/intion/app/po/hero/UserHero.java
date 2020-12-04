package com.intion.app.po.hero;

public class UserHero {
	private long hid;
	private long uid;//
	private int configId;//原型id
	private int level;
	private int exp;//经验
	private int star;
	private int title;//神位称号
	private int quality;
	private int[] skillLevels;//技能等级数组

	//属性
	private float hp;//血量
	private float patt;//物理攻击
	private float matt;//法术攻击
	private float pdef;//物理防御
	private float mdef;//法术防御
	private int critper;//暴击率
	private int critdamper;//爆伤
	private int coattper;//反伤

	public long getHid() {
		return hid;
	}

	public void setHid(long hid) {
		this.hid = hid;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public int getConfigId() {
		return configId;
	}

	public void setConfigId(int configId) {
		this.configId = configId;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getTitle() {
		return title;
	}

	public void setTitle(int title) {
		this.title = title;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}

	public int getQuality() {
		return quality;
	}

	public void setQuality(int quality) {
		this.quality = quality;
	}

	public float getHp() {
		return hp;
	}

	public void setHp(float hp) {
		this.hp = hp;
	}

	public float getPatt() {
		return patt;
	}

	public void setPatt(float patt) {
		this.patt = patt;
	}

	public float getMatt() {
		return matt;
	}

	public void setMatt(float matt) {
		this.matt = matt;
	}

	public float getPdef() {
		return pdef;
	}

	public void setPdef(float pdef) {
		this.pdef = pdef;
	}

	public float getMdef() {
		return mdef;
	}

	public void setMdef(float mdef) {
		this.mdef = mdef;
	}

	public int getCritper() {
		return critper;
	}

	public void setCritper(int critper) {
		this.critper = critper;
	}

	public int getCritdamper() {
		return critdamper;
	}

	public void setCritdamper(int critdamper) {
		this.critdamper = critdamper;
	}

	public int getCoattper() {
		return coattper;
	}

	public void setCoattper(int coattper) {
		this.coattper = coattper;
	}

	public int[] getSkillLs() {
		return skillLevels;
	}

}
