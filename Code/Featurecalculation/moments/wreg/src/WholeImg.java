package p2;
import java.awt.image.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import javax.imageio.*;
import java.sql.*;
import javax.sql.*;
class WholeImg
{
public static void main(String args[])throws Exception
{
Connection con=null;
PreparedStatement pst=null;
ResultSet rs;
String chos[];
Choosefile choo1=new Choosefile();
	chos=choo1.choose();
File folder=new File(chos[0]);
File files[];
double ar[]=new double[9];
files=folder.listFiles();
int j;
System.out.println("no.of images:"+files.length);
try {
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
con = DriverManager.getConnection("jdbc:odbc:xxx");
pst=con.prepareStatement("insert into colormoments values(?,?,?,?,?,?,?,?,?,?)");
for(j=0;j<files.length;j++)
{
String s=String.valueOf(files[j]);
Wholemom obj=new Wholemom();
ar=obj.getMoments(s);
File f1=new File(s);
String name=f1.getName();
pst.setString(1,name);
pst.setDouble(2,ar[0]);
pst.setDouble(3,ar[1]);
pst.setDouble(4,ar[2]);
pst.setDouble(5,ar[3]);
pst.setDouble(6,ar[4]);
pst.setDouble(7,ar[5]);
pst.setDouble(8,ar[6]);
pst.setDouble(9,ar[7]);
pst.setDouble(10,ar[8]);
int k = pst.executeUpdate();
System.out.println("image:"+j);
}

}
 catch (Exception e) 
{
 e.printStackTrace();
}
}
}