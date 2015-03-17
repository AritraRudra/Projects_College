arrivalRate=30
mu=40
R=[1:70]
hfRate=R
sum=0.0;

%syms i
%sum=symsum((2*i+1), 1, R)

for i=1:numel(R)
%sum=sum+2*i+1
syms j
sum=sum+symsum((2*j+1), 1, numel(R))
hfRate(i)= arrivalRate*mu/((mu-arrivalRate)*sum)
end

figure
plot(R,hfRate)
grid on


%sum=2*R+1

%div=((mu-arrivalRate)*sum)
%hfRate= R_vs_HfR(arrivalRate, mu, R)
%hfRate= arrivalRate*mu/((mu-arrivalRate)*sum)

%plot(R,hfRate)
%%fun=@R_vs_HfR;
%%fplot(fun,[1 10])

%function out=fun(arrivalRate, mu, R)
%out = arrivalRate*mu/((mu-arrivalRate)*sum)

% function[P]=MyLsqFit(N) 
% x=n(1,:); 
% y=n(2,:); 
% P=polyfit(x,y,1); 
% y1=polyval(P,x); 
% plot(x,y,'o',x,y1); 


%v=linspace(0,10,100);
% R=1000;
% p=R_vs_HfR(v,1/R);
% plot(v,p)
% xlabel('Voltage (V) ')
% ylabel('Power (W) ');
% title('Power Dissapation for a 1000 Ohm Resistor');


% x=1:0.1:100 
% y=x% makes y and x the same dimensions 
% for i=1:numel(x) 
% y=exp(1/x(i)) 
% end 
% plot(x,y);