package p2;
import java.io.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
public class MainClass
{
public static void main(String args[])throws Exception
{
	JPanel  p = new JPanel (); 
JFrame  fra=  new JFrame ("Query Image ");
	String sgm=null;
String s[]=new String[2];
try{
System.out.println("Select  a query image:");
Choosefile ch=new Choosefile();
s=ch.choose();
//System.out.println("Query image Selected is:"+" "+s[1]);
p.add (new JLabel (new ImageIcon (s[1])));
int gcp=0,rcp1=0,rcp2=0,gtp=0,rtp1=0,rtp2=0,gc=0,r1=0,r2=0;
GlobalMoments gm=new GlobalMoments();
sgm="D:/C  Drive/output/colorglbl/";
gcp=gm.getglbl(s,sgm);

RegionMoments6 rm6=new RegionMoments6();
rcp1=rm6.getRegion(s,"D:/C  Drive/output/colorreg6/");
RegionMoments9 rm9=new RegionMoments9();
rcp2=rm9.getRegion9(s,"D:/C  Drive/output/colorreg9/");
Globaltexture glblt=new Globaltexture();
gtp=glblt.getglobaltexture(s,"D:/C  Drive/output/textglbl/");
Regiontexture6 reg6=new Regiontexture6();
rtp1=reg6.getRegionTexture(s,"D:/C  Drive/output/textreg6/");
Reg9text reg9=new Reg9text();
rtp2=reg9.getRegion9Texture(s,"D:/C  Drive/output/textreg9/");
Global g=new Global();
gc=g.getGlobal(s,"D:/C  Drive/output/glbl/");
Region6 combireg6=new Region6();
r1=combireg6.getRegioncomb(s,"D:/C  Drive/output/reg6/");
Region9 combireg9=new Region9();
r2=combireg9.getRegioncomb9(s,"D:/C  Drive/output/reg9/");
PrintStream ps=new PrintStream("Overall Performance.txt");
ps.println("Color Moments:");
ps.println("  Global"+" "+(float)gcp/100);
ps.println("   6Regions"+ " "+(float)rcp1/100);
ps.println("   9Regions"+ " "+(float)rcp2/100);
ps.println("Texture:");
ps.println("  Global"+" "+(float)gtp/100);
ps.println("   6Regions"+ " "+(float)rtp1/100);
ps.println("   9Regions"+ " "+(float)rtp2/100);
ps.println("Combining the features:");
ps.println("  Global"+" "+(float)gc/100);
ps.println("   6Regions"+ " "+(float)r1/100);
ps.println("   9Regions"+ " "+(float)r2/100);
ImageList img=new ImageList();
img.OutputDisplay();
fra.getContentPane().add (p);
fra.pack();
fra.setVisible(true);
fra.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
}
catch(Exception e)
	{
	e.printStackTrace();
	}
	/*Del dl=new Del();
	dl.deletedir(sgm);*/
	System.out.println("Image Retrieval is done");

}
}



