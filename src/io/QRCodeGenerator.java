package io;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import sample.Main;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Map;

public class QRCodeGenerator
{
	public void createQRCodes(String charset, Map hintMap, int height, int width)
			throws WriterException, IOException
	{
		for (int i = 0; i < Main.getItems(Main.HES_CSV).size(); i++)
		{
			BitMatrix matrix = new MultiFormatWriter().encode(
					new String(Main.getStrings(Main.HES_CSV).get(i).getBytes(charset), charset),
					BarcodeFormat.QR_CODE,
					width, height,
					hintMap);

			Path path = FileSystems.getDefault().getPath(
					Main.HES_QRCODE_DIRECTORY_PATH + Main.getItems(Main.HES_CSV)
							.get(i)
							.getAssetNumber()
							+ "_" + Main.getItems(Main.HES_CSV).get(i).getItemName() + "_"
							+ Main.getItems(Main.HES_CSV).get(i).getSerialNumber() + "_" +
							Main.getItems(Main.HES_CSV).get(i).getModel() + ".png");
			MatrixToImageWriter.writeToPath(matrix, "PNG", path);
		}

		for (int i = 0; i < Main.getItems(Main.AMS_CSV).size(); i++)
		{
			BitMatrix matrix = new MultiFormatWriter().encode(
					new String(Main.getStrings(Main.AMS_CSV).get(i).getBytes(charset), charset),
					BarcodeFormat.QR_CODE,
					width, height,
					hintMap);

			Path path = FileSystems.getDefault().getPath(
					Main.AMS_QRCODE_DIRECTORY_PATH + Main.getItems(Main.AMS_CSV)
							.get(i)
							.getAssetNumber()
							+ "_" + Main.getItems(Main.AMS_CSV).get(i).getItemName() + "_"
							+ Main.getItems(Main.AMS_CSV).get(i).getSerialNumber() + "_" +
							Main.getItems(Main.AMS_CSV).get(i).getModel() + ".png");
			MatrixToImageWriter.writeToPath(matrix, "PNG", path);
		}

		for (int i = 0; i < Main.getItems(Main.NHS_CSV).size(); i++)
		{
			BitMatrix matrix = new MultiFormatWriter().encode(
					new String(Main.getStrings(Main.NHS_CSV).get(i).getBytes(charset), charset),
					BarcodeFormat.QR_CODE,
					width, height,
					hintMap);

			Path path = FileSystems.getDefault().getPath(
					Main.NHS_QRCODE_DIRECTORY_PATH + Main.getItems(Main.NHS_CSV)
							.get(i)
							.getAssetNumber()
							+ "_" + Main.getItems(Main.NHS_CSV).get(i).getItemName() + "_"
							+ Main.getItems(Main.NHS_CSV).get(i).getSerialNumber() + "_" +
							Main.getItems(Main.NHS_CSV).get(i).getModel() + ".png");
			MatrixToImageWriter.writeToPath(matrix, "PNG", path);
		}
	}

	public void createDMCodes(String charset, Map hintMap, int height, int width)
			throws WriterException, IOException
	{
		for (int i = 0; i < Main.getItems(Main.HES_CSV).size(); i++)
		{
			BitMatrix matrix = new MultiFormatWriter().encode(
					new String(Main.getStrings(Main.HES_CSV).get(i).getBytes(charset), charset),
					BarcodeFormat.DATA_MATRIX,
					width, height,
					hintMap);

			Path path = FileSystems.getDefault().getPath(
					Main.HES_DMCODE_DIRECTORY_PATH + Main.getItems(Main.HES_CSV)
							.get(i)
							.getAssetNumber()
							+ "_" + Main.getItems(Main.HES_CSV).get(i).getItemName() + "_"
							+ Main.getItems(Main.HES_CSV).get(i).getSerialNumber() + "_" +
							Main.getItems(Main.HES_CSV).get(i).getModel() + ".png");
			MatrixToImageWriter.writeToPath(matrix, "PNG", path);
		}

		for (int i = 0; i < Main.getItems(Main.AMS_CSV).size(); i++)
		{
			BitMatrix matrix = new MultiFormatWriter().encode(
					new String(Main.getStrings(Main.AMS_CSV).get(i).getBytes(charset), charset),
					BarcodeFormat.DATA_MATRIX,
					width, height,
					hintMap);

			Path path = FileSystems.getDefault().getPath(
					Main.AMS_DMCODE_DIRECTORY_PATH + Main.getItems(Main.AMS_CSV)
							.get(i)
							.getAssetNumber()
							+ "_" + Main.getItems(Main.AMS_CSV).get(i).getItemName() + "_"
							+ Main.getItems(Main.AMS_CSV).get(i).getSerialNumber() + "_" +
							Main.getItems(Main.AMS_CSV).get(i).getModel() + ".png");
			MatrixToImageWriter.writeToPath(matrix, "PNG", path);
		}

		for (int i = 0; i < Main.getItems(Main.NHS_CSV).size(); i++)
		{
			BitMatrix matrix = new MultiFormatWriter().encode(
					new String(Main.getStrings(Main.NHS_CSV).get(i).getBytes(charset), charset),
					BarcodeFormat.DATA_MATRIX,
					width, height,
					hintMap);

			Path path = FileSystems.getDefault().getPath(
					Main.NHS_DMCODE_DIRECTORY_PATH + Main.getItems(Main.NHS_CSV)
							.get(i)
							.getAssetNumber()
							+ "_" + Main.getItems(Main.NHS_CSV).get(i).getItemName() + "_"
							+ Main.getItems(Main.NHS_CSV).get(i).getSerialNumber() + "_" +
							Main.getItems(Main.NHS_CSV).get(i).getModel() + ".png");
			MatrixToImageWriter.writeToPath(matrix, "PNG", path);
		}
	}

	//get text
	//turn into list of items
	//turn into list of strings
	//generate qr code
}