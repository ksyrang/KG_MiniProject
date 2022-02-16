package common;



import java.util.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class CommonService {

	public static void Msg(String content) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("알림");
		alert.setContentText(content);
		alert.show();
	}
	
	public static void WindowClose(Parent form) {
		Stage stage = (Stage)form.getScene().getWindow();
		stage.close();
	}
	
	public static void LogOut() {

		
	}
	
	public static LocalDate DateCnvt(java.sql.Date sqldate) {
		
//		java.util.Date date = new java.sql.Date(rs.getDate("todate").getTime());
		Date utilDate = new Date(sqldate.getTime());
		// 1. Date 객체 생성 (현재날짜)
		LocalDate localDate = utilDate.toInstant() // Date -> Instant
		.atZone(ZoneId.systemDefault()) // Instant -> ZonedDateTime
		.toLocalDate(); // ZonedDateTime -> LocalDate
		// 3. Date -> LocalDateTime
//		LocalDateTime localDateTime = date.toInstant() // Date -> Instant
//		.atZone(ZoneId.systemDefault()) // Instant -> ZonedDateTime
//		.toLocalDateTime(); // ZonedDateTime -> LocalDateTime
//		// 4. Date, LocalDate, LocalDateTime 출력
//		System.out.println(date); // Sun Jun 20 21:09:20 KST 2021
//		System.out.println(localDate); // 2021-06-20
//		System.out.println(localDateTime); // 2021-06-20T21:09:20.461
		return localDate;
	}
	
}
