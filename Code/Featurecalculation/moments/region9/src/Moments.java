package p2;
import javax.imageio.ImageIO;  
import java.awt.image.BufferedImage;  
import java.io.*;  
import java.awt.*;
 public class Moments 
	 {  
 public double[] Calmoments(File f)throws Exception
		{
int j;
double arr[],fa[];
 arr=new double[10];
        fa=new double[82];
try{
		String t=String.valueOf(f);
		File file=new File(t);
		FileInputStream fis = new FileInputStream(file);  
        BufferedImage image = ImageIO.read(fis);
		String name=file.getName();
       
        int rows = 3;   
        int cols = 3;  
        int chunks = rows * cols;  
		int chunkWidth = image.getWidth() / cols; // determines the chunk width and height  
        int chunkHeight = image.getHeight() / rows;  
        int count = 0;  
        BufferedImage imgs[] = new BufferedImage[chunks]; //Image array to hold image chunks  
        for (int x = 0; x < rows; x++) {  
            for (int y = 0; y < cols; y++) {  
                //Initialize the image array with image chunks  
                 imgs[count] = new BufferedImage(chunkWidth, chunkHeight, image.getType());  
  
                // draws the image chunk  
               Graphics2D gr = imgs[count++].createGraphics();  
                gr.drawImage(image, 0, 0, chunkWidth, chunkHeight, chunkWidth * y, chunkHeight * x, chunkWidth * y + chunkWidth, chunkHeight * x + chunkHeight, null);  
                gr.dispose();  
            }  
        }  
        
		X obj=new X();
		String s;
		//System.out.println("no.of regions produced:"+imgs.length);
		int x=0;
		for(int i=0;i<imgs.length;i++)
			{
  arr=obj.getMoments(imgs[i]);
  for(int k=0;k<9;k++)
				{
				fa[x]=arr[k];
				x++;
				}
}

		}
		
		catch(Exception e)
			{
			}
				return fa;
    }  
} 