Ph=0.2;
Pb=0.1;
Phf=0.005;

lambda=1:60;
x=lambda;

k=1;
lambda_ho=k*(Ph*(1-Pb)/(1-Ph*(1-Phf)))*lambda;
y1=lambda_ho;

k=2;
lambda_ho=k*(Ph*(1-Pb)/(1-Ph*(1-Phf)))*lambda;
y2=lambda_ho;

k=3;
lambda_ho=k*(Ph*(1-Pb)/(1-Ph*(1-Phf)))*lambda;
y3=lambda_ho;

k=5;
lambda_ho=k*(Ph*(1-Pb)/(1-Ph*(1-Phf)))*lambda;
y4=lambda_ho;

k=10;
lambda_ho=k*(Ph*(1-Pb)/(1-Ph*(1-Phf)))*lambda;
y5=lambda_ho;

k=20;
lambda_ho=k*(Ph*(1-Pb)/(1-Ph*(1-Phf)))*lambda;
y6=lambda_ho;

figure;
%subplot(2,1,1);
plot(x,y1,'-k',x,y2,'--k',x,y3,':k',x,y4,'-.k',x,y5,'-xk',x,y6,'-ok');
title('Arrival Rate VS Handoff Rate')
xlabel('Arrival Rate ( \lambda_n )  -->');
ylabel('Handoff Rate ( \lambda_h_o ) -->');
%xlabel('lambda')
%ylabel('lambda_ho')

%subplot(2,1,2);
%plot(x,y2);