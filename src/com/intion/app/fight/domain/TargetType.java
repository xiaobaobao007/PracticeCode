package com.intion.app.fight.domain;

/**
 * 选中类型
 *
 * @author xiaobaobao
 * @date 2020/11/26，20:37
 */
public enum TargetType {
	//  只作用于指定点
	//  ▢  ▢  ▢  ▢  ▢
	//  ▢  ▢  ▢  ▢  ▢
	//  ▢  ▢  ▤  ▢  ▢
	//  ▢  ▢  ▢  ▢  ▢
	//  ▢  ▢  ▢  ▢  ▢
	ONLY_THE_POSITION,
	//  作用指定点并且一定距离的十字范围内，范围是2如图所示
	//  ▢  ▢  ◼  ▢  ▢
	//  ▢  ▢  ◼  ▢  ▢
	//  ◼  ◼  ▤  ◼  ◼
	//  ▢  ▢  ◼  ▢  ▢
	//  ▢  ▢  ◼  ▢  ▢
	CROSS_THE_POSITION,
	//  圆，指定范围内都能达到，范围是2如图所示
	//  ▢  ▢  ◼  ▢  ▢
	//  ▢  ◼  ◼  ◼  ▢
	//  ◼  ◼  ▤  ◼  ◼
	//  ▢  ◼  ◼  ◼  ▢
	//  ▢  ▢  ◼  ▢  ▢
	CIRCLE,
}
