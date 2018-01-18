package item;

import java.util.ArrayList;

public class Item //needs toString override
{
	private boolean inventoried = true;

	private int assetNumber;
	private String itemName;
	private String category;
	private String condition;
	private String building;
	private int room;
	private String department;
	private String personOfContact;
	private String brand;
	private String model;
	private double purchasePrice;
	private String purchaseDate;
	private String inventoryDate;
	private String recycleDate;
	private String serialNumber;
	private String macAddress;
	private String ipAddress;
	private String operatingSystem;
	private String supplier;

	public Item()
	{
	}

	public Item(int assetNumber, String itemName, String category, String condition, String building, int room,
	            String department, String personOfContact, String brand,
	            String model, double purchasePrice, String purchaseDate, String inventoryDate, String recycleDate,
	            String serialNumber, String macAddress, String ipAddress, String operatingSystem, String supplier)
	{
		this.assetNumber = assetNumber;
		this.itemName = itemName;
		this.category = category;
		this.condition = condition;
		this.building = building;
		this.room = room;
		this.department = department;
		this.personOfContact = personOfContact;
		this.brand = brand;
		this.model = model;
		this.purchasePrice = purchasePrice;
		this.purchaseDate = purchaseDate;
		this.inventoryDate = inventoryDate;
		this.recycleDate = recycleDate;
		this.serialNumber = serialNumber;
		this.macAddress = macAddress;
		this.ipAddress = ipAddress;
		this.operatingSystem = operatingSystem;
		this.supplier = supplier;

	}

	public Item(ArrayList<String> fields)
	{
		this.assetNumber = fields.get(0).equals("") ? Short.MAX_VALUE : Integer.parseInt(fields.get(0));
		this.itemName = fields.get(1);
		this.category = fields.get(2);
		this.condition = fields.get(3);
		this.building = fields.get(4);
		this.room = fields.get(5).equals("") ? Short.MAX_VALUE : Integer.parseInt(fields.get(5));
		this.department = fields.get(6);
		this.personOfContact = fields.get(7);
		this.brand = fields.get(8);
		this.model = fields.get(9);
		this.purchasePrice = fields.get(10).equals("") ? 0 : Double.parseDouble(fields.get(10));
		this.purchaseDate = fields.get(11);
		this.inventoryDate = fields.get(12);
		this.recycleDate = fields.get(13);
		this.serialNumber = fields.get(14);
		this.macAddress = fields.get(15);
		this.ipAddress = fields.get(16);
		this.operatingSystem = fields.get(17);
		this.supplier = fields.get(18);
		this.inventoried = fields.get(19).toLowerCase().startsWith("t");
	}

	public static ArrayList<String> parser(String qrinput)
	{
		ArrayList<String> fields = new ArrayList<>();
		StringBuilder field = new StringBuilder();

		for (int i = 0; i < qrinput.length(); i++)
		{
			String character = Character.toString(qrinput.charAt(i));

			if (character.equals(","))
			{
				fields.add(field.toString());
				field = new StringBuilder();
			}
			else if (i == qrinput.length() - 1)
			{
				field.append(character);
				fields.add(field.toString());
				field = new StringBuilder();
			}
			else
			{
				field.append(character);
			}
		}
		return fields;
	}

	public int getAssetNumber()
	{
		return assetNumber;
	}

	public String getItemName()
	{
		return itemName;
	}

	public ItemTypes getCategory()
	{
		if (category.equalsIgnoreCase("phone"))
		{
			return ItemTypes.PHONE;
		}
		else if (category.equalsIgnoreCase("printer"))
		{
			return ItemTypes.PRINTER;
		}
		else if (category.equalsIgnoreCase("projector"))
		{
			return ItemTypes.PROJECTOR;
		}
		else if (category.equalsIgnoreCase("smartboard"))
		{
			return ItemTypes.SMARTBOARD;
		}
		else if (category.equalsIgnoreCase("ipad"))
		{
			return ItemTypes.IPAD;
		}
		else if (category.equalsIgnoreCase("chromebook"))
		{
			return ItemTypes.CHROMEBOOK;
		}
		else if (category.equalsIgnoreCase("laptop"))
		{
			return ItemTypes.LAPTOP;
		}
		else if (category.equalsIgnoreCase("desktop"))
		{
			return ItemTypes.DESKTOP;
		}
		else
		{
			return ItemTypes.OTHER;
		}
	}

	@Override
	public String toString()
	{
		int assetNumber = Integer.parseInt(Integer.toString(this.assetNumber).isEmpty() ? "0" :
		                                   String.valueOf(this.assetNumber));
		String itemName = this.itemName.isEmpty() ? " " : this.itemName;
		String category = this.category.isEmpty() ? " " : this.category;
		String condition =
				this.condition.isEmpty() ? " " : this.condition;
		String building = this.building.isEmpty() ? " " : this.building;
		int room = Integer.parseInt(String.valueOf(this.room).isEmpty() ? "0" : String.valueOf(this.room));
		String department = this.department.isEmpty() ? " " : this.department;
		String personOfContact = this.personOfContact.isEmpty() ? " " : this.personOfContact;
		String brand = this.brand.isEmpty() ? " " : this.brand;
		String model = this.model.isEmpty() ? " " : this.model;
		double purchasePrice =
				Double.parseDouble(String.valueOf(this.purchasePrice).isEmpty() ? "0.0" :
				                   String.valueOf(this.purchasePrice));
		String purchaseDate =
				this.purchaseDate.isEmpty() ? " " : this.purchaseDate;
		String inventoryDate =
				this.inventoryDate.isEmpty() ? " " : this.inventoryDate;
		String recycleDate =
				this.recycleDate.isEmpty() ? " " : this.recycleDate;
		String serialNumber = this.serialNumber.isEmpty() ? " " : this.serialNumber;
		String macAddress = this.macAddress.isEmpty() ? " " : this.macAddress;
		String ipAddress = this.ipAddress.isEmpty() ? " " : this.ipAddress;
		String operatingSystem = this.operatingSystem.isEmpty() ? " " :
		                         this.operatingSystem;
		String supplier = this.supplier.isEmpty() ? " " : this.supplier;

		return ("\n" + assetNumber + "," + itemName + "," + category + "," + condition + "," + building + "," + room +
				"," + department + "," + personOfContact + "," + brand + "," + model + "," + purchasePrice +
				"," + purchaseDate + "," + inventoryDate + "," + recycleDate + "," + serialNumber + "," + macAddress +
				"," + ipAddress + "," + operatingSystem + "," + supplier);
	}

	public String getCondition()
	{
		return condition;
	}

	public String getBuilding()
	{
		return building;
	}

	public int getRoom()
	{
		return room;
	}

	public String getDepartment()
	{
		return department;
	}

	public String getPersonOfContact()
	{
		return personOfContact;
	}

	public String getBrand()
	{
		return brand;
	}

	public String getModel()
	{
		return model;
	}

	public double getPurchasePrice()
	{
		return purchasePrice;
	}

	public String getPurchaseDate()
	{
		return purchaseDate;
	}

	public String getInventoryDate()
	{
		return inventoryDate;
	}

	public String getRecycleDate()
	{
		return recycleDate;
	}

	public String getSerialNumber()
	{
		return serialNumber;
	}

	public String getMacAddress()
	{
		return macAddress;
	}

	public String getIpAddress()
	{
		return ipAddress;
	}

	public String getOperatingSystem()
	{
		return operatingSystem;
	}

	public String getSupplier()
	{
		return supplier;
	}

	public boolean isInventoried()
	{
		return inventoried;
	}

	public void setInventoried(boolean inventoried)
	{
		this.inventoried = inventoried;
	}

	// getters and setters
}
