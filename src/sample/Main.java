package sample;

import com.google.zxing.*;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import comparators.SortByAssetNumber;
import comparators.SortByItemType;
import comparators.SortByRoomNumber;
import io.ExcelReader;
import io.ExcelWriter;
import io.QRCodeGenerator;
import item.Item;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.*;
import java.util.*;

/*
TO-DO:
- any changes made to excel get re-written to csv
- color coded to check if inventoried or not
- organize fxml so tab key goes in order
- "enter key" in itemPlainString text field sends to csv
- notification of success of writing
 */

public class Main extends Application
{
	public static String EXCEL_FILE_PATH = "C:/Users/titom/Desktop/tester.xlsx";

	public static String CODES_MAIN_DIRECTORY = "C:/Users/titom/Desktop/Inventories/";

	public static String HES_QRCODE_DIRECTORY_PATH = CODES_MAIN_DIRECTORY + "HESQR/";
	public static String HES_DMCODE_DIRECTORY_PATH = CODES_MAIN_DIRECTORY + "HESDM/";
	public static String HES_CSV = CODES_MAIN_DIRECTORY + "HES.csv";

	public static String AMS_QRCODE_DIRECTORY_PATH = CODES_MAIN_DIRECTORY + "AMSQR/";
	public static String AMS_DMCODE_DIRECTORY_PATH = CODES_MAIN_DIRECTORY + "AMSDM/";
	public static String AMS_CSV = CODES_MAIN_DIRECTORY + "AMS.csv";

	public static String NHS_QRCODE_DIRECTORY_PATH = CODES_MAIN_DIRECTORY + "NHSQR/";
	public static String NHS_DMCODE_DIRECTORY_PATH = CODES_MAIN_DIRECTORY + "NHSDM/";
	public static String NHS_CSV = CODES_MAIN_DIRECTORY + "NHS.csv";


	public static void main(String[] args) throws Exception
	{
		launch(args);
		ExcelReader reader = new ExcelReader();
		//reader.readExcelHESWriteToTXT(EXCEL_FILE_PATH);
	}

	public static void generateQRCodesFromInventory(boolean QR)
	{
		try
		{
			if (QR)
			{
				File HESDir = new File(HES_QRCODE_DIRECTORY_PATH);
				for (File file : Objects.requireNonNull(HESDir.listFiles()))
					if (!file.isDirectory())
						file.delete();

				File AMSDir = new File(AMS_QRCODE_DIRECTORY_PATH);
				for (File file : Objects.requireNonNull(AMSDir.listFiles()))
					if (!file.isDirectory())
						file.delete();

				File NHSDir = new File(NHS_QRCODE_DIRECTORY_PATH);
				for (File file : Objects.requireNonNull(NHSDir.listFiles()))
					if (!file.isDirectory())
						file.delete();

				QRCodeGenerator qrCodeGenerator = new QRCodeGenerator();
				String charset = "UTF-8"; // or "ISO-8859-1"
				Map hintMap = new HashMap();
				hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
				qrCodeGenerator.createQRCodes(charset, hintMap, 200, 200);
			}
			else
			{
				File HESDir = new File(HES_DMCODE_DIRECTORY_PATH);
				for (File file : Objects.requireNonNull(HESDir.listFiles()))
					if (!file.isDirectory())
						file.delete();

				File AMSDir = new File(AMS_DMCODE_DIRECTORY_PATH);
				for (File file : Objects.requireNonNull(AMSDir.listFiles()))
					if (!file.isDirectory())
						file.delete();

				File NHSDir = new File(NHS_DMCODE_DIRECTORY_PATH);
				for (File file : Objects.requireNonNull(NHSDir.listFiles()))
					if (!file.isDirectory())
						file.delete();

				QRCodeGenerator qrCodeGenerator = new QRCodeGenerator();
				String charset = "UTF-8"; // or "ISO-8859-1"
				Map hintMap = new HashMap();
				hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
				qrCodeGenerator.createDMCodes(charset, hintMap, 200, 200);
			}
		} catch (WriterException e)
		{
			System.out.println("Could not generate QR Code, WriterException :: " + e.getMessage());
		} catch (IOException e)
		{
			System.out.println("Could not generate QR Code, IOException :: " + e.getMessage());
		}
	}

	public static void writeHESExcel() throws InvalidFormatException, IOException, FormatException, ChecksumException, NotFoundException
	{
		ArrayList<Item> items = new ArrayList<>();

		final File file = new File(HES_CSV);

		//System.out.println(fileEntry.getAbsolutePath());
		for (String string : getStrings(file.getAbsolutePath()))
		{
			items.add(new Item(Item.parser(string)));
		}

		items.sort(new SortByRoomNumber());


		int index = 0;
		for (int i = 0; i < items.size(); i++)
		{
			if (i != 0)
			{
				if (items.get(i).getRoom() != items.get(i - 1).getRoom())
				{
					items.subList(index, i).sort(new SortByItemType());
					index = i;
				}
			}
		}

		int index2 = 0;
		for (int i = 0; i < items.size(); i++)
		{
			if (i != 0)
			{
				if (items.get(i).getAssetNumber() != items.get(i - 1).getAssetNumber())
				{
					items.subList(index2, i).sort(new SortByAssetNumber());
					index2 = i;
				}
			}
		}

		ExcelWriter excelWriter = new ExcelWriter();
		excelWriter.writeExcelHES(items, EXCEL_FILE_PATH);
	}

	public static void writeAMSExcel() throws InvalidFormatException, IOException, FormatException, ChecksumException, NotFoundException
	{
		ArrayList<Item> items = new ArrayList<>();

		final File file = new File(AMS_CSV);

		//System.out.println(fileEntry.getAbsolutePath());
		for (String string : getStrings(file.getAbsolutePath()))
		{
			items.add(new Item(Item.parser(string)));
		}

		items.sort(new SortByRoomNumber());

		int index = 0;
		for (int i = 0; i < items.size(); i++)
		{
			if (i != 0)
			{
				if (items.get(i).getRoom() != items.get(i - 1).getRoom())
				{
					items.subList(index, i).sort(new SortByItemType());
					index = i;
				}
			}
		}

		int index2 = 0;
		for (int i = 0; i < items.size(); i++)
		{
			if (i != 0)
			{
				if (items.get(i).getAssetNumber() != items.get(i - 1).getAssetNumber())
				{
					items.subList(index2, i).sort(new SortByAssetNumber());
					index2 = i;
				}
			}
		}

		ExcelWriter excelWriter = new ExcelWriter();
		excelWriter.writeExcelAMS(items, EXCEL_FILE_PATH);
	}

	public static void writeNHSExcel() throws InvalidFormatException, IOException, FormatException, ChecksumException, NotFoundException
	{
		ArrayList<Item> items = new ArrayList<>();

		final File file = new File(NHS_CSV);

		//System.out.println(fileEntry.getAbsolutePath());
		for (String string : getStrings(file.getAbsolutePath()))
		{
			items.add(new Item(Item.parser(string)));
		}

		items.sort(new SortByRoomNumber());

		int index = 0;
		for (int i = 0; i < items.size(); i++)
		{
			if (i != 0)
			{
				if (items.get(i).getRoom() != items.get(i - 1).getRoom())
				{
					items.subList(index, i).sort(new SortByItemType());
					index = i;
				}
			}
		}

		int index2 = 0;
		for (int i = 0; i < items.size(); i++)
		{
			if (i != 0)
			{
				if (items.get(i).getAssetNumber() != items.get(i - 1).getAssetNumber())
				{
					items.subList(index2, i).sort(new SortByAssetNumber());
					index2 = i;
				}
			}
		}

		ExcelWriter excelWriter = new ExcelWriter();
		excelWriter.writeExcelNHS(items, EXCEL_FILE_PATH);
	}

	public static ArrayList<String> getStrings(String path) throws FileNotFoundException
	{
		File file = new File(path);
		Scanner scanner = new Scanner(file);
		ArrayList<String> strings = new ArrayList<>();

		while (scanner.hasNextLine())
		{
			strings.add(scanner.nextLine());
		}

		return strings;
	}

	public static ArrayList<Item> getItems(String path) throws FileNotFoundException
	{
		File file = new File(path);
		Scanner scanner = new Scanner(file);
		ArrayList<Item> items = new ArrayList<>();

		while (scanner.hasNextLine())
		{
			items.add(new Item(Item.parser(scanner.nextLine())));
		}

		//for (Item item : items) System.out.println(item.getAssetNumber());

		return items;
	}

	public static boolean searchUsingScanner(File filePath, String searchQuery) throws FileNotFoundException
	{
		ArrayList<String> list = new ArrayList<>();
		Scanner scanner = new Scanner(filePath);
		while (scanner.hasNextLine())
		{
			list.add(scanner.nextLine());
		}

		String total = "";
		for (int i = 0; i < list.size(); i++)
		{
			total += list.get(i);
		}

		if (total.contains(searchQuery))
			return true;
		else
			return false;
	}

	public static void markInventoried(File fileName, String textToReplace)
	{
		try
		{
			BufferedReader file = new BufferedReader(new FileReader(fileName));
			String line;
			String input = "";

			while ((line = file.readLine()) != null)
			{
				//System.out.println(line);
				if (line.contains(textToReplace))
				{
					line = "";
					ArrayList<String> itemStrings = Item.parser(textToReplace);
					itemStrings.set(19, "TRUE");
					for (int j = 0; j < itemStrings.size(); j++)
					{
						if (j == 0)
							input += itemStrings.get(j);
						else
							input += "," + itemStrings.get(j);
					}
				}
				input += line + '\n';
			}
			removeEmptyLines(fileName);

			FileOutputStream File = new FileOutputStream(fileName);
			File.write(input.getBytes());
			file.close();
			File.close();
		} catch (Exception e)
		{
			System.out.println("Problem reading file.");
		}
	}

	public static void removeEmptyLines(File file)
	{
		try (BufferedReader inputFile = new BufferedReader(new FileReader(file));
		     PrintWriter outputFile = new PrintWriter(new FileWriter(file)))
		{
			String lineOfText;
			while ((lineOfText = inputFile.readLine()) != null)
			{
				lineOfText = lineOfText.trim();
				if (!lineOfText.isEmpty())
				{
					outputFile.println(lineOfText);
				}
			}

			inputFile.close();
			outputFile.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception
	{
		Parent root = FXMLLoader.load(getClass().getResource("NSD Master Inventory.fxml"));
		primaryStage.setTitle("NSD Master Inventory");
		primaryStage.setScene(new Scene(root, 1400, 800));
		primaryStage.show();
	}
}
