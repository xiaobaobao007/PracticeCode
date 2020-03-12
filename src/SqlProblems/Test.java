package SqlProblems;

import java.sql.*;

/**
 * @author xiaobaobao
 * @date 2020/3/12，22:23
 */
public class Test {
	public static void main(String[] args) {
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jfinal?serverTimezone=Asia/Shanghai", "root", "123456");
			PreparedStatement pstmt = conn.prepareStatement("select * from user where account = ? and password=?;");
			pstmt.setString(1, "root");
			pstmt.setString(2, "root");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println(rs);
			}
			System.out.println("==============");
			pstmt = conn.prepareStatement("select * from user where account = ? and password=?;");
			pstmt.setString(1, "root or 1=1;#");
			pstmt.setString(2, "error");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
