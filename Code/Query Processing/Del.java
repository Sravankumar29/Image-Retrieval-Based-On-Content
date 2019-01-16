package p2;
import java.io.*;
class Del
{
public void deletedir(String s)
{
File f=new File(s);
File f1[];
f1=f.listFiles();
for(int i=0;i<f1.length;i++)
	{
f1[i].delete();
	}

f.delete();
}
}