package io;

import item.Item;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import sample.Main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelReader
{
	int columns;

	public String readExcelHESWriteToTXT(String excelFilePath) throws IOException, InvalidFormatException
	{
		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
		Workbook workbook = WorkbookFactory.create(inputStream);
		Sheet sheet = workbook.getSheet("HES");
		ArrayList<ArrayList<String>> data = new ArrayList<>();

		Iterator<Row> rowIterator = sheet.iterator();
		while (rowIterator.hasNext())
		{
			Row row = rowIterator.next();
			//For each row, iterate through all the columns
			Iterator<Cell> cellIterator = row.cellIterator();
			ArrayList<String> arrayList = new ArrayList<>();

			while (cellIterator.hasNext())
			{
				Cell cell = cellIterator.next();
				//Check the cell type and format accordingly

				if (cell.getCellTypeEnum() == CellType.STRING)
				{
					arrayList.add(cell.getStringCellValue());
				}
				else if (cell.getCellTypeEnum() == CellType.NUMERIC)
				{
					arrayList.add(Double.toString(cell.getNumericCellValue()));
				}
				else
				{
					arrayList.add(" ");
				}
			}

			data.add(arrayList);


		}

		PrintWriter printWriter = new PrintWriter(Main.HES_CSV);
		System.out.println(data.size());
		for (int i = 0; i < data.size(); i++)
		{
			for (int j = 0; j < data.get(i).size(); j++)
			{
				printWriter.print(data.get(i).get(j) + ",");
			}
			printWriter.print("\n");
		}

		//Main.removeFirstLine(Main.HES_CSV);
		return null;
	}

	public void readExcelAMS(List<Item> items, String excelFilePath) throws IOException, InvalidFormatException
	{
		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
		Workbook workbook = WorkbookFactory.create(inputStream);
		Sheet sheet = workbook.getSheet("AMS");
	}

	public void readExcelNHS(List<Item> items, String excelFilePath) throws IOException, InvalidFormatException
	{
		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
		Workbook workbook = WorkbookFactory.create(inputStream);
		Sheet sheet = workbook.getSheet("NHS");
	}
}
