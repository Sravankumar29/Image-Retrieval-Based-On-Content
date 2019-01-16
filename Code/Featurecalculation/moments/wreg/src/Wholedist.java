package p2;
import java.awt.image.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import javax.imageio.*;
import java.sql.*;
import javax.sql.*;

class Wholedist
{
public static void main(String args[])throws Exception
{
Connection con=null;
ResultSet rs,rs1,rs2,rs3;
Statement st,st1;
BufferedImage img= null;
PreparedStatement pst=null;
String str,re,imgname,chos[];
int  i,j,catno=0,catno1=0,count=0;
//String perq="select category from performance where ID='"+imgname+"'";
double eqdist;
double sum1=0,sum2=0,sum3=0,sum4=0,sum5=0,sum6=0,sum7=0,sum8=0,sum9=0;
double meanr,meang,meanb,sdr,sdg,sdb,skewr,skewg,skewb,x1,x2,x3;
double sumx;
int pixels[];
double ch1[],ch2[],ch3[];
String queryimg;
double arr[],a[];

try {
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
con = DriverManager.getConnection("jdbc:odbc:xxx");
PrintStream ps=new PrintStream("distancewhole.txt");
PrintStream ps2=new PrintStream("categorywhole.txt");
//PrintStream ps1=new PrintStream("querywhole.txt");
st=con.createStatement();
st1=con.createStatement();
String del="delete * from distance";
st.executeUpdate(del);
 str="select * from colormoments";
re="select * from distance order by eudist";
 pst=con.prepareStatement("insert into distance values(?,?)");
 //File f=new File(args[0]);
 //String s=String.valueOf(f);
//queryimg=f.getName();
//System.out.println("query image name"+queryimg);
Wholemom obj=new Wholemom();
Choosefile choo1=new Choosefile();
	chos=choo1.choose();
	File f=new File(chos[1]);
arr=obj.getMoments(chos[1]);
//for(int p=0;p<9;p++)
  //  ps1.println(arr[p]);
System.out.println("query image name"+f.getName());
imgname=f.getName();
rs2=st.executeQuery("select category from performance where ID='"+imgname+"'");
while(rs2.next())
catno=rs2.getInt(1);
rs=st.executeQuery(str);
j=0;
while(rs.next())
{
String name=rs.getString(1);
for(int d=0;d<9;d++)
	{
	a[d]=rs.getDouble(d+2);
	}
	double sum=0;
	for(int d=0;d<9;d++)
	{
		sum+=Math.pow((a[d]-arr[d]),2);
	}

eqdist=Math.sqrt(sum);

pst.setString(1,name);
pst.setDouble(2,eqdist);
int k=pst.executeUpdate();
j++;
}
int l=0;
rs1=st1.executeQuery(re);
int n=0,p=0;int rank=0;
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
            System.out.println("entered:"+(n));
            if(n<100)
                p++;
		count++;
                rank+=n;
        ps2.println(n+"\t"+nm+"\t"+e);
        }
	}
ps.println(nm+"\t"+e);
n++;
}
//int x=n-1;
//int sm=x*(x+1)/2;
System.out.println("Performance in top 100:"+p+"%");
System.out.println("rank  performance:"+rank);
con.close();
System.out.println("end of file");

}
 catch (Exception e) 
{
 e.printStackTrace();
}
}
}