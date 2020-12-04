package com.intion.app.fight.constant;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * buff效果的叠的最大层数
 *
 * @author xiaobaobao
 * @date 2020/12/2，18:50
 */
public class BuffConstant {

	private static final Map<Integer, BuffType> buff_map = new HashMap<>(BuffType.values().length, 1.0F);//buff的一些参数

	public static final Set<Integer> BUFF_SET = new HashSet<>();//增益buff，用来区别减益buff。只要不是增益就是减益
	public static final Set<Integer> NOT_OPERATION_BUFF_SET = new HashSet<>();//不允许进行任何操作,或者自动移动等
	public static final Set<Integer> EFFECT_BEFORE_ROUND = new HashSet<>();//回合开始前进行buff判定

	static {
		for (BuffType value : BuffType.values()) {
			buff_map.put(value.buffId, value);
		}

		BUFF_SET.add(BuffType.ADD_BLOOD.buffId);
		BUFF_SET.add(BuffType.THEINJURY.buffId);
		BUFF_SET.add(BuffType.INVINCIBLE.buffId);
		BUFF_SET.add(BuffType.STEALTH.buffId);

		NOT_OPERATION_BUFF_SET.add(BuffType.BE_STONE.buffId);
		NOT_OPERATION_BUFF_SET.add(BuffType.VERTIGO.buffId);
		NOT_OPERATION_BUFF_SET.add(BuffType.FROZEN.buffId);
		NOT_OPERATION_BUFF_SET.add(BuffType.WINDING.buffId);
		NOT_OPERATION_BUFF_SET.add(BuffType.BE_SARCASM.buffId);
		NOT_OPERATION_BUFF_SET.add(BuffType.FEAR.buffId);

		EFFECT_BEFORE_ROUND.add(BuffType.BLEEDING.buffId);
		EFFECT_BEFORE_ROUND.add(BuffType.POISONING.buffId);
		EFFECT_BEFORE_ROUND.add(BuffType.ADD_BLOOD.buffId);
	}

	public static BuffType getConstant(int buffId) {
		return buff_map.get(buffId);
	}

}