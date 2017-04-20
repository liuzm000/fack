package cn.eshore.mismp.util;

import java.io.File;
import java.io.IOException;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

public class TwoDimensionalUtil {

	public static int generateTwoDemimension(String content, String filePath,
			String fileName, int width, int height) {
		int d = 0;
		BitMatrix byteMatrix;
		try {
			byteMatrix = new MultiFormatWriter().encode(content,
					BarcodeFormat.QR_CODE, width, height);
			File is = new File(filePath);
			if (!is.isDirectory()) {
				is.mkdirs();
				System.out.println("创建" + is + "目录");
			}
			File file = new File(filePath + fileName);
			MatrixToImageWriter.writeToFile(byteMatrix, "png", file);
			return 1;
		} catch (IOException e) {
			e.printStackTrace();
			return d;
		} catch (WriterException e1) {
			e1.printStackTrace();
			return d;
		}
	}
}
