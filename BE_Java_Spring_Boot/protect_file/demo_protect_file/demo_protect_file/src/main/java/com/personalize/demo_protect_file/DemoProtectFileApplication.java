package com.personalize.demo_protect_file;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.aspose.words.DocSaveOptions;
import com.aspose.words.Document;
import com.aspose.cells.EncryptionType;
import com.aspose.cells.Workbook;
import com.aspose.cells.FileFormatType;

@SpringBootApplication
public class DemoProtectFileApplication {

	public static void main(String[] args) {
//		SpringApplication.run(DemoProtectFileApplication.class, args);
		try {
			// Tạo một Workbook mới
			Workbook workbook = new Workbook();

			// Thêm một Worksheet vào Workbook
			workbook.getWorksheets().add("Sheet11");

			// Đặt mật khẩu cho Workbook
			workbook.setPassword("your_password");

			// Lưu Workbook với mật khẩu
			workbook.save("D:\\Personalized\\IE-Eng\\Test1.xlsx", FileFormatType.XLSX);

			System.out.println("Excel file has been created successfully.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
