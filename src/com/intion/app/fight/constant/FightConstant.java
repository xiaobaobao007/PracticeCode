package com.intion.app.fight.constant;

/**
 * 战斗中的参数
 *
 * @author xiaobaobao
 * @date 2020/11/26，15:45
 */
public class FightConstant {

	public static final int FIGHT_RESULT_ATK_WIN = 1;//攻击方胜利
	public static final int FIGHT_RESULT_DEF_WIN = 2;//防守方胜利

	//比率
	public static final int HUNDRED = 100;//百分比
	public static final int THOUSAND = 1000;//千分比
	public static final int TEN_THOUSAND = 10000;//万分比

	//属性
	public static final int PROPRTTY_MAXHP = 1;//最大血量
	public static final int PROPRTTY_PATT = 3;//物攻
	public static final int PROPRTTY_MATT = 4;//法攻
	public static final int PROPRTTY_PDEF = 5;//物防
	public static final int PROPRTTY_MDEF = 6;//法防
	public static final int PROPRTTY_CRIT_PER = 7;//暴击率
	public static final int PROPRTTY_CRITDAM_PER = 8;//暴伤倍率
	public static final int PROPRTTY_TRUEDAM = 9;//固定伤害
	public static final int PROPRTTY_LOWDAM = 10;//免伤
	public static final int PROPRTTY_COATT_PER = 11;//反击倍率
	public static final int PROPRTTY_MAXHP_PER = 12;//最大血量增加百分比
	public static final int PROPRTTY_PATT_PER = 13;//物攻增加百分比
	public static final int PROPRTTY_MATT_PER = 14;//法攻增加百分比
	public static final int PROPRTTY_PDEF_PER = 15;//物防增加百分比
	public static final int PROPRTTY_MDEF_PER = 16;//法防增加百分比
	public static final int PROPRTTY_DISTANCE = 17;//移动
	public static final int PROPRTTY_MOVE = 18;//攻距
	public static final int PROPRTTY_SKILL = 19;//可否释放技能
	public static final int PROPRTTY_COATT = 20;//可否普通攻击

	//攻击类型
	public static final int DAMAGE_TYPE_PHY = 1;// 物理
	public static final int DAMAGE_TYPE_MAG = 2;// 法攻

	//战斗中参数
	public static final int MAX_ROUND = 15 * 2;
	public static final int ROUND_DELAY = 10;//一回合等待玩家多久操作 s
	public static final int SHOW_FIGHT_DELAY = 10;//等待客户端播放战斗画面多久 s

	//战斗过程中玩家的操作
	public static final int FIGHT_CALCULATE_BUFF = 0;//计算buff
	public static final int FIGHT_PLAYER_OPERATION_TYPE_NOTHING = 1;//待命
	public static final int FIGHT_PLAYER_OPERATION_TYPE_ATTACK = 2;//攻击
	public static final int FIGHT_PLAYER_OPERATION_TYPE_MOVE = 3;//只移动
	public static final int FIGHT_PLAYER_OPERATION_TYPE_SHOW_OVER = 4;//客户端战斗播放完成，程序可以继续往下计时

	//地图
	public static final int AREA_WIDTH = 6;//宽度
	public static final int AREA_HEIGHT = 5;//高度
	public static final int AREA_ATK_HEIGHT_MAX_LIMIT = 1;//0-1
	public static final int AREA_DEF_HEIGHT_MIN_LIMIT = 4;//4-5

	//buff
	public static final int BUFF_REMOVE = 0;//buff被清除
	public static final int BUFF_ADD = 1;//buff新增新的
	public static final int BUFF_EFFECT = 2;//buff生效

	//检测玩家操作的事件
	public static final int OPERATION_TYPE = 1;//能否控制
	public static final int SKILL_TYPE = 2;//能否使用技能
	public static final int MOVE_TYPE = 3;//能否移动并检测移动距离
	public static final int ONLY_MOVE_TYPE = 4;//能否移动不检测移动距离
	public static final int NORMAL_ATTACK_TYPE = 5;//能否普通攻击
	public static final int BE_ATTACK_TYPE = 6;//能否被选中
	public static final int BE_ATTACK_RETURN_TYPE = 7;//能否进行反击

	//地图位置参数
	public static final int STAY_POSITION = -1;//不移动的位置设置参数

	//能否进行初始化位置
	public static boolean fightUnitCanInitPosition(boolean isAtk, int x, int y) {
		return true;
		// if (isAtk) {
		// 	return x >= 0 && x < AREA_WIDTH && y >= 0 && y <= AREA_ATK_HEIGHT_MAX_LIMIT;
		// } else {
		// 	return x >= 0 && x < AREA_WIDTH && y >= AREA_DEF_HEIGHT_MIN_LIMIT && y < AREA_HEIGHT;
		// }
	}

	//能否移动到指定位置
	public static boolean fightUnitCanMove(int x, int y) {
		return x >= 0 && x < AREA_WIDTH && y >= 0 && y < AREA_HEIGHT;
	}

	//计算两点间距离
	public static int calculateDistance(int x, int y, int x1, int y1) {
		return Math.abs(x1 - x) + Math.abs(y1 - y);
	}

}
