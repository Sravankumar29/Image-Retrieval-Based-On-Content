package texture;
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
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
con = DriverManager.getConnection("jdbc:odbc:text");
pst=con.prepareStatement("insert into Features values(?,?,?,?,?)");
	int i;
	String s[];
	s=new String[2];
	Choosefile ch1=new Choosefile();
	s=ch1.choose();
		File folder= new File(s[0]); 
		File files[];
		files=folder.listFiles();
		System.out.println("no.of images in the folder:"+files.length);
	for(j=0;j<files.length;j++)
	{
			String t=String.valueOf(files[j]);
		File file=new File(t);
		String name=file.getName();
	BufferedImage src=ImageIO.read(file);
String si="graylevels.txt";
PrintStream ps=new PrintStream(si);
ColorConvertOp grayOp=new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY),null);
BufferedImage dest=grayOp.filter(src,null);
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
	
	Glcmsample gl=new Glcmsample();

xx=	gl.calGlcm(si);
pst.setString(1,name);
pst.setDouble(2,xx[0]);
pst.setDouble(3,xx[1]);
pst.setDouble(4,xx[2]);
pst.setDouble(5,xx[3]);

int k=pst.executeUpdate();
//	System.out.println();
//ImageIO.write(dest,"jpg",new File("gray1.jpg"));
//System.out.println("GLCM written onto file");
	System.out.println("features calculated:"+j);
	}
}
catch(Exception e)
{
}
con.close();
System.out.println("features calculation completed");
}
}