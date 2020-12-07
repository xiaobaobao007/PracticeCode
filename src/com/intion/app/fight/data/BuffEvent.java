package com.intion.app.fight.data;

import java.util.LinkedList;

/**
 * buff的表现
 */
public class BuffEvent {

	private int status;//0清除，1新增，2已存在的生效
	private int buffId;//buff的id
	private LinkedList<Long> targetIds;//影响到的英雄id

	public BuffEvent() {
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getBuffId() {
		return buffId;
	}

	public void setBuffId(int buffId) {
		this.buffId = buffId;
	}

	public void addTargetId(long targetId) {
		if (targetIds == null) {
			targetIds = new LinkedList<>();
		}
		targetIds.add(targetId);
	}
}
