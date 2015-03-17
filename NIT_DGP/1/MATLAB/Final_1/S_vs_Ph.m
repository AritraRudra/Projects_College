format long;


%%Handoff arrival rate varying
handRate=10;

arrRate=100;
mu=50;

S=10;
rate=arrRate+handRate;
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


handRate=30;
rate=arrRate+handRate;
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



handRate=60;
rate=arrRate+handRate;
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



handRate=100;
rate=arrRate+handRate;
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


handRate=500;
rate=arrRate+handRate;
sum=0.0;
X5=1:S;
P_blk_5=X5;

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
    
    P_blk_5(i)=a/sum;
end

figure;
%plot(X1,P_blk_1,X2,P_blk_2,X3,P_blk_3,X4,P_blk_4,X5,P_blk_5);
subplot(3,1,1);
plot(X1,P_blk_1,'-k',X2,P_blk_2,'--k',X3,P_blk_3,':k',X4,P_blk_4,'-.k',X5,P_blk_5,'-ok');
set(gca,'YTick',[0 0.1 0.2 0.3 0.4 0.5 0.6 0.7 0.8 0.9 1]);
xlabel('No. of Channels (S) -->');
ylabel('Handoff Probabitily (P_h) -->');
hleg1 = legend('\lambda_h_o=10','\lambda_h_o=30','\lambda_h_o=60','\lambda_h_o=100','\lambda_h_o=500');



%%mu is varying
mu=20;

handRate=80;
arrRate=100;

rate=arrRate+handRate;
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


mu=50;

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



mu=100;
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


mu=200;
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


mu=500;
sum=0.0;
X5=1:S;
P_blk_5=X5;

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
    
    P_blk_5(i)=a/sum;
end

subplot(3,1,2);
plot(X1,P_blk_1,'-k',X2,P_blk_2,'--k',X3,P_blk_3,':k',X4,P_blk_4,'-.k',X5,P_blk_5,'-ok');
set(gca,'YTick',[0 0.1 0.2 0.3 0.4 0.5 0.6 0.7 0.8 0.9 1]);
xlabel('No. of Channels (S) -->');
ylabel('Handoff Probabitily (P_h) -->');
hleg2 = legend('\mu=20','\mu=50','\mu=100','\mu=200','\mu=500');






%%Arrival rate (hence handoff rate too) is varying
mu=80;
handRate=70;

arrRate=70;
rate=arrRate+handRate;

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


arrRate=80;
rate=arrRate+handRate;

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



arrRate=100;
rate=arrRate+handRate;
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


arrRate=150;
rate=arrRate+handRate;
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


arrRate=500;
rate=arrRate+handRate;
sum=0.0;
X5=1:S;
P_blk_5=X5;

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
    
    P_blk_5(i)=a/sum;
end

subplot(3,1,3);
plot(X1,P_blk_1,'-k',X2,P_blk_2,'--k',X3,P_blk_3,':k',X4,P_blk_4,'-.k',X5,P_blk_5,'-ok');
set(gca,'YTick',[0 0.1 0.2 0.3 0.4 0.5 0.6 0.7 0.8 0.9 1]);
xlabel('No. of Channels (S) -->');
ylabel('Handoff Probabitily (P_h) -->');
hleg2 = legend('\lambda_n = 70','\lambda_n = 80','\lambda_n = 100','\lambda_n = 150','\lambda_n = 500');