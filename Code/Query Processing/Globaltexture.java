package p2;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.sql.*;
import javax.sql.*;
public class Globaltexture
{
public int getglobaltexture(String s[],String sgm)throws Exception
{
Connection con=null;
double a[];
a=new double[4];
double sum=0,eqdist;
ResultSet rs,rs1,rs2,rs3,qr=null;
Statement st,st1,st2;
BufferedImage img= null;
PreparedStatement pst=null;
String str,re,queryimg,c1,imgname,cat;
int i,j,catno=0,catno1=0,count=0;
double arr[];
arr=new double[4];
try {
String del="delete * from distance";
 Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
con = DriverManager.getConnection("jdbc:odbc:xxx");
PrintStream ps=new PrintStream("textureoutput/Globaltexture.txt");
PrintStream ps1=new PrintStream("textureoutput/Categoryglbaltexture.txt");
st=con.createStatement();
st1=con.createStatement();
st2=con.createStatement();
st.executeUpdate(del);
 str="select * from Features";
re="select * from distance order by eudist";
//imgname="";

 pst=con.prepareStatement("insert into distance values(?,?)");
// p2.Choosefile objch=new p2.Choosefile();
// s=objch.choose();
//Globalglcm obj=new Globalglcm();
File f=new File(s[1]);
String qname=f.getName();
//panel.add (new JLabel (new ImageIcon (s[1])));
qr=st2.executeQuery("select * from Features where ID='"+qname+"'");

while(qr.next())
{for(int rr=0;rr<4;rr++)
	{
    arr[rr]=qr.getDouble(rr+2);
	//System.out.println(rr+"."+arr[rr]);
    }
}

rs2=st.executeQuery("select category from performance where ID='"+qname+"'");
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
//System.out.println("query image:"+name+"\nfeatures:"+"\n"+a[0]+"\t"+a[1]+"\t"+a[2]+"\t"+a[3]);
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
int n=0;int u=0;
while(rs1.next() && n<100)
{
 //System.out.println("entered:"+(l++));
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
	//	 panel.add (new JLabel (new ImageIcon (s[0]+"/"+imgname)));
                ps1.println(n+"\t"+nm+"\t"+e);
              //  rank=rank+n;
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
        //System.out.println("rank performance:"+(int)(rank/count)+"%");
//System.out.println("Performance:"+count+"%");
/*frame.getContentPane().add (panel);
 frame.pack();
frame.setVisible(true);
frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);*/

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