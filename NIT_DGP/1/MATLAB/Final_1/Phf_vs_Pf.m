Pb=0.15;
lambda=50;
lambda_ho=35;

Phf=0.0:0.001:0.99;
x=Phf;
Pf=Phf;
limit=numel(Phf);
k=1;
for i=1:limit
    a=k*lambda*(1-Pb)-lambda_ho*Phf(i);
    b=k*lambda*(1-Pb)+lambda_ho*(1-Phf(i));
    Pf(i)=a/b;
end
y1=Pf;

k=2;
for i=1:limit
    a=k*lambda*(1-Pb)-lambda_ho*Phf(i);
    b=k*lambda*(1-Pb)+lambda_ho*(1-Phf(i));

    Pf(i)=a/b;
end
y2=Pf;

k=3;
for i=1:limit
    a=k*lambda*(1-Pb)-lambda_ho*Phf(i);
    b=k*lambda*(1-Pb)+lambda_ho*(1-Phf(i));
    Pf(i)=a/b;
end
y3=Pf;

k=5;
for i=1:limit
    a=k*lambda*(1-Pb)-lambda_ho*Phf(i);
    b=k*lambda*(1-Pb)+lambda_ho*(1-Phf(i));
    Pf(i)=a/b;
end
y4=Pf;

figure;
plot(x,y1,'-xk',x,y2,'--k',x,y3,':k',x,y4,'-.k');
xlabel('Handoff Failure Probability ( P_h_f ) -->');
ylabel('Forced Termination Probabitily ( P_F ) -->');