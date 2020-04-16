package DesignModel.SoundMoniter;

/**
 * @author xiaobaobao
 * @date 2020/4/11，15:55
 */
public class Event {
	public static final int INSTALLED = 1;
	public static final int STARTED = 2;
	public static final int RESOLVED = 3;
	public static final int STOPPED = 4;
	public static final int UNRESOLVED = 5;
	public static final int UNINSTALLED = 6;
	private int type;
	private Object source;

	public Event(int type, Object source) {
		this.type = type;
		this.source = source;
	}

	public int getType() {
		return type;
	}

	public Object getSource() {
		return source;
	}
}