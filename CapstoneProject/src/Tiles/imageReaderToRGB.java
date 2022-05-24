package Tiles;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class imageReaderToRGB {
//	int[][] arr;
	
	
	public imageReaderToRGB(){//int[][] arr) {
	//	this.arr = arr;
	}
	
	public int[][] ImageToArr(BufferedImage image) {
		int[][]arr = new int[100][100];
		for(int i = 0; i < arr.length; i++)
		    for(int j = 0; j < arr[i].length; j++) {
		    	int color = image.getRGB(i, j);

		    	// Components will be in the range of 0..255:
		    	int blue = color & 0xff;
	//	    	int green = (color & 0xff00) >> 8;
		    	int red = (color & 0xff0000) >> 16;

//		    	System.out.println("blue: " + blue );//+ " green: " + green + " red: " + red);
		    	arr[i][j] = blue/10 + red/10;
//	    	arr[i][j] = image.getRGB(i, j)/10;	
//	    	System.out.print(image.getRGB(i, j) + " ");
		    }
		
		
		return arr;
	}
//	
//	public static void createImage(int[][] regen) {
//		BufferedImage img = new BufferedImage(regen.length, regen[0].length, BufferedImage.TYPE_BYTE_GRAY);  
//			for(int x = 0; x < regen.length; x++){
//			    for(int y = 0; y<regen[x].length; y++){
//			    	img.setRGB(x, y, regen[x][y]);
//			    }
//			}
//			File imageFile = new File("Assets\\pictu.png");
//			try {
//				ImageIO.write(img, "png", imageFile);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//	}
	
//	public void writeImage(int Name) {
//	    String path = "Assets/" + Name + ".png";
//	    BufferedImage image = new BufferedImage(arr.length, arr[0].length, BufferedImage.TYPE_INT_RGB);
//	    for (int x = 0; x < arr.length; x++) {
//	        for (int y = 0; y < arr[0].length; y++) {
//	            image.setRGB(x, y, arr[x][y] * 10);
//	        }
//	    }
//	    
//	    File ImageFile = new File(path);
//	    try {
//	        ImageIO.write(image, "png", ImageFile);
//	    } catch (IOException e) {
//	        e.printStackTrace();
//	    }
//	}

}
