package comparators;

import item.Item;

import java.util.Comparator;

public class SortByAssetNumber implements Comparator<Item>
{
	@Override
	public int compare(Item o1, Item o2)
	{
		return o1.getAssetNumber() - o2.getAssetNumber();
	}
}
