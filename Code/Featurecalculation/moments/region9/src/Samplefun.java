package p2;
import javax.imageio.ImageIO;  
import java.awt.image.BufferedImage;  
import java.io.*;  
import java.awt.*;
import java.sql.*;
import javax.sql.*;
public class Samplefun
	{  
    public static void main(String[] args)throws Exception 
		{
int j;
Connection con=null;
PreparedStatement pst=null;
try{
	
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
con = DriverManager.getConnection("jdbc:odbc:xxx");
pst=con.prepareStatement("insert into region9 values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
	int i;
	String s[];
	s=new String[2];
	Choosefile ch1=new Choosefile();
	s=ch1.choose();
		File folder= new File(s[0]); 
		File files[];
		files=folder.listFiles();
		System.out.println("no.of images in the folder:"+files.length);
		double mom[],fa[];
		mom=new double[82];
		fa=new double[82];
	//	PrintStream ps=new PrintStream("moments.txt");
		 
	Moments ch=new Moments();
		for(j=0;j<files.length;j++)
	{
			String t=String.valueOf(files[j]);
		File file=new File(t);
		mom=ch.Calmoments(file);
		String name=file.getName();
	//ps.println(j+"\t"+name+":");
//for(int i=0;i<54;i++)
//ps.println(i+"\t"+mom[i]);
//Insertmoments obj1=new Insertmoments();
//obj1.Insertion(name,mom);
for(i=0;i<81;i++)
	fa[i]=mom[i];
pst.setString(1,name);
pst.setDouble(2,fa[0]);
pst.setDouble(3,fa[1]);
pst.setDouble(4,fa[2]);
pst.setDouble(5,fa[3]);
pst.setDouble(6,fa[4]);
pst.setDouble(7,fa[5]);
pst.setDouble(8,fa[6]);
pst.setDouble(9,fa[7]);
pst.setDouble(10,fa[8]);
pst.setDouble(11,fa[9]);
pst.setDouble(12,fa[10]);
pst.setDouble(13,fa[11]);
pst.setDouble(14,fa[12]);
pst.setDouble(15,fa[13]);
pst.setDouble(16,fa[14]);
pst.setDouble(17,fa[15]);
pst.setDouble(18,fa[16]);
pst.setDouble(19,fa[17]);
pst.setDouble(20,fa[18]);
pst.setDouble(21,fa[19]);
pst.setDouble(22,fa[20]);
pst.setDouble(23,fa[21]);
pst.setDouble(24,fa[22]);
pst.setDouble(25,fa[23]);
pst.setDouble(26,fa[24]);
pst.setDouble(27,fa[25]);
pst.setDouble(28,fa[26]);
pst.setDouble(29,fa[27]);
pst.setDouble(30,fa[28]);
pst.setDouble(31,fa[29]);
pst.setDouble(32,fa[30]);
pst.setDouble(33,fa[31]);
pst.setDouble(34,fa[32]);
pst.setDouble(35,fa[33]);
pst.setDouble(36,fa[34]);
pst.setDouble(37,fa[35]);
pst.setDouble(38,fa[36]);
pst.setDouble(39,fa[37]);
pst.setDouble(40,fa[38]);
pst.setDouble(41,fa[39]);
pst.setDouble(42,fa[40]);
pst.setDouble(43,fa[41]);
pst.setDouble(44,fa[42]);
pst.setDouble(45,fa[43]);
pst.setDouble(46,fa[44]);
pst.setDouble(47,fa[45]);
pst.setDouble(48,fa[46]);
pst.setDouble(49,fa[47]);
pst.setDouble(50,fa[48]);
pst.setDouble(51,fa[49]);
pst.setDouble(52,fa[50]);
pst.setDouble(53,fa[51]);
pst.setDouble(54,fa[52]);
pst.setDouble(55,fa[53]);
pst.setDouble(56,fa[54]);
pst.setDouble(57,fa[55]);
pst.setDouble(58,fa[56]);
pst.setDouble(59,fa[57]);
pst.setDouble(60,fa[58]);
pst.setDouble(61,fa[59]);
pst.setDouble(62,fa[60]);
pst.setDouble(63,fa[61]);
pst.setDouble(64,fa[62]);
pst.setDouble(65,fa[63]);
pst.setDouble(66,fa[64]);
pst.setDouble(67,fa[65]);
pst.setDouble(68,fa[66]);
pst.setDouble(69,fa[67]);
pst.setDouble(70,fa[68]);
pst.setDouble(71,fa[69]);
pst.setDouble(72,fa[70]);
pst.setDouble(73,fa[71]);
pst.setDouble(74,fa[72]);
pst.setDouble(75,fa[73]);
pst.setDouble(76,fa[74]);
pst.setDouble(77,fa[75]);
pst.setDouble(78,fa[76]);
pst.setDouble(79,fa[77]);
pst.setDouble(80,fa[78]);
pst.setDouble(81,fa[79]);
pst.setDouble(82,fa[80]);
int k=pst.executeUpdate();
System.out.println(j);
	}
					}
		catch(Exception e)
			{
			}
    }  
} 