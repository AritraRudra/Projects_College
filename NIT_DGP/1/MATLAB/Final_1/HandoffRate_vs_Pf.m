Pb=0.2;
Phf=0.05;
lambda=150;

lambda_ho=1:100;
x=lambda_ho;
Pf=lambda_ho;

k=1;
for i=1:100
    a=k*lambda*(1-Pb)-lambda_ho(i)*Phf;
    b=k*lambda*(1-Pb)+lambda_ho(i)*(1-Phf);
    Pf(i)=a/b;
end
y1=Pf;

k=2;
for i=1:100
    a=k*lambda*(1-Pb)-lambda_ho(i)*Phf;
    b=k*lambda*(1-Pb)+lambda_ho(i)*(1-Phf);
    Pf(i)=a/b;
end
y2=Pf;

k=3;
for i=1:100
    a=k*lambda*(1-Pb)-lambda_ho(i)*Phf;
    b=k*lambda*(1-Pb)+lambda_ho(i)*(1-Phf);
    Pf(i)=a/b;
end
y3=Pf;

k=5;
for i=1:100
    a=k*lambda*(1-Pb)-lambda_ho(i)*Phf;
    b=k*lambda*(1-Pb)+lambda_ho(i)*(1-Phf);
    Pf(i)=a/b;
end
y4=Pf;

figure;
%plot(x,y1,'-k',x,y2,'--k',x,y3,':k',x,y4,'-.k');
plot(x,y1,'-ok',x,y2,'-xk',x,y3,'k',x,y4,'-.k');
xlabel('Handoff Rate ( \lambda_h_o ) -->');
ylabel('Forced Termination Probabitily (P_F) -->');