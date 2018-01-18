package io;

import item.Item;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A very simple program that writes some data to an Excel file
 * using the Apache POI library.
 *
 * @author www.codejava.net
 */
public class ExcelWriter
{
	public void writeExcelHES(List<Item> items, String excelFilePath) throws IOException, InvalidFormatException
	{
		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
		Workbook workbook = WorkbookFactory.create(inputStream);
		Sheet sheet = workbook.getSheet("HES");
		int rowCount = 1;

		for (int i = 0; i < items.size(); i++)
		{
			if (i != 0)
			{
				if (items.get(i).getRoom() != items.get(i - 1).getRoom())
				{
					sheet.createRow(++rowCount);
				}
			}

			Row row = sheet.createRow(++rowCount);
			writeItem(items.get(i), row);
		}

		int counter = 2;
		for (int i = 2; i <= sheet.getLastRowNum(); i++)
		{
			Row row = sheet.getRow(i);
			Cell cell = row.getCell(0);

			if (cell != null)
			{

				CellStyle style = workbook.createCellStyle();
				style.setFillForegroundColor(
						items.get(i - counter).isInventoried() ? IndexedColors.LIGHT_GREEN.getIndex() :
						IndexedColors.RED.getIndex());

				style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				//System.out.println(items.get(i - counter).isInventoried());
				cell.setCellStyle(style);
			}
			else
			{
				counter++;
			}
		}

		FileOutputStream outputStream = new FileOutputStream(excelFilePath);
		workbook.write(outputStream);
		outputStream.close();
		workbook.close();
	}

	public void writeExcelAMS(List<Item> items, String excelFilePath) throws IOException, InvalidFormatException
	{
		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
		Workbook workbook = WorkbookFactory.create(inputStream);
		Sheet sheet = workbook.getSheet("AMS");
		int rowCount = 1;

		for (int i = 0; i < items.size(); i++)
		{
			if (i != 0)
			{
				if (items.get(i).getRoom() != items.get(i - 1).getRoom())
				{
					sheet.createRow(++rowCount);
				}
			}

			Row row = sheet.createRow(++rowCount);
			writeItem(items.get(i), row);
		}

		int counter = 2;
		for (int i = 2; i <= sheet.getLastRowNum(); i++)
		{
			Row row = sheet.getRow(i);
			Cell cell = row.getCell(0);

			if (cell != null)
			{

				CellStyle style = workbook.createCellStyle();
				style.setFillForegroundColor(
						items.get(i - counter).isInventoried() ? IndexedColors.LIGHT_GREEN.getIndex() :
						IndexedColors.RED.getIndex());

				style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				//System.out.println(items.get(i - counter).isInventoried());
				cell.setCellStyle(style);
			}
			else
			{
				counter++;
			}
		}

		FileOutputStream outputStream = new FileOutputStream(excelFilePath);
		workbook.write(outputStream);
		outputStream.close();
		workbook.close();
	}

	public void writeExcelNHS(List<Item> items, String excelFilePath) throws IOException, InvalidFormatException
	{
		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
		Workbook workbook = WorkbookFactory.create(inputStream);
		Sheet sheet = workbook.getSheet("NHS");
		int rowCount = 1;

		for (int i = 0; i < items.size(); i++)
		{
			if (i != 0)
			{
				if (items.get(i).getRoom() != items.get(i - 1).getRoom())
				{
					sheet.createRow(++rowCount);
				}
			}

			Row row = sheet.createRow(++rowCount);
			writeItem(items.get(i), row);
		}

		int counter = 2;
		for (int i = 2; i <= sheet.getLastRowNum(); i++)
		{
			Row row = sheet.getRow(i);
			Cell cell = row.getCell(0);

			if (cell != null)
			{

				CellStyle style = workbook.createCellStyle();
				style.setFillForegroundColor(
						items.get(i - counter).isInventoried() ? IndexedColors.LIGHT_GREEN.getIndex() :
						IndexedColors.RED.getIndex());

				style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				//System.out.println(items.get(i - counter).isInventoried());
				cell.setCellStyle(style);
			}
			else
			{
				counter++;
			}
		}

		FileOutputStream outputStream = new FileOutputStream(excelFilePath);
		workbook.write(outputStream);
		outputStream.close();
		workbook.close();
	}

	private void writeItem(Item item, Row row)
	{
		Cell cell = row.createCell(0);
		cell.setCellValue(item.getAssetNumber() == Short.MAX_VALUE ? "" : String.valueOf(item.getAssetNumber()));

		cell = row.createCell(1);
		cell.setCellValue(item.getItemName());

		cell = row.createCell(2);
		cell.setCellValue(item.getCategory().toString());

		cell = row.createCell(3);
		cell.setCellValue(item.getCondition());

		cell = row.createCell(4);
		cell.setCellValue(item.getBuilding());

		cell = row.createCell(5);
		cell.setCellValue(item.getRoom() == Short.MAX_VALUE ? "" : String.valueOf(item.getRoom()));

		cell = row.createCell(6);
		cell.setCellValue(item.getDepartment());

		cell = row.createCell(7);
		cell.setCellValue(item.getPersonOfContact());

		cell = row.createCell(8);
		cell.setCellValue(item.getBrand());

		cell = row.createCell(9);
		cell.setCellValue(item.getModel());

		cell = row.createCell(10);
		cell.setCellValue(item.getPurchasePrice() == 0 ? "" : String.valueOf(item.getPurchasePrice()));

		cell = row.createCell(11);
		cell.setCellValue(item.getPurchaseDate());

		cell = row.createCell(12);
		cell.setCellValue(item.getInventoryDate());

		cell = row.createCell(13);
		cell.setCellValue(item.getRecycleDate());

		cell = row.createCell(14);
		cell.setCellValue(item.getSerialNumber());

		cell = row.createCell(15);
		cell.setCellValue(item.getMacAddress());

		cell = row.createCell(18);
		cell.setCellValue(item.getIpAddress());

		cell = row.createCell(17);
		cell.setCellValue(item.getOperatingSystem());

		cell = row.createCell(18);
		cell.setCellValue(item.getSupplier());
	}

	public void clearExcelDocs(String excelFilePath) throws IOException, InvalidFormatException
	{
		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
		Workbook workbook = WorkbookFactory.create(inputStream);
		ArrayList<Sheet> sheets = new ArrayList<>();
		sheets.add(workbook.getSheet("HES"));
		sheets.add(workbook.getSheet("AMS"));
		sheets.add(workbook.getSheet("NHS"));

		for (Sheet sheet : sheets)
		{
			for (int i = 2; i <= sheet.getLastRowNum(); i++)
			{
				if (sheet.getRow(i) != null)
					sheet.removeRow(sheet.getRow(i));
			}
		}

		FileOutputStream outputStream = new FileOutputStream(excelFilePath);
		workbook.write(outputStream);
		outputStream.close();
		workbook.close();
	}
}