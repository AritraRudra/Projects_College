lamda=1:0.1:6
mu=0.1:0.01:0.6
p=0.4
pie=[3 1 0]
pb=(1-p)*pie*[1;1;1]
pe=p*pie*[1;1;1]
X=lamda/mu
Y=pb+pe

% p=0.4
% pie=[3 1 0]
% for lamda=1:0.1:6 
%     mu=1%:0.1:6    
%     
%     pb=(1-p)*pie*[1;1;1]
%     pe=p*pie*[1;1;1]
%     X=lamda/mu
%     Y=pb+pe
% end
%plot(lamda,Y)
plot(X,Y)
title('Plot title');
xlabel('X Axis');
ylabel('Y Axis');
