package textreg;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.awt.*;
import java.sql.*;
import javax.sql.*;
import java.awt.color.ColorSpace;
public class Textdist
{
public static void main(String args[])throws Exception
{
Connection con=null;
double a[];
a=new double[24];
double sum=0,eqdist;
ResultSet rs,rs1,rs2,rs3;
Statement st,st1;
BufferedImage img= null;
PreparedStatement pst=null;
String str,re,queryimg,s[],c1,imgname,cat;
int i,j,catno=0,catno1=0,count=0;
double arr[],dr[];
try {
	int r=0;
	dr=new double[25];
s=new String[2];
String del="delete * from distance";
 Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
con = DriverManager.getConnection("jdbc:odbc:text");
PrintStream ps=new PrintStream("Alldist.txt");
PrintStream ps1=new PrintStream("Category.txt");
PrintStream ps2=null;
st=con.createStatement();
st1=con.createStatement();
st.executeUpdate(del);
BufferedImage imgs[]=null;
 str="select * from reg6";
re="select * from distance order by eudist";
//imgname="";
Chunks chu=new Chunks();

 pst=con.prepareStatement("insert into distance values(?,?)");
 Choosefile objch=new Choosefile();
 s=objch.choose();
Glcmsample obj=new Glcmsample();
File f=new File(s[1]);
imgs=chu.divide(s[1]);
for(int k1=0;k1<imgs.length;k1++)
	{
	
BufferedImage src=imgs[k1];
ColorConvertOp grayOp=new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY),null);
BufferedImage dest=grayOp.filter(src,null);
int n=dest.getWidth();
int m=dest.getHeight();
ps2=new PrintStream("query gray.txt");
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

arr=obj.calGlcm("query gray.txt");
for( i=0;i<4;i++)
		{
dr[r]=arr[i];
r++;
		}
	}

System.out.println("query image name"+f.getName());
imgname=f.getName();
rs2=st.executeQuery("select category from performance where ID='"+imgname+"'");
while(rs2.next())
catno=rs2.getInt(1);
rs=st.executeQuery(str);
j=0;
while(rs.next())
{
    sum=0;
String name=rs.getString(1);
for(int d=0;d<23;d++)
	{
	a[d]=rs.getDouble(d+2);
	}

//System.out.println("query image:"+name+"\nfeatures:"+"\n"+a[0]+"\t"+a[1]+"\t"+a[2]+"\t"+a[3]);
for(int q=0;q<24;q++)
	{
	sum=sum+((a[q]-dr[q])*(a[q]-dr[q]));
	}
	eqdist=Math.sqrt(sum);
pst.setString(1,name);
pst.setDouble(2,eqdist);
int k=pst.executeUpdate();
j++;
}
int l=0;
int rank=0;
rs1=st1.executeQuery(re);
int n1=0,p=0;
while(rs1.next() && count<=100)
{
 
String nm=rs1.getString(1);
double e=rs1.getDouble(2);
imgname=nm;
    	rs3=st.executeQuery("select category from performance where ID='"+imgname+"'");
        while(rs3.next())
	{
        catno1=rs3.getInt(1);
	if(catno==catno1)
        {
            System.out.println("entered:"+(n1));
        if(n1<100){
            p++;
        }
		count++;
                ps1.println(n1+"\t"+nm+"\t"+e);
               rank=rank+n1;
        }
	}
ps.println(nm+"\t"+e);
n1++;
}
        System.out.println("Performance:"+p+"%");
System.out.println("rank performance:"+rank);

con.close();
System.out.println("end of file");
}
 catch (Exception e) 
{
 e.printStackTrace();
}
}
}