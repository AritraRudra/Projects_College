C=5 %No. pf servers
N=2 %Buffer size

%PH(bita,T) distribution parameters
% bita=[1 0 0]
% T=[-3 0 0;0 -3 0;0 0 -3]
% m= %No. of phases in PH distribution
% l= %level 0<=l<=N, 0<=k<=m^l -1
% k= %phase if l==-1, 0<=k<=C-1
% 
lambda= 10 %Rate of arrival of Poisson processes
mu= 1/5 %Inverse of the mean of the exponentially distributed service time
% 
% 
% Ao=M0
% Al=M1
% At=M2


% Creating A0(-1) 

m = C; 
n = N*(N+C+1);%(L+1)*(N+C+1); 
A = zeros(m,n); 
A(m,1)= lambda;
M0=A


% Creating A1(-1) 

m = C; 
n = C; 
A = zeros(m,n); 

A(1,1)= -lambda;
A(1,2)= lambda;
for i = 2: m-1
    for j = 1:n 
        %A(i,i)= 2
        %A(i,i-1)= (i-1)*5;
        %A(i,i+1)= 7;
        A(i,i)= -(i-1)*mu -lambda;
        A(i,i-1)= (i-1)*mu;
        A(i,i+1)= lambda;
    end 
end 
%A(C,C)= 2;
%A(C,C-1)= (C-1)*5;
A(C,C)= -(C-1)*mu -lambda;
A(C,C-1)= (C-1)*mu;
M1=A

% Creating A2(0) 

m = (N+C+1);%(M+1)*(N+C+1);
n = C;
A = zeros(m,n); 
A(1,n)= C*mu;
M2=A