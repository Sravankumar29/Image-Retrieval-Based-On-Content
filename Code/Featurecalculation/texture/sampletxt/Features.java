package texture;
public class Features
{
public double[] getFeature(double cm[][])throws Exception
{
	double ene=0,con=0,var=0,mean=0,sum=0;
	double hom=0,ff[];
	ff=new double[5];
	int i=0,j=0;
	try{
	for(i=0;i<16;i++)
	{
		for(j=0;j<16;j++)
		{
		sum+=cm[i][j];
		}
	}
	mean=sum/256;
for(i=0;i<16;i++)
	{
	for(j=0;j<16;j++)
		{
ene+=Math.pow(cm[i][j],2);
//ent+=(cm[i][j])*Math.abs(Math.log10(cm[i][j])/Math.log10(2));
con+=((double)((i-j)*(i-j)))*(cm[i][j]);
hom+=(1/(1+Math.pow((double)(i-j),2)))*(cm[i][j]);
var+=Math.pow(((double)i-mean),2)*cm[i][j];
		}
	}
	ff[0]=ene;
	ff[1]=hom;
	ff[2]=con;
	ff[3]=var;
//	System.out.println("\nFeatures calculated :");
	//System.out.println("\n"+"Energy:"+ene+"\n"+"Homogenity:"+hom+"\n"+"Contrast:"+con+"\n"+"Variance:"+var+"\n");
	
}
catch(Exception e){}
return ff;

}
}