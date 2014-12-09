package adventureGame;

public class Items {

	private String itemName; 
	private String description;
	
	
	public Items(String name,String desc)
	{
		itemName = name;
		description = desc;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
