Phf=0.05;

%n=[1:10]%/70:1/10:1/40]
%n=[1/40:1/10:1/20]
%n= 1/200:1/20:1/20
n=[0.01:0.0005:0.9]
mu=0.5
Pf=n

for i=1:numel(n)
Pf(i) = n(i)*Phf/(mu+n(i)*Phf)
end

figure
plot(n,Pf)
grid on
%plot(x,y1,x,y2,x,y3,x,y4,x,y5,x,y6)
title('????')
xlabel('lambda')
ylabel('lambda_h^o')