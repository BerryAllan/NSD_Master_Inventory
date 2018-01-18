package sample;

import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import io.ExcelWriter;
import item.Item;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Controller
{
	@FXML
	TextField assetNumber;
	@FXML
	TextField itemName;
	@FXML
	ComboBox category;
	@FXML
	ComboBox condition;
	@FXML
	ComboBox building;
	@FXML
	TextField roomNo;
	@FXML
	TextField department;
	@FXML
	TextField personOfContact;
	@FXML
	ComboBox brand;
	@FXML
	TextField model;
	@FXML
	TextField purchasePrice;
	@FXML
	DatePicker purchaseDate;
	@FXML
	DatePicker inventoryDate;
	@FXML
	DatePicker recycleDate;
	@FXML
	TextField serialNumber;
	@FXML
	TextField macAddress;
	@FXML
	TextField ipAddress;
	@FXML
	ComboBox operatingSystem;
	@FXML
	ComboBox supplier;
	@FXML
	Button updateSpreadsheet;
	@FXML
	Button generateQRCodes;
	@FXML
	Button generateDMCodes;
	@FXML
	Button generateText;
	@FXML
	TextField generatedText;
	@FXML
	Button inventoryItem;
	@FXML
	TextField itemPlainString;

	private EventHandler inventoryItemButtonEventHandler = event -> {
		try
		{
			PrintWriter writer;
			File file;
			if (Item.parser(itemPlainString.getText()).get(4).equals("HES"))
			{
				file = new File(Main.HES_CSV);
				if (!file.exists())
				{
					file.createNewFile();
				}
				FileWriter fw = new FileWriter(file, true);
				BufferedWriter bw = new BufferedWriter(fw);
				writer = new PrintWriter(bw);
			}
			else if (Item.parser(itemPlainString.getText()).get(4).equals("AMS"))
			{
				file = new File(Main.AMS_CSV);
				if (!file.exists())
				{
					file.createNewFile();
				}
				FileWriter fw = new FileWriter(file, true);
				BufferedWriter bw = new BufferedWriter(fw);
				writer = new PrintWriter(bw);
			}
			else if (Item.parser(itemPlainString.getText()).get(4).equals("NHS"))
			{
				file = new File(Main.NHS_CSV);
				if (!file.exists())
				{
					file.createNewFile();
				}
				FileWriter fw = new FileWriter(file, true);
				BufferedWriter bw = new BufferedWriter(fw);
				writer = new PrintWriter(bw);
			}
			else //defaults to the NHS TXT
			{
				file = new File(Main.NHS_CSV);
				if (!file.exists())
				{
					file.createNewFile();
				}
				FileWriter fw = new FileWriter(file, true);
				BufferedWriter bw = new BufferedWriter(fw);
				writer = new PrintWriter(bw);
			}

			if (!Main.searchUsingScanner(file, itemPlainString.getText()))
			{
				ArrayList<String> stringArrayList = Item.parser(itemPlainString.getText());
				if (stringArrayList.get(19).startsWith("f"))
				{
					writer.print("\n");
					for (int i = 0; i < stringArrayList.size(); i++)
					{
						if (i != stringArrayList.size() - 1)
							writer.print(stringArrayList.get(i) + ",");
						else
							writer.print("TRUE");
					}
				}
				else
					writer.print("\n" + itemPlainString.getText());
			}
			else
			{
				Main.markInventoried(file, itemPlainString.getText());
			}

			writer.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	};

	private EventHandler eventHandlerGenerateText = event -> {
		try
		{
			PrintWriter writer;
			File file;
			if (building.getEditor().getText().equals("HES"))
			{
				file = new File(Main.HES_CSV);
				if (!file.exists())
				{
					file.createNewFile();
				}
				FileWriter fw = new FileWriter(file, true);
				BufferedWriter bw = new BufferedWriter(fw);
				writer = new PrintWriter(bw);
			}
			else if (building.getEditor().getText().equals("AMS"))
			{
				file = new File(Main.AMS_CSV);
				if (!file.exists())
				{
					file.createNewFile();
				}
				FileWriter fw = new FileWriter(file, true);
				BufferedWriter bw = new BufferedWriter(fw);
				writer = new PrintWriter(bw);
			}
			else if (building.getEditor().getText().equals("NHS"))
			{
				file = new File(Main.NHS_CSV);
				if (!file.exists())
				{
					file.createNewFile();
				}
				FileWriter fw = new FileWriter(file, true);
				BufferedWriter bw = new BufferedWriter(fw);
				writer = new PrintWriter(bw);
			}
			else //defaults to the NHS TXT
			{
				file = new File(Main.NHS_CSV);
				if (!file.exists())
				{
					file.createNewFile();
				}
				FileWriter fw = new FileWriter(file, true);
				BufferedWriter bw = new BufferedWriter(fw);
				writer = new PrintWriter(bw);
			}

			int assetNumber = Integer.parseInt(this.assetNumber.getText().isEmpty() ? String.valueOf(Short.MAX_VALUE) :
			                                   this.assetNumber.getText());
			String itemName = this.itemName.getText().isEmpty() ? "" : this.itemName.getText();
			String category = this.category.getEditor().getText().isEmpty() ? "" : this.category.getEditor().getText();
			String condition =
					this.condition.getEditor().getText().isEmpty() ? "" : this.condition.getEditor().getText();
			String building = this.building.getEditor().getText().isEmpty() ? "" : this.building.getEditor().getText();
			int room = Integer.parseInt(
					this.roomNo.getText().isEmpty() ? String.valueOf(Short.MAX_VALUE) : this.roomNo.getText());
			String department = this.department.getText().isEmpty() ? "" : this.department.getText();
			String personOfContact = this.personOfContact.getText().isEmpty() ? " " : this.personOfContact.getText();
			String brand = this.brand.getEditor().getText().isEmpty() ? "" : this.brand.getEditor().getText();
			String model = this.model.getText().isEmpty() ? "" : this.model.getText();
			double purchasePrice =
					Double.parseDouble(this.purchasePrice.getText().isEmpty() ? "0.0" : this.purchasePrice.getText());
			String purchaseDate =
					this.purchaseDate.getEditor().getText().isEmpty() ? "" : this.purchaseDate.getEditor().getText();
			String inventoryDate =
					this.inventoryDate.getEditor().getText().isEmpty() ? "" : this.inventoryDate.getEditor().getText();
			String recycleDate =
					this.recycleDate.getEditor().getText().isEmpty() ? "" : this.recycleDate.getEditor().getText();
			String serialNumber = this.serialNumber.getText().isEmpty() ? "" : this.serialNumber.getText();
			String macAddress = this.macAddress.getText().isEmpty() ? "" : this.macAddress.getText();
			String ipAddress = this.ipAddress.getText().isEmpty() ? "" : this.ipAddress.getText();
			String operatingSystem = this.operatingSystem.getEditor().getText().isEmpty() ? "" :
			                         this.operatingSystem.getEditor().getText();
			String supplier = this.supplier.getEditor().getText().isEmpty() ? "" : this.supplier.getEditor().getText();

			generatedText.setText(
					assetNumber + "," + itemName + "," + category + "," + condition + "," + building + "," + room +
							"," + department + "," + personOfContact + "," + brand + "," + model + "," + purchasePrice +
							"," + purchaseDate + "," + inventoryDate + "," + recycleDate + "," + serialNumber + "," + macAddress +
							"," + ipAddress + "," + operatingSystem + "," + (
							supplier == null ? "" : supplier) + "," + "TRUE");

			if (!Main.searchUsingScanner(file, generatedText.getText()))
			{
				writer.print(
						"\n" + assetNumber + "," + itemName + "," + category + "," + condition + "," + building + "," + room +
								"," + department + "," + personOfContact + "," + brand + "," + model + "," + purchasePrice +
								"," + purchaseDate + "," + inventoryDate + "," + recycleDate + "," + serialNumber + "," + macAddress +
								"," + ipAddress + "," + operatingSystem + "," + (supplier == null ? "" :
								                                                 supplier) + "," + "TRUE");
			}
			else
			{
				System.out.println("got here");
				Main.markInventoried(file, itemPlainString.getText());
			}

			writer.close();

		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}; //not printing ':\

	private EventHandler eventHandlerGenerateQRCodes = event -> {
		Main.generateQRCodesFromInventory(true);
	};

	private EventHandler eventHandlerGenerateDMCodes = event -> {
		Main.generateQRCodesFromInventory(false);
	};

	private EventHandler eventHandlerWriteToSpreadsheet = event -> {
		try
		{
			ExcelWriter writer = new ExcelWriter();
			writer.clearExcelDocs(Main.EXCEL_FILE_PATH);

			Main.writeHESExcel();
			Main.writeAMSExcel();
			Main.writeNHSExcel();
		} catch (InvalidFormatException | IOException | FormatException | NotFoundException | ChecksumException e)
		{
			System.out.println();
			e.printStackTrace();
		}
	};

	// Add a public no-args constructor
	public Controller()
	{
	}

	@FXML
	private void initialize()
	{
		updateSpreadsheet.addEventHandler(ActionEvent.ANY, eventHandlerWriteToSpreadsheet);
		generateQRCodes.addEventHandler(ActionEvent.ANY, eventHandlerGenerateQRCodes);
		generateDMCodes.addEventHandler(ActionEvent.ANY, eventHandlerGenerateDMCodes);
		generateText.addEventHandler(ActionEvent.ANY, eventHandlerGenerateText);
		inventoryDate.focusedProperty().addListener(new ChangeListener<Boolean>()
		{
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue)
			{
				if (newValue)
				{
					inventoryDate.setValue(LocalDate.now());
				}
			}
		});
		inventoryItem.addEventHandler(ActionEvent.ANY, inventoryItemButtonEventHandler);
	}
}
