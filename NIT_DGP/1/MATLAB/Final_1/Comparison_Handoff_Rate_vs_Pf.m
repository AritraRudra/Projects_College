Pb=0.2;
Phf=0.05;
lambda=120;
mu=70;

lambda_ho=1:100;
x=lambda_ho;
Pf=lambda_ho;

limit=numel(lambda_ho);

for i=1:limit
    a=lambda_ho(i)*Phf;
    b=mu+lambda_ho(i)*Phf;
    Pf(i)=a/b;
end
y1=Pf;

k=1;
for i=1:limit
    a=k*lambda*(1-Pb)-lambda_ho(i)*Phf;
    b=k*lambda*(1-Pb)+lambda_ho(i)*(1-Phf);
    Pf(i)=a/b;
end
y2=Pf;

figure;
plot(x,y1,':k',x,y2,'-.k');
xlabel('Handoff Rate ( \lambda_h_o ) -->');
ylabel('Forced Termination Probabitily (P_F) -->');
hleg1 = legend('Traditional','Proposed');