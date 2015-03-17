% clear;
% noOfNodes = 150;
% R = 65; % maximum range;
% locMatrix = load('D:/Java/Project/Mine/4/Clustering/Network_Data.txt');
% netXloc = locMatrix(1,:);
% netYloc = locMatrix(2,:);
% adj_matrix = zeros(noOfNodes,noOfNodes);
% dist_matrix = zeros(noOfNodes,noOfNodes);
% figure(1);
% clf;
% hold on;
% %count=0;
% %temp_Nbrs=zeros(1,noOfNodes);
% %arr_Nbrs=cell(1,noOfNodes);
% for i = 1:noOfNodes
%     plot(netXloc(i), netYloc(i), '.');
%     text(netXloc(i), netYloc(i), num2str(i));
%     for j = 1:noOfNodes
%         dist = sqrt((netXloc(i) - netXloc(j))^2 + (netYloc(i) - netYloc(j))^2);
%         if dist <= R
%             if ( i == j )
%                 adj_matrix(i,j)=0;
%             else
%                 adj_matrix(i,j) = 1;
%                 dist_matrix(i,j) = dist; 
% 				line([netXloc(i) netXloc(j)], [netYloc(i) netYloc(j)], 'Color','g','LineStyle', ':');
%             end;
%             %temp_Nbrs(j)=j; % isNbr;
%             %count=count+1;
%             %line([netXloc(i) netXloc(j)], [netYloc(i) netYloc(j)], 'Color','g','LineStyle', ':');
%         end;
%     end;
% end;


% ########### Graph 1 from 1,2,3 ###########
clear;
y = [8,8,9,11;
    10,10,9,10;
    11,11,10,10];
%y = round(rand(5,3)*10);
figure(1);
clf;
fH = gcf;
colormap(jet(4));
%h = bar(y);
bar(y,'grouped');
title('Range = 50, Area = 200X200');
n = length(y);
%colormap(summer(n));
grid on;
l = cell(1,n);
l{1}='No. of nodes = 150';
l{2}='No. of nodes = 200';
l{3}='No. of nodes = 250';
set(gca,'xticklabel', l);
ylim([0,15]);
legend('Lowest ID','Min','Max','Proposed');
%applyhatch_pluscolor(fH, '\-x.', 0, [1 0 1 0], summer(n));
applyhatch_pluscolor(fH, '\-x.', 0, [1 0 1 0], jet(4));
%legend(h,l);



% ########### Graph 2 from 4,5,6 ###########
clear;
y = [7,7,9,8;
    7,7,6,7;
    6,6,6,3];
%y = round(rand(5,3)*10);
figure(2);
clf;
fH = gcf;
colormap(jet(4));
%bar(y);
bar(y,'grouped');
title('No. of nodes = 250, Area = 200X200');
n = length(y);
%colormap(summer(n));
grid on;
l = cell(1,n);
l{1}='Range = 60';
l{2}='Range = 70';
l{3}='Range = 80';
set(gca,'xticklabel', l);
ylim([0,12]);
legend('Lowest ID','Min','Max','Proposed');
applyhatch_pluscolor(fH, '\-x.', 0, [1 0 1 0], jet(n));



% ########### Graph 3 from 7,8,9 ###########
clear;
y = [15,15,13,17;
    14,12,13,12;
    5,5,5,4];
%y = round(rand(5,3)*10);
figure(3);
clf;
fH = gcf;
colormap(jet(4));
%bar(y);
bar(y,'grouped');
title('No. of nodes = 300, Area = 200X200');
n = length(y);
%colormap(summer(n));
grid on;
l = cell(1,3);
l{1}='Range = 40';
l{2}='Range = 50';
l{3}='Range = 90';
set(gca,'xticklabel', l);
ylim([0,20]);
legend('Lowest ID','Min','Max','Proposed');
applyhatch_pluscolor(fH, '\-x.', 0, [1 0 1 0], jet(n));


% ########### Graph 4 from 10,11,12,13,14 ###########
clear;
y = [9,9,10,12;
    8,8,8,8;
    7,7,7,6;
    6,6,6,5;
    5,5,5,5];
%y = round(rand(5,3)*10);
figure(4);
clf;
fH = gcf;
colormap(jet(5));
%bar(y);
bar(y,'grouped');
title('No. of nodes = 150, Area = 200x200');
n = length(y);
%colormap(summer(n));
grid on;
l = cell(1,n);
l{1}='Range = 50';
l{2}='Range = 60';
l{3}='Range = 65';
l{4}='Range = 70';
l{5}='Range = 80';
set(gca,'xticklabel', l);
ylim([0,15]);
legend('Lowest ID','Min','Max','Proposed');
applyhatch_pluscolor(fH, '\-x.', 0, [1 0 1 0], jet(n));


% ########### Graph 5 from 15,16,17 ###########
clear;
y = [6,6,5,5;
    5,5,4,3;
    4,4,4,3];
%y = round(rand(5,3)*10);
figure(5);
clf;
fH = gcf;
colormap(jet(4));
%bar(y);
bar(y,'grouped');
title('No. of nodes = 50, Area = 100x100');
n = length(y);
%colormap(summer(n));
grid on;
l = cell(1,n);
l{1}='Range = 30';
l{2}='Range = 40';
l{3}='Range = 50';
set(gca,'xticklabel', l);
ylim([0,9]);
legend('Lowest ID','Min','Max','Proposed');
applyhatch_pluscolor(fH, '\-x.', 0, [1 0 1 0], jet(n));



% ########### Graph 6 from 19,20,21,22 ########### not experiment 18
clear;
y = [5,5,6,5;
    5,5,6,4;
    4,4,4,3;
    3,3,3,2];
%y = round(rand(5,3)*10);
figure(6);
clf;
fH = gcf;
colormap(jet(4));
bar(y);
h = bar(y,'grouped');
title('No. of nodes = 100, Area = 100x100');
n = length(y);
grid on;
l = cell(1,4);
l{1}='Range = 36';
l{2}='Range = 40';
l{3}='Range = 50';
l{4}='Range = 59';
set(gca,'xticklabel', l);
ylim([0,8]);
legend('Lowest ID','Min','Max','Proposed');
applyhatch_pluscolor(fH, '\-x.', 0, [1 0 1 0], jet(n));