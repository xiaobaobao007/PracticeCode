package com.intion.app.xmlConfig.hero;

import javax.xml.bind.annotation.XmlAttribute;

public class HeroConfig {
	private int id;
	private int name;//id关联 language
	private int sex;
	private int sect;//派系
	private int camp;//阵营
	private int job;//职业
	private int diamond;//英雄钻石
	private int quality;//品质
	private int level;
	private int star;
	private int starlimit;
	private int title;//称号
	private int distance;//攻击距离
	private int move;//移动距离
	private int coattid;//普通攻击
	private String skills;//英雄关联技能
	private int[] skillIds;
	public int getId() {
		return id;
	}
    @XmlAttribute
	public void setId(int id) {
		this.id = id;
	}
	public int getName() {
		return name;
	}
    @XmlAttribute
	public void setName(int name) {
		this.name = name;
	}
	public int getSex() {
		return sex;
	}
    @XmlAttribute
	public void setSex(int sex) {
		this.sex = sex;
	}
	public int getSect() {
		return sect;
	}
    @XmlAttribute
	public void setSect(int sect) {
		this.sect = sect;
	}
	public int getCamp() {
		return camp;
	}
    @XmlAttribute
	public void setCamp(int camp) {
		this.camp = camp;
	}
	public int getJob() {
		return job;
	}
    @XmlAttribute
	public void setJob(int job) {
		this.job = job;
	}
	public int getDiamond() {
		return diamond;
	}
    @XmlAttribute
	public void setDiamond(int diamond) {
		this.diamond = diamond;
	}
	public int getQuality() {
		return quality;
	}
    @XmlAttribute
	public void setQuality(int quality) {
		this.quality = quality;
	}
	public int getLevel() {
		return level;
	}
    @XmlAttribute
	public void setLevel(int level) {
		this.level = level;
	}
	public int getStar() {
		return star;
	}
    @XmlAttribute
	public void setStar(int star) {
		this.star = star;
	}
	public int getStarlimit() {
		return starlimit;
	}
    @XmlAttribute
	public void setStarlimit(int starlimit) {
		this.starlimit = starlimit;
	}
	public int getTitle() {
		return title;
	}
    @XmlAttribute
	public void setTitle(int title) {
		this.title = title;
	}
	public int getDistance() {
		return distance;
	}
    @XmlAttribute
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public int getMove() {
		return move;
	}
    @XmlAttribute
	public void setMove(int move) {
		this.move = move;
	}
	public int getCoattid() {
		return coattid;
	}
	@XmlAttribute
	public void setCoattid(int coattid) {
		this.coattid = coattid;
	}
	@XmlAttribute
	public String getSkills() {
		return skills;
	}
	public void setSkills(String skills) {
		this.skills = skills;
	}
	public void initStruture() {
		if(skills!=null) {
			String[] ss = skills.split(",");
			this.skillIds = new int[ss.length];
			for(int i = 0;i<ss.length;i++) {
				this.skillIds[i] = Integer.parseInt(ss[i]);
			}
		}
	}
	
	public int[] getSkillIds() {
		return skillIds;
	}
}
