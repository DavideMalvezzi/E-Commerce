package ecommerce.utils;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author Davide
 * @file
 * Classe contenente metodi statici per il caricamento e il resize di immagini
 */
public class ImageLoader {

	/**
	 * @brief Carica un'immagine mantenendone l'aspect ratio
	 * @param path Path dell'immagine da caricare
	 * @param size Dimensione del contenitore dell'immagine
	 * @return Immagine caricata o null se l'immagine non esiste
	 */
	public static Image loadImage(String path, Dimension size){
		
		if(path != null && !path.isEmpty()){
			File f = new File(path);	
			if(f.exists()){
				try {
					BufferedImage img = ImageIO.read(new File(path));
					Dimension scaled = getScaledDimension(new Dimension(img.getWidth(), img.getHeight()), size);
					return img.getScaledInstance((int)scaled.getWidth(), (int)scaled.getHeight(), Image.SCALE_SMOOTH);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	/**
	 * @brief Calcola la dimensione che mantenga l'aspect ratio dell'immagine e sia minore uguale delle dimensioni del contenitore
	 * @param imgSize Dimensione originale dell'immagine
	 * @param boundary Dimensione del contenitore dell'immagine
	 * @return Dimensione finale
	 */
	private static Dimension getScaledDimension(Dimension imgSize, Dimension boundary) {
	    int original_width = imgSize.width;
	    int original_height = imgSize.height;
	    int bound_width = boundary.width;
	    int bound_height = boundary.height;
	    int new_width = original_width;
	    int new_height = original_height;

	    // first check if we need to scale width
	    if (original_width > bound_width) {
	        //scale width to fit
	        new_width = bound_width;
	        //scale height to maintain aspect ratio
	        new_height = (new_width * original_height) / original_width;
	    }

	    // then check if we need to scale even with the new height
	    if (new_height > bound_height) {
	        //scale height to fit instead
	        new_height = bound_height;
	        //scale width to maintain aspect ratio
	        new_width = (new_height * original_width) / original_height;
	    }

	    return new Dimension(new_width, new_height);
	}
	
}
