package Game.PropKit;

import com.jfinal.kit.PropKit;

/**
 * @author xiaobaobao
 * @date 2019/7/30 10:55
 */
public class PropKitTest {
	public static void main(String[] args) {
		PropKit.use("Game/PropKit/server.conf");
		String id = PropKit.get("server.id");
		System.out.println(id);
		String bmy = PropKit.get("fasfasdf", "no key");
		System.out.println(bmy);
	}
}
