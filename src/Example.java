import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;
import java.util.Optional;

public class Example extends Application implements Runnable {

	private String name;
	private static InetAddress ip = null;
	private static MulticastSocket socket = null;

	private TextField tf_add, tf_choose, tf_addr, tf_net;
	private TextArea ta_info;
	private TextArea ta_sent, ta_number;
	private Button btn_sent, btn_close, btn_add, btn_choose, btn_upfile, btn_uppicture, btn_write, btn_con;
	private ArrayList<String> list = new ArrayList<String>();
	ComboBox<String> cBox = new ComboBox<String>();
	private ChoiceDialog<String> dialog;
	private Optional<String> result;

	@Override
	public void start(Stage stage) throws Exception {

		name = "" + System.currentTimeMillis() % 100;
		ip = InetAddress.getByName("228.9.6.88");
		socket = new MulticastSocket(6789);
		socket.joinGroup(ip);

		btn_sent = new Button(" 发送S ");
		btn_sent.setOnAction(this::btnSend);
		btn_sent.setPrefSize(60, 30);
		btn_close = new Button(" 关闭C ");
		btn_close.setPrefSize(60, 30);
		btn_upfile = new Button("上传文件");
		btn_uppicture = new Button("上传图片");
		btn_write = new Button("文档编辑");

		Label lb_net = new Label("网名：");
		tf_net = new TextField(name);
		tf_net.setPrefColumnCount(9);

		tf_add = new TextField();
		tf_add.setPrefColumnCount(12);
		btn_add = new Button("加入群聊");
		btn_choose = new Button("选择聊天对象");
		btn_choose.setOnAction(this::btnChooseHandler);

		ta_info = new TextArea();
		ta_info.setPrefSize(300, 300);
		ScrollPane pane_info = new ScrollPane(ta_info);
		pane_info.setFitToWidth(true);
		TitledPane pane_01 = new TitledPane("消息记录", pane_info);
		pane_01.setCollapsible(false);

		ta_sent = new TextArea();
		ta_sent.setPrefHeight(150);
		ScrollPane pane_sent = new ScrollPane(ta_sent);
		pane_sent.setFitToWidth(true);
		TitledPane pane_02 = new TitledPane("发送窗口", pane_sent);
		pane_02.setCollapsible(false);

		ta_number = new TextArea();
		ta_number.setPrefHeight(300);
		ta_number.setPrefWidth(240);
		ScrollPane pane_number = new ScrollPane(ta_number);
		pane_number.setFitToWidth(true);
		TitledPane pane_03 = new TitledPane("群聊在线成员", pane_number);
		pane_03.setCollapsible(false);

		VBox box = new VBox(pane_01, pane_02);

		AnchorPane apane = new AnchorPane(box, btn_sent, btn_close, lb_net, tf_net, tf_add, btn_add, pane_03,
				btn_choose, btn_upfile, btn_uppicture, btn_write);
		apane.setStyle("-fx-background-color:lightgreen");
		apane.setPrefHeight(620);
		apane.setPrefWidth(850);

		AnchorPane.setTopAnchor(box, 30.0);
		AnchorPane.setLeftAnchor(box, 30.0);

		AnchorPane.setTopAnchor(btn_sent, 540.0);
		AnchorPane.setLeftAnchor(btn_sent, 480.0);
		AnchorPane.setTopAnchor(btn_close, 540.0);
		AnchorPane.setLeftAnchor(btn_close, 400.0);

		AnchorPane.setTopAnchor(tf_add, 110.0);
		AnchorPane.setLeftAnchor(tf_add, 580.0);
		AnchorPane.setTopAnchor(btn_add, 110.0);
		AnchorPane.setLeftAnchor(btn_add, 750.0);

		AnchorPane.setTopAnchor(lb_net, 75.0);
		AnchorPane.setLeftAnchor(lb_net, 580.0);
		AnchorPane.setTopAnchor(tf_net, 70.0);
		AnchorPane.setLeftAnchor(tf_net, 620.0);

// apane.setTopAnchor(tf_addr, 30.0);
// apane.setLeftAnchor(tf_addr, 580.0);
// apane.setTopAnchor(btn_con, 30.0);
// apane.setLeftAnchor(btn_con, 750.0);

		AnchorPane.setTopAnchor(pane_03, 150.0);
		AnchorPane.setLeftAnchor(pane_03, 580.0);

// apane.setTopAnchor(tf_choose, 430.0);
// apane.setLeftAnchor(tf_choose, 580.0);
		AnchorPane.setTopAnchor(btn_choose, 150.0);
		AnchorPane.setLeftAnchor(btn_choose, 735.0);

		AnchorPane.setTopAnchor(btn_upfile, 355.0);
		AnchorPane.setLeftAnchor(btn_upfile, 30.0);
		AnchorPane.setTopAnchor(btn_uppicture, 355.0);
		AnchorPane.setLeftAnchor(btn_uppicture, 94.0);
		AnchorPane.setTopAnchor(btn_write, 355.0);
		AnchorPane.setLeftAnchor(btn_write, 158.0);

		Scene scene = new Scene(apane);
		stage.setScene(scene);
		stage.setTitle("客户端");
		stage.show();

		new Thread(this).start();
	}

	private void btnChooseHandler(ActionEvent event) {
		dialog = new ChoiceDialog<>("", list);// 选择框
		dialog.setHeaderText(null);
		dialog.setContentText("请你聊天对象：");
		result = dialog.showAndWait();
	}

	private void btnSend(ActionEvent event) {
		try {
			String msg = (tf_net.getText() + "说" + tf_add.getText());
			DatagramPacket p = new DatagramPacket(msg.getBytes(), msg.getBytes().length, ip, 6789);
			socket.send(p);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		byte[] buf = new byte[100];
		DatagramPacket recv = new DatagramPacket(buf, buf.length);
		while (true) {
			try {
				socket.receive(recv);
				String str = new String(recv.getData(), 0, recv.getLength());
				javafx.application.Platform.runLater(() -> ta_info.appendText(str + "\n"));
				System.out.println(name + "收到:" + str);
			} catch (IOException e1) {
				System.out.println("接受失败");
			}
		}
	}

	public static void main(String[] args) {
		Application.launch();
	}
}