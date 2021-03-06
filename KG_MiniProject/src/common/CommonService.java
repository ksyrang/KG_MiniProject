package common;



import java.util.Date;
import java.util.Locale;
import java.util.Optional;

import Main.login.LoginController;
import Main.main.Controller;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class CommonService {

	public static String CvtPriceComma(String Data) {
		String RowData = Data.replace(",", "");
		//입력 개수 한정
		RowData = getLengthLimit(8,RowData);
		//해당 데이터를 int화
		int RowPriceint = Integer.parseInt(RowData);
		//int형 데이터에 ',' 추가
		String FormatPrice = NumberFormat.getInstance(Locale.KOREA).format(RowPriceint);
		return FormatPrice;
	}
	
	
	public static boolean CheckMsg(String content) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("확인 알림");
		alert.setContentText(content);
		Optional<ButtonType> result = alert.showAndWait();
		if(result.get() == ButtonType.OK) {
			return true;						
		}else return false;
		
	}
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
	
	public static boolean CompareDate(LocalDate Start, LocalDate End) {
		if(Start.isAfter(End)) return true;//Start보다 End가 일찍이라면 false
		else return false;
	}

	public static LocalDate StringtoLocalDate(String Date) {		
		return LocalDate.parse(Date, DateTimeFormatter.ISO_DATE);
	}
	public static String LocaltoStringDate(LocalDate Date) {
		return Date.toString();
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

	//sql.LocalDate -> sql.Date
	public static java.sql.Date LocalDateCnvt(LocalDate dateToConvert) {
		
		Date utilDate = java.sql.Date.valueOf(dateToConvert);
		long timeInMilliSeconds = utilDate.getTime();
		java.sql.Date sqlDate = new java.sql.Date(timeInMilliSeconds);
		
		return sqlDate;
		
	}
	public static java.sql.Date CnvtsqlDate(java.util.Date utilDate){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String formattedDate = simpleDateFormat.format(utilDate);
		java.sql.Date cnvtdate = java.sql.Date.valueOf(formattedDate);
		
		return cnvtdate;
	}
	
	public static String getNowDatetoString() {
		SimpleDateFormat Sdf = new SimpleDateFormat("yy년 MM월 dd일");
		return Sdf.format(new Date());
		}
	
	
//	public static void OpenPage(Parent Form) {
//		Stage  stage = new Stage();
//		
//		
//		stage.setScene(new Scene(Form));
//		stage.show();		
//	}
	
	public static String getLengthLimit(int Length, String data) {
		String tmp = data;
		if (data.length() > Length) {
			tmp = tmp.substring(0, Length);
		}
		return tmp;
	}
	
	
	public static String priceFormat(int number) {
		DecimalFormat df = new DecimalFormat("###,###");
		String strNumber = df.format(number);
		
		return strNumber;
	}
	
	
}

