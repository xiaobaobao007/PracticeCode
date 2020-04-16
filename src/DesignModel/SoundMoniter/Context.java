package DesignModel.SoundMoniter;

/**
 * 监听器模式
 *
 * @author xiaobaobao
 * @date 2020/4/11，15:53
 */

import java.util.ArrayList;
import java.util.List;

public class Context {
	private static List<Listener> list = new ArrayList<>();

	public static void addListener(Listener listener) {
		list.add(listener);
	}

	public static void removeListener(Listener listener) {
		list.remove(listener);
	}

	public static void sendNotification(Event event) {
		for (Listener listener : list) {
			listener.onChange(event);
		}
	}
}