package ecommerce.utils;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {

	public static Image loadImage(String path, Dimension size){
		BufferedImage img = null ;
		
		if(path != null && !path.isEmpty()){
			File f = new File(path);	
			if(f.exists()){
				try {
					img = ImageIO.read(new File(path));
					return img.getScaledInstance((int)size.getWidth(), (int)size.getHeight(), Image.SCALE_SMOOTH);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
}
