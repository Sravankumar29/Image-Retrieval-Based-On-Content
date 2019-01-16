package p2;
import java.awt.image.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import javax.imageio.*;
import java.sql.*;
import javax.sql.*;
/*import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;*/
        

class GlobalMoments
{
public  int getglbl(String chos[],String sgm)throws Exception
{
Connection con=null;
ResultSet rs,rs1,rs2,rs3,qr;
Statement st,st1,st2;
BufferedImage img= null;
PreparedStatement pst=null;
String str,re,imgname;
int  i,j,catno=0,catno1=0,count=0;
//String perq="select category from performance where ID='"+imgname+"'";
double eqdist;
double arr[],a[];
a=new double[10];
        arr=new double[10];
        try {
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
con = DriverManager.getConnection("jdbc:odbc:xxx");
PrintStream ps=new PrintStream("momoutput/GlobalMoments.txt");
PrintStream ps2=new PrintStream("momoutput/Samecategoryglbalmom.txt");
st=con.createStatement();
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
//System.out.println("query image name"+f.getName());
//System.out.println("query moments:");
while(qr.next())
	{
	for(int rr=0;rr<9;rr++)
		{arr[rr]=qr.getDouble(rr+2);
		}


	}
	/*for(int rr=0;rr<9;rr++)
		{
		System.out.println(arr[rr]);
		}*/
	

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
		
           // System.out.println("entered:"+(n));
//            String xxx=String.valueOf(count);
//      panel.add (new JLabel (xxx,new ImageIcon (chos[0]+"/"+imgname),SwingConstants.LEFT));		
	 
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


}
 catch (Exception e) 
{
 e.printStackTrace();
}

con.close();
return count;
}
}