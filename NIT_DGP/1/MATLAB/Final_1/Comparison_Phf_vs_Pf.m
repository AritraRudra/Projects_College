Pb=0.15;
lambda=120;
lambda_ho=100;
mu=60;

Phf=0.0:0.001:0.99;
x=Phf;
Pf=Phf;
limit=numel(Phf);

for i=1:limit
    a=lambda_ho*Phf(i);
    b=mu+lambda_ho*Phf(i);
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

figure;
plot(x,y1,'-k',x,y2,'-.k');
xlabel('Handoff Failure Probability ( P_h_f ) -->');
ylabel('Forced Termination Probabitily ( P_F ) -->');
set(gca,'YTick',[0 0.1 0.2 0.3 0.4 0.5 0.6 0.7 0.8 0.9 1]);
hleg1 = legend('Traditional','Proposed');
set(hleg1,'Location','SouthEast');