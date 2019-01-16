package p2;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import java.awt.*;
import javax.imageio.*;
public class X
{
public double[] getMoments(BufferedImage img)throws Exception
{
double a[];
a=new double[9];
try{
int pixels[],w, h,i,j,n;
double ch1[],ch2[],ch3[];
double sum1=0,sum2=0,sum3=0,sum4=0,sum5=0,sum6=0,sum7=0,sum8=0,sum9=0;
double x1,x2,x3;
double sumx;
//BufferedImage img= null;
//img = ImageIO.read(new File(path));
w = img.getWidth();
h = img.getHeight();
n=w*h;
pixels = new int[n];
ch1=new double[n];
ch2=new double[n];
ch3=new double[n];
img.getRGB(0, 0, w, h, pixels, 0, w);
for(i=0;i<n;i++)
{
ch1[i]=(pixels[i] >> 16) & 0xff;
ch2[i]=(pixels[i] >> 8) & 0xff;
ch3[i]=(pixels[i] & 0xff);
}
for(i=0;i<n;i++)
{
sum1+=ch1[i];
sum2+=ch2[i];
sum3+=ch3[i];
}
a[0]=(sum1/n);//meanr
a[1]=(sum2/n);//meang
a[2]=(sum3/n);//meanb
for(i=0;i<n;i++)
{
x1=(ch1[i]-a[0]);
sum4+=(x1*x1);
sum7+=(x1*x1*x1);
x2=(ch2[i]-a[1]);
sum5+=(x2*x2);
sum8+=(x2*x2*x2);
x3=(ch3[i]-a[2]);
sum6+=(x3*x3);
sum9+=(x3*x3*x3);
}
a[3]=Math.sqrt(sum4/n);//sdr
a[4]=Math.sqrt(sum5/n);//sdg
a[5]=Math.sqrt(sum6/n);//sdb
a[6]=Math.cbrt(sum7/n);//skewr
a[7]=Math.cbrt(sum8/n);//skewg
a[8]=Math.cbrt(sum9/n);//skewb

}
catch(Exception e)
{
e.printStackTrace();
}
return a;
}
}
