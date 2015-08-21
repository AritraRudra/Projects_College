Ph=0.2;
Pb=0.35;
lambda=100;

%lambda_ho=1:100;
lambda_ho=1:100;
x=lambda_ho;
Phf=lambda_ho;

k=1;
for i=1:100
    Phf(i)=(lambda_ho(i)*(1-Ph)-k*lambda*Ph*(1-Pb))/(lambda_ho(i)*Ph);
end
y1=Phf;

k=2;
for i=1:100
    Phf(i)=(lambda_ho(i)*(1-Ph)-k*lambda*Ph*(1-Pb))/(lambda_ho(i)*Ph);
end
y2=Phf;

k=3;
for i=1:100
    Phf(i)=(lambda_ho(i)*(1-Ph)-k*lambda*Ph*(1-Pb))/(lambda_ho(i)*Ph);
end
y3=Phf;

k=5;
for i=1:100
    Phf(i)=(lambda_ho(i)*(1-Ph)-k*lambda*Ph*(1-Pb))/(lambda_ho(i)*Ph);
end
y4=Phf;

k=10;
for i=1:100
    Phf(i)=(lambda_ho(i)*(1-Ph)-k*lambda*Ph*(1-Pb))/(lambda_ho(i)*Ph);
end
y5=Phf;

figure;
subplot(2,1,1);
plot(x,y1,'-',x,y2,'--',x,y3,':',x,y4,'-.',x,y5);






Pb=0.15;
lambda=50;
lambda_ho=40;
Ph=0.01:0.01:0.99;
x=Ph;
Phf=Ph;

k=2;
for i=1:numel(Ph);
    Phf(i)=(lambda_ho*(1-Ph(i))-k*lambda*Ph(i)*(1-Pb))/(lambda_ho*Ph(i))/100;
end
y1=Phf;
subplot(2,1,2);
plot(x,y1);