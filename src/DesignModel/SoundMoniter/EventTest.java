package DesignModel.SoundMoniter;

/**
 * @author xiaobaobao
 * @date 2020/4/11，15:55
 */
public class EventTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Listener listener = new MyListener();
		//加入监听者
		Context.addListener(listener);
		//模拟bundle安装完毕事件触发
		Context.sendNotification(new Event(Event.INSTALLED, null));
	}

}
