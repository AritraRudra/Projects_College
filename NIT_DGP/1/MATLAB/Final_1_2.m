function [Pb,Pe]=get_Pb_Pe(lmda,mu)
C=10;
N=5;
lmda=24;
mu=20;
q=0.30;
%bita=[1 0 0];
bita=(1/3)*[1 1 1];
%bita_prob=0.5;
%T=[-3 3 0;0 -3 3;0 0 -3];
T=(-56/150)*[50 0 0;0 10 0;0 0 1];

m=3;    % or m^N-1 is the range
em=ones(m,1);
Im=eye(m);
t=-T*em;


% A_0-1==> (m^l-1)*C r*s
% A_1^-1==> C*C s*s
% A_2^0==> C*(m^l-1) s*r
%others ==> (m^l-1)*(m^l-1) r*r


A0_1=zeros(m);
A0_1(m,1)=lmda;
%A0_1=zeros(C)
%A0_1(C,1)=lmd


% Creating A1(-1) 
a = m;%C; 
b = m;%C; 
A = zeros(a,b);

A(1,1)= -lmda;
A(1,2)= lmda;
for i = 2: a-1
    for j = 1:b 
        %A(i,i)= 2
        %A(i,i-1)= (i-1)*5;
        %A(i,i+1)= 7;
        A(i,i)= -(i-1)*mu -lmda;
        A(i,i-1)= (i-1)*mu;
        A(i,i+1)= lmda;
    end 
end 
A(m,m)= -(m-1)*mu -lmda;
A(m,m-1)= (m-1)*mu;
A1_1=A;
%A(C,C)= 2;
%A(C,C-1)= (C-1)*5;
% % A(C,C)= -(C-1)*mu -lambda;
% % A(C,C-1)= (C-1)*mu;


% Creating A2(0) 
a = m;%(N+C+1);%(M+1)*(N+C+1);
b = m;%C;
A = zeros(a,b); 
A(1,b)= C*mu;
A2_0=A;


%A matrix of all A_0^(l) matrices
A0 = cell(1, N);    %0<=l<N
prod=lmda*bita;
for i = 1:N
    %result{i} = zeros(arraySize);
    dim=power(m,(i-1));
    Im_l=eye(dim);
    A0{i}=kron(Im_l,prod);
end

%A matrix of all A_2^(l) matrices
U1=cell(1,N);   %1<=l<=N
%Im=eye(m);  already defined
U1{1}=t;  %l=1
%U1{:,1}=t;
for i=2:N   %1< l<=N
    dim=power(m,(i-1));
    Im_l1=eye(dim);
    U1_1=kron(t,Im_l1);
    U1_2=kron(Im,U1{(i-1)});
    %U1_2=kron(Im,U1{:,(i-1)});
    U1{i}=U1_1 + U1_2;
end

%for SIRO discipline
U2=cell(1,(N-1));   %1<=l< N
%em=ones(m,1);  already defined
U2{1}=C*mu*em;    %l=1
for i=2:(N-1)
    dim=power(m,(i-1));
    Im_l1=eye(dim);
    U2_1=(C*mu/i)*kron(em,Im_l1);
    U2_2=((i-1)/i)*kron(Im,U2{(i-1)});
    %U2_2=((i-1)/i)*kron(Im,U2{:,(i-1)});
    U2{i}=U2_1 + U2_2;
end
A2=cell(1,N);
for i=1:(N-1)
    A2{i}=U1{i}+U2{i};
end
A2{N}=U1{N};
%A2{1}
%A2{2}

%An array of all A_1^(l) matrices
A1=cell(1,N);   %1<=l<=N
D=cell(1,N);    %1<=l<=N
D{1}=T;     %l=1
A1{1}=D{1}; %##!!??
for i=2:N   %1<l<=N
    dim=power(m,(i-1));
    Im_l1=eye(dim);
    D_1=kron(T,Im_l1);
    D_2=kron(Im,D{(i-1)});
    if (i==N)
        prod=lmda*bita;
        D_N=kron(kron(em,Im_l1),prod);
        D_2=D_2+ q*D_N;
    end
    D{i}=D_1+D_2;
    
    dim2=power(m,i);
    Im_l=eye(dim2);
    D_4=(C*mu+ lmda)*Im_l;
    if (i==N)
        D_4=(C*mu+ q*lmda)*Im_l;
    end
    
    A1{i}=D{i}-D_4;
end
% A1{1}
% A1{2}
% A1{3}


e3=ones(m,1);
A1_0=-(A0{1}*e3 + A2_0*e3)/e3;
for i=2:m
    A1_0(i,i)=A1_0(i,1);
    A1_0(i,1)=0;
end


%Algorithm for calculating pi
% X=cell(1,(N+1));    %l=N-1,N-2,...,1,0,-1;  -1 mone hoe hobe naa
% Y=A1{N};
% %X{(N+1)}=A0{N}/(-Y)    %X{N}=A0{(N-1)}/(-Y)
% X{(N+1)}=A0{N}*inv(-Y);
% for i=(N-1):-1:1
%     Y=A1{i}+X{(i+2)}*A2{(i+1)};
%     %X{(i+1)}=A0{i}/(-Y);     %X{i}=A0{(i-1)}/(-Y)
%     X{(i+1)}=A0{i}*inv(-Y);
% end
% Y=A1_0+X{2}*A2{1};      %### A1_0=??
% %X{1}=A0_1/(-Y)
% X{1}=A0_1*inv(-Y);



%Algorithm for calculating pi
X=cell(1,N);    %l=N-1,N-2,...,1,0,-1;  -1 mone hoe hobe naa
Y=A1{N};
%X{(N+1)}=A0{N}/(-Y)    %X{N}=A0{(N-1)}/(-Y)
X{N}=A0{N}*inv(-Y);
for i=(N-1):-1:1
    Y=A1{i}+X{(i+1)}*A2{(i+1)};
    %X{(i+1)}=A0{i}/(-Y);     %X{i}=A0{(i-1)}/(-Y)
    X{i}=A0{i}*inv(-Y);
end
%Y=A1_0+X{2}*A2{1};      %### A1_0=??
X{1}=A0_1*inv(-Y);  % this one or
%X{1}

%#####  Using QBD tools
%[G1,R1]=QBD_CR(A2{1},A1_0,A0_1)
%pie=QBD_pi(A2_0,A1_1,R1,C);
%e=ones(3,1);
%syms x
%s = solve('x + y = 1, x - 11*y = 5')
%solve('x*Y=0 , x*e=1')
%##p1=1\e3
%p2=1/e3

%#####  Using QBD tools
[Pie,R0]=get_Pi_R(C,N,lmda,mu,m,bita,T,q);




%X{1}=R0;    % this one, which one to use?
%X{1}
Pies=cell(1,(N+1));
Pies{1}=Pie;
for i=2:(N+1)
    Pies{i}=Pies{(i-1)}*X{(i-1)};
end

%size(Pies{(N+1)})
ef=ones((numel(Pies{(N+1)})),1);    %final column vector
Pb=(1-q)*Pies{(N+1)}*ef
Pe=q*Pies{(N+1)}*ef