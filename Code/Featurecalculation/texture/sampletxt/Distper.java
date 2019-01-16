package texture;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.sql.*;
import javax.sql.*;
public class Textdist
{
public static void main(String args[])throws Exception
{
Connection con=null;
double a[];
a=new double[4];
double sum=0,eqdist;
ResultSet rs,rs1,rs2,rs3;
Statement st,st1;
BufferedImage img= null;
PreparedStatement pst=null;
String str,re,queryimg,s[],c1,imgname,cat;
int i,j,catno=0,catno1=0,count=0;
double arr[];
try {
s=new String[2];
String del="delete * from distance";
 Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
con = DriverManager.getConnection("jdbc:odbc:text");
PrintStream ps=new PrintStream("Alldist.txt");
PrintStream ps1=new PrintStream("Category.txt");
PrintStream ps2=new PrintStream("query gray.txt");
st=con.createStatement();
st1=con.createStatement();
st.executeUpdate(del);
 str="select * from Features";
re="select * from distance order by eudist";
//imgname="";

 pst=con.prepareStatement("insert into distance values(?,?)");
 Choosefile objch=new Choosefile();
 s=objch.choose();
Glcmsample obj=new Glcmsample();
File f=new File(s[1]);
BufferedImage src=ImageIO.read(f);
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
		ps2.print((int)Math.ceil(grayLevel/16)+" ");
		}
		ps2.println();
	}
System.out.println("query image name"+f.getName());
imgname=f.getName();
arr=obj.calGlcm("query gray.txt");
rs2=st.executeQuery("select category from performance where ID='"+imgname+"'");
while(rs2.next())
catno=rs2.getInt(1);
rs=st.executeQuery(str);
j=0;
while(rs.next())
{
    sum=0;
String name=rs.getString(1);
a[0]=rs.getDouble(2);
a[1]=rs.getDouble(3);
a[2]=rs.getDouble(4);
a[3]=rs.getDouble(5);
System.out.println("query image:"+name+"\nfeatures:"+"\n"+a[0]+"\t"+a[1]+"\t"+a[2]+"\t"+a[3]);
for(int q=0;q<4;q++)
	{
	sum=sum+((a[q]-arr[q])*(a[q]-arr[q]));
	}
	eqdist=Math.sqrt(sum);
pst.setString(1,name);
pst.setDouble(2,eqdist);
int k=pst.executeUpdate();
j++;
}
int l=0;
//float rank=0;
rs1=st1.executeQuery(re);
int n=0;
while(rs1.next() && n<100)
{
 System.out.println("entered:"+(l++));
String nm=rs1.getString(1);
double e=rs1.getDouble(2);
imgname=nm;
    	rs3=st.executeQuery("select category from performance where ID='"+imgname+"'");
        while(rs3.next())
	{
        catno1=rs3.getInt(1);
	if(catno==catno1)
        {
		count++;
                ps1.println(n+"\t"+nm+"\t"+e);
              //  rank=rank+n;
        }
	}
ps.println(nm+"\t"+e);
n++;
}
        //System.out.println("rank performance:"+(int)(rank/count)+"%");
System.out.println("Performance:"+count+"%");
con.close();
System.out.println("end of file");
}
 catch (Exception e) 
{
 e.printStackTrace();
}
}
}