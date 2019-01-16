package p2;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.awt.*;
import java.sql.*;
import javax.sql.*;
/*import java.awt.color.ColorSpace;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;*/
public class Regiontexture6
{
public int getRegionTexture(String s[],String sgm)throws Exception
{
Connection con=null;
double a[];
a=new double[24];
double sum=0,eqdist;
ResultSet rs,rs1,rs2,rs3,qr;
Statement st,st1,st2;
BufferedImage img= null;
PreparedStatement pst=null;
String str,re,queryimg,c1,imgname,cat;
int i,j,catno=0,catno1=0,count=0;
double arr[],dr[];

//JPanel  panel = new JPanel (); 
//JFrame  frame=  new JFrame ("Image divided into 6Regions & texture features are extracted ");
try {
	int r=0;
	dr=new double[25];
//s=new String[2];
String del="delete * from distance";
 Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
con = DriverManager.getConnection("jdbc:odbc:xxx");
PrintStream ps=new PrintStream("textureoutput/6Regionstexture.txt");
PrintStream ps1=new PrintStream("textureoutput/Cat6Regionstexture.txt");
PrintStream ps2=null;
st=con.createStatement();
st1=con.createStatement();
st2=con.createStatement();
st.executeUpdate(del);
BufferedImage imgs[]=null;
 str="select * from reg6";
re="select * from distance order by eudist";
//imgname="";

 pst=con.prepareStatement("insert into distance values(?,?)");

File f=new File(s[1]);
//System.out.println("query image name"+f.getName());
//panel.add (new JLabel (new ImageIcon (s[1])));
imgname=f.getName();
qr=st2.executeQuery("select * from reg6 where ID='"+imgname+"'");

while(qr.next())
{
    for(int rr=0;rr<24;rr++)
	{
    dr[rr]=qr.getDouble(rr+2);
	System.out.println(rr+"."+dr[rr]);
    }
}
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
int n1=0,p=0;int u=0;
while(rs1.next() && count<=100)
{
 
String nm=rs1.getString(1);
double e=rs1.getDouble(2);
imgname=nm;
    	rs3=st.executeQuery("select category from performance where ID='"+imgname+"'");
        while(rs3.next()&&n1<100)
	{
        catno1=rs3.getInt(1);
	if(catno==catno1)
        {
            //System.out.println("entered:"+(n1));
       /* if(n1<100){
            p++;
        }*/
	//	 panel.add (new JLabel (new ImageIcon (s[0]+"/"+imgname)));
	
		count++;
                ps1.println(n1+"\t"+nm+"\t"+e);
               //rank=rank+n1;
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
n1++;
}
     //   System.out.println("Performance:"+count+"%");
//System.out.println("rank performance:"+rank);
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