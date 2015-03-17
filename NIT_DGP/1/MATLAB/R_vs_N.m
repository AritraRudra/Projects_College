arrRate=60;
handRate=10;
R=7;%57;
mu=40;

rate=arrRate+handRate;
sum=0.0;

X1=1:R ;
N=X1;

for i=1:R
    for j=1:i
        a=power(2,(j-1));
        sum=sum+a;
    end
    N(i)=6*rate*sum;
end
        
figure;
plot(X1,N,'-ok');