package textreg;
import javax.imageio.ImageIO;  
import java.awt.image.BufferedImage;  
import java.io.*;  
import java.awt.*;
 public class Chunks
	 {  
 public BufferedImage[] divide(String t)throws Exception
		{
	 BufferedImage img[]=null;
int j;
try{
		File file=new File(t);
FileInputStream fis = new FileInputStream(file);  
        BufferedImage image = ImageIO.read(fis);
//		String name=t;       
        int rows = 3;   
        int cols = 3;  
        int chunks = rows * cols;  
		int chunkWidth = image.getWidth() / cols; // determines the chunk width and height  
        int chunkHeight = image.getHeight() / rows;  
        int count = 0;  
        img = new BufferedImage[chunks]; //Image array to hold image chunks  
        for (int x = 0; x < rows; x++) {  
            for (int y = 0; y < cols; y++) {  
                //Initialize the image array with image chunks  
                 img[count] = new BufferedImage(chunkWidth, chunkHeight, image.getType());  
  
                // draws the image chunk  
               Graphics2D gr = img[count++].createGraphics();  
                gr.drawImage(image, 0, 0, chunkWidth, chunkHeight, chunkWidth * y, chunkHeight * x, chunkWidth * y + chunkWidth, chunkHeight * x + chunkHeight, null);  
                gr.dispose();  
            }  
        }  
        

		}
		
		catch(Exception e)
			{
			e.printStackTrace();
			}
				return img;
    }  
} 