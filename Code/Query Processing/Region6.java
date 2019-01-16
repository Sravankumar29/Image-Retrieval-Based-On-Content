package p2;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.sql.*;
import javax.sql.*;
/*import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;*/
public class Region6
{
public int getRegioncomb(String s[],String sgm)throws Exception
{
Connection con=null;
double a[];
a=new double[55];
double sum=0,eqdist,eqdist1=0,sum1,arrt[],arrt1[],eqdist2;
ResultSet rs,rs1,rs2,rs3,qr,qrt;
Statement st,st1,st2,stt;
BufferedImage img= null;
PreparedStatement pst=null;
String str,re,queryimg,c1,imgname,cat;
int i,j,catno=0,catno1=0,no[],count=0,j1;
no=new int[1000];
double arr[];
arr=new double[55];
arrt=new double[25];
arrt1=new double[25];
 //JPanel  panel = new JPanel (); 
   //JFrame  frame=  new JFrame ("Image divided into 6Regions ");
try {
//s=new String[2];
String del="delete * from distance";

    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
con = DriverManager.getConnection("jdbc:odbc:xxx");
PrintStream ps=new PrintStream("combioutput/6Regions.txt");
PrintStream ps2=new PrintStream("combioutput/samecat6reg.txt");
//PrintStream ps1=new PrintStream("querimg moments.txt");
st=con.createStatement();
stt=con.createStatement();
st1=con.createStatement();
st2=con.createStatement();
st.executeUpdate(del);
 str="select * from region";
re="select * from distance order by eudist";
//imgname="";

 pst=con.prepareStatement("insert into distance values(?,?)");
 //Choosefile objch=new Choosefile();
// s=objch.choose();
File f=new File(s[1]);
//System.out.println("query image name"+f.getName());
imgname=f.getName();
qr=st2.executeQuery("select * from region where ID='"+imgname+"'");
qrt=stt.executeQuery("select * from reg6 where ID='"+imgname+"'");
//panel.add (new JLabel (new ImageIcon (s[1])));
while(qr.next())
{for(int rr=0;rr<54;rr++)
	{
    arr[rr]=qr.getDouble(rr+2);
	//System.out.println(rr+"."+arr[rr]);
  
	}
}
while(qrt.next())
		{
	for(int rr=0;rr<24;rr++)
			{
arrt[rr]=qrt.getDouble(rr+2);
			}

	}

rs2=st.executeQuery("select category from performance where ID='"+imgname+"'");
while(rs2.next())
catno=rs2.getInt(1);
rs=st.executeQuery(str);
j=0;
int ss=0;
while(rs.next())
{sum1=0;eqdist1=0;eqdist=0;eqdist2=0;
    sum=0;qrt=null;
String name=rs.getString(1);
qrt=stt.executeQuery("select * from reg6 where ID='"+name+"'");
while(qrt.next())
	{
	for(int rr=0;rr<24;rr++)
		{
arrt1[rr]=qrt.getDouble(rr+2);
		}
	}
	for(int rr=0;rr<24;rr++)
	{
		sum1+=Math.pow((arrt[rr]-arrt1[rr]),2);
	}//System.out.println("texture distance calculated"+ss);
	eqdist1=Math.sqrt(sum1);
for(int d=0;d<54;d++)
	{
	a[d]=rs.getDouble(d+2);
	}

for(int q=0;q<54;q++)
	{
	sum+=Math.pow((a[q]-arr[q]),2);
	}
	
	//System.out.println("color moments distance calculated"+ss);
	eqdist=Math.sqrt(sum);
	eqdist2=(0.8*eqdist)+(0.2*eqdist1);
//	System.out.println("features combined"+(ss++));
//eqdist2=eqdist+eqdist1;
pst.setString(1,name);
pst.setDouble(2,eqdist2);
int k=pst.executeUpdate();
j++;
}
int l=0;int rank=0;
rs1=st1.executeQuery(re);
int n=0,p=0;int u=0;
while(rs1.next()&&n<100)
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
  //            panel.add (new JLabel (new ImageIcon (s[0]+"/"+imgname)));		
	  
         
          
		count++;
               // rank+=n;
        ps2.println(n+"\t"+nm+"\t"+e);
        }
	}
	     if(u<10)
			{
			String su=u+".jpg";
			BufferedImage bb=ImageIO.read(new File(s[0]+"/"+imgname));
			ImageIO.write(bb,"jpg",new File(sgm+"/"+su));
			}
			u++;
ps.println(nm+"\t"+e);
n++;
}
//int x=n-1;
//int sm=x*(x+1)/2;
//System.out.println("Performance:"+count+"%");
//System.out.println("rank  performance:"+rank);
 //frame.getContentPane().add (panel);
 //frame.pack();
//frame.setVisible(true);
//frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);


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