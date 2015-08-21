format long;

arrRate=150;
handRate=10;
rate=arrRate+handRate;
mu=100;
S=6;

sum=0.0;
X1=1:S;
P_blk_1=X1;

for i=1:S
    a1=power(rate,i);
    a2=factorial(i);
    a3=power(mu,i);
    a=a1/(a2*a3);

    for j=1:i        
        b1=power(rate,j);
        b2=factorial(j);
        b3=power(mu,j);
        b=b1/(b2*b3);
        sum=sum+b;
    end
    
    P_blk_1(i)=a/sum;
end



arrRate=1000;
handRate=275;
rate=arrRate+handRate;
mu=50;

S=10;
sum=0.0;
X2=1:S;
P_blk_2=X2;

for i=1:S
    a1=power(rate,i);
    a2=factorial(i);
    a3=power(mu,i);
    a=a1/(a2*a3);

    for j=1:i
        b1=power(rate,j);
        b2=factorial(j);
        b3=power(mu,j);
        b=b1/(b2*b3);
        sum=sum+b;
    end
    
    P_blk_2(i)=a/sum;
end




arrRate=1000;
handRate=275;
rate=arrRate+handRate;
mu=6150;

S=10;
sum=0.0;
X3=1:S;
P_blk_3=X3;

for i=1:S
    a1=power(rate,i);
    a2=factorial(i);
    a3=power(mu,i);
    a=a1/(a2*a3);

    for j=1:i
        b1=power(rate,j);
        b2=factorial(j);
        b3=power(mu,j);
        b=b1/(b2*b3);
        sum=sum+b;
    end
    
    P_blk_3(i)=a/sum;
end




arrRate=1000;
handRate=275;
rate=arrRate+handRate;
mu=750;

S=10;
sum=0.0;
X4=1:S;
P_blk_4=X4;

for i=1:S
    a1=power(rate,i);
    a2=factorial(i);
    a3=power(mu,i);
    a=a1/(a2*a3);

    for j=1:i
        b1=power(rate,j);
        b2=factorial(j);
        b3=power(mu,j);
        b=b1/(b2*b3);
        sum=sum+b;
    end
    
    P_blk_4(i)=a/sum;
end



figure;
plot(X1,P_blk_1,X2,P_blk_2,X3,P_blk_3,X4,P_blk_4);
%grid on;