package NIO.Netty;

import java.io.Serializable;

/**
 * @author xiaobaobao
 * @date 2019/12/15，17:04
 */
public class Msg implements Serializable {

	private static final long serialVersionUID = 1L;

	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
