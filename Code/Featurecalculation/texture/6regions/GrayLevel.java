package textreg;
import java.io.*;
import java.awt.*;
import java.sql.*;
import javax.sql.*;
import java.awt.image.*;//classes:BufferedImage,ColorConvertOp
import java.awt.color.ColorSpace;
import javax.imageio.ImageIO;
public class GrayLevel
{
public static void main(String args[])throws Exception
{double xx[];
int j=0;
xx=new double[4];
	Connection con=null;
	PreparedStatement pst=null;
try
{
	Chunks chu=new Chunks();
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
con = DriverManager.getConnection("jdbc:odbc:text");
pst=con.prepareStatement("insert into reg6 values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
	int i;
	String s[];
	s=new String[2];
	double k[];
	k=new double[25];
	Choosefile ch1=new Choosefile();
	Glcmsample gl=new Glcmsample();
	s=ch1.choose();
		File folder= new File(s[0]); 
		File files[];
		
		files=folder.listFiles();
		System.out.println("no.of images in the folder:"+files.length+"\n");
		
	for(j=0;j<files.length;j++)
	{//System.out.println("files enterd:"+j);
			String t=String.valueOf(files[j]);
		File file=new File(t);
		String name=file.getName();
	
String si="graylevels.txt";
PrintStream ps=null;
BufferedImage imgs[]=null;
imgs=chu.divide(t);
int r=0;
for(int k1=0;k1<imgs.length;k1++)
			{
ColorConvertOp grayOp=new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY),null);
BufferedImage src=imgs[k1];
BufferedImage dest=grayOp.filter(src,null);
 ps=new PrintStream(si);
int n=dest.getWidth();
int m=dest.getHeight();
for(int x=0;x<n;x++)
	{
	for(int y=0;y<m;y++)
		{
DataBuffer data=dest.getRaster().getDataBuffer();
		int grayLevel=data.getElem(y*dest.getWidth()+x);
	
		ps.print((int)Math.ceil(grayLevel/16)+" ");
		}
		ps.println();
	}
	
	xx=gl.calGlcm(si);
for(int g=0;g<4;g++)
				{
	k[r]=xx[g];
	r++;
				}
			}
pst.setString(1,name);
pst.setDouble(2,k[0]);
pst.setDouble(3,k[1]);
pst.setDouble(4,k[2]);
pst.setDouble(5,k[3]);
pst.setDouble(6,k[4]);
pst.setDouble(7,k[5]);
pst.setDouble(8,k[6]);
pst.setDouble(9,k[7]);
pst.setDouble(10,k[8]);
pst.setDouble(11,k[9]);
pst.setDouble(12,k[10]);
pst.setDouble(13,k[11]);
pst.setDouble(14,k[12]);
pst.setDouble(15,k[13]);
pst.setDouble(16,k[14]);
pst.setDouble(17,k[15]);
pst.setDouble(18,k[16]);
pst.setDouble(19,k[17]);
pst.setDouble(20,k[18]);
pst.setDouble(21,k[19]);
pst.setDouble(22,k[20]);
pst.setDouble(23,k[21]);
pst.setDouble(24,k[22]);
pst.setDouble(25,k[23]);
int k1=pst.executeUpdate();
//	System.out.println();
//ImageIO.write(dest,"jpg",new File("gray1.jpg"));
//System.out.println("GLCM written onto file");
	System.out.println(j);
	}
}
catch(Exception e)
{
	e.printStackTrace();
}
System.out.println("Feature extraction completed");


}
}