public class MyMath {
    private double PI = 3.14;
    private int n=4;
    public double cos(double degree) {
        int artieksi = 0,i,ikiN,j,temp2,degree2;
        double cosine,toplam=0,radSayi,temp,fact;
        degree2= (int) (degree%360);
        radSayi=(PI*(double) degree2)/180;
        temp=radSayi;
        temp2=n;
        for (j = 0; j<=n; n--){
            ikiN=2*n;
            if (n%2==1)
                artieksi=-1;
            if (n%2==0)
                artieksi=1;
            for (i = 1; i <=(ikiN-1); ++i)
            {
                radSayi*=temp;
            }
            if (ikiN==0)
            {
                fact=1;
                radSayi=1;
            }
            fact=1;
            while (ikiN>1)
            {
                fact*=ikiN*(ikiN-1);
                ikiN-=2;
            }
            cosine =((double) artieksi* radSayi)/ fact;
            toplam+=cosine;
            radSayi=temp;
        }
        return toplam;
    }
    public double sin(double degree)
    {
        int artieksi = 0,i,ikiN,j,temp2;
        double sine,toplam=0.0,radSayi,temp,fact,degree2;
        degree2= (degree%360);
        radSayi=(PI*(double) degree2)/180;
        temp=radSayi;
        temp2=n;
        for (j = 0; j<=n; n--) {
            ikiN=2*n;
            if (n%2==1)
                artieksi=-1;
            if (n%2==0)
                artieksi=1;
            for (i = 1; i <=(ikiN); ++i) {
                radSayi*=temp;
            }
            if (ikiN+1==1) {
                fact=1;
                radSayi=temp;
            }
            fact=1;
            while (ikiN+1>1) {
                fact*=(ikiN+1)*(ikiN);
                ikiN-=2;
            }
            sine =((double) artieksi* radSayi)/ fact;
            toplam+=sine;
            radSayi=temp;
        }
        return toplam;
    }
    public double abs(double number){
        if(number < 0)
            return -number;
        return number;
    }

}
