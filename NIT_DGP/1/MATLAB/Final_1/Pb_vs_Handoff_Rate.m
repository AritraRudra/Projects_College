Ph=0.2;
Phf=0.1;
lambda=50;

Pb=0.01:0.01:1;
x=Pb;

k=1;
lambda_ho=k*(Ph*(1-Pb)/(1-Ph*(1-Phf)))*lambda;
y1=lambda_ho;

figure;
plot(x,y1);