package controller.server;

import java.io.Serializable;

import model.ItemProduct;

public class ItemNameValidation  {
	
	
	public String validateItemName(ItemProduct item)
	{
		
		String result = "Valid Name. ", name = item.getName();
		
		for(int i=0; i<name.length();i++)
		{
		    
			char ch = name.charAt(i);
		    
			// To check if the character is not alphabet or space
			if (!(Character.isLetter(ch) || ch == ' ') )
			{
				
				result="Invalid Name. ";
				
		    }
		
		}
		return result;	
	

	}



}