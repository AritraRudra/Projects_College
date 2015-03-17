%function  [out]=R_vs_HfR(arr_Rate,mu,R)
%syms i
%sum=symsum((2*i+1), 1, R)
%sum=2*R+1
%out=arr_Rate*mu/((mu-arr_Rate)*sum)

function Y=R_vs_HfR(R)
arr_Rate=5
mu=7

sum=2*R+1
%Y(:,1) = R
Y(:,1) = arr_Rate*mu/((mu-arr_Rate)*sum)

% function Y = myfun(x)
% Y(:,1) = 200*sin(x(:))./x(:);
% Y(:,2) = x(:).^2;