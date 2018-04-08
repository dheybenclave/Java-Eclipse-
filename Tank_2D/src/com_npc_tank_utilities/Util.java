package com_npc_tank_utilities;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class Util {
	private static HashMap<String,Image> cache = new HashMap<String,Image>(); 

	public static Image LoadImage(String path)
	{
		Image image = null;
		if(cache.containsKey(path))
		{
			return cache.get(path);
		}
		try
		{
			image = ImageIO.read(new File(path));
			if(!cache.containsKey(path))
				cache.put(path, image);
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
		return image;
	
	}

}