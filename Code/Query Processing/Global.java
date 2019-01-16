package p2;
import java.awt.image.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import javax.imageio.*;
import java.sql.*;
import javax.sql.*;
class Global
{
public int getGlobal(String chos[],String sgm)throws Exception
{
Connection con=null;
ResultSet rs,rs1,rs2,rs3,qr,qrt;
Statement st,st1,st2,stt;
BufferedImage img= null;
PreparedStatement pst=null;
String str,strtxt,re,imgname;
int  i,j,catno=0,catno1=0,count=0,j1,i1;
double eqdist=0,sum1=0,eqdist1=0,eqdist2;
double arr[],a[],arrt[],arrt1[];
a=new double[10];
arrt=new double[5];
arrt1=new double[5];
        arr=new double[10];
	try {
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
con = DriverManager.getConnection("jdbc:odbc:xxx");
PrintStream ps=new PrintStream("combioutput/Global.txt");
PrintStream ps2=new PrintStream("combioutput/categlbal.txt");
st=con.createStatement();
stt=con.createStatement();
st1=con.createStatement();
st2=con.createStatement();
String del="delete * from distance";
st.executeUpdate(del);

 str="select * from colormoments";
re="select * from distance order by eudist";
 pst=con.prepareStatement("insert into distance values(?,?)");
// Choosefile choo1=new Choosefile();
	//chos=choo1.choose();
	File f=new File(chos[1]);
//arr=obj.getMoments(chos[1]);
//for(int p=0;p<9;p++)
  //  ps1.println(arr[p]);
        imgname=f.getName();
		//panel.add (new JLabel (new ImageIcon (chos[1])));

qr=st2.executeQuery("select * from colormoments where ID='"+imgname+"'");
qrt=stt.executeQuery("select * from Features where ID='"+imgname+"'");
//System.out.println("query image name"+f.getName());
//System.out.println("query moments:");
while(qr.next())
	{
	for(int rr=0;rr<9;rr++)
		{
		arr[rr]=qr.getDouble(rr+2);
		}
	}
while(qrt.next())
		{
	for(int rr=0;rr<4;rr++)
			{
arrt[rr]=qrt.getDouble(rr+2);
			}

	}
	

imgname=f.getName();
rs2=st.executeQuery("select category from performance where ID='"+imgname+"'");
while(rs2.next())
catno=rs2.getInt(1);
//Distance calculation
rs=st.executeQuery(str);

int ss=0;
j=0;
while(rs.next())
{
	qrt=null;
	sum1=0;eqdist1=0;eqdist=0;eqdist2=0;
	String name=rs.getString(1);
qrt=stt.executeQuery("select * from Features where ID='"+name+"'");
while(qrt.next())
	{
	for(int rr=0;rr<4;rr++)
		{
arrt1[rr]=qrt.getDouble(rr+2);
		}
	}
	for(int rr=0;rr<4;rr++)
	{
		sum1+=Math.pow((arrt[rr]-arrt1[rr]),2);
	}//System.out.println("texture distance calculated"+ss);
	eqdist1=Math.sqrt(sum1);
for(int d=0;d<9;d++)
	{
	a[d]=rs.getDouble(d+2);
	}
	double sum=0;
	for(int d=0;d<9;d++)
	{
		sum+=Math.pow((a[d]-arr[d]),2);
	}
	//System.out.println("color moments distance calculated"+ss);

eqdist=Math.sqrt(sum);
//eqdist2=eqdist+eqdist1;
eqdist2=(0.8*eqdist)+(0.2*eqdist1);
//System.out.println("features combined"+(ss++));

pst.setString(1,name);
pst.setDouble(2,eqdist2);
int k=pst.executeUpdate();
j++;
}
int l=0;
rs1=st1.executeQuery(re);
int n=0,p=0;int rank=0;int u=0;
while(rs1.next() &&n<100)
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
	  
		count++;
              //  rank+=n;
        ps2.println(n+"\t"+nm+"\t"+e);
        }
	}
	if(u<10)
			{
			String su=u+".jpg";
			BufferedImage bb=ImageIO.read(new File(chos[0]+"/"+imgname));
			ImageIO.write(bb,"jpg",new File(sgm+"/"+su));
			}
			u++;
ps.println(nm+"\t"+e);
n++;
}
//System.out.println("Performance in top 100:"+count+"%");
//System.out.println("rank  performance:"+rank);


}
 catch (Exception e) 
{
 e.printStackTrace();
}
con.close();
return count;
//System.out.println("end of file");
}
}