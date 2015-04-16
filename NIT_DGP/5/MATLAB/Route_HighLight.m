clear all;

adjMat = importdata('D:\College\Project\5\Data\Set_5\Network_Data.txt');
xy_loc_mat = load('D:\College\Project\5\Data\Set_5\XY_Loc.txt');
netXloc = xy_loc_mat(1,:);
netYloc = xy_loc_mat(2,:);
len = length(adjMat);


% Route data for data set_5
route1=[1, 53, 31, 11, 41, 60, 64];    % S = 0.5
route2=[1, 50, 37, 52, 28, 62, 30, 48, 64];   % Reduce Energy cost, S = 0.8
route3=[1, 50, 3, 47, 44, 48, 64];    % Reduce Delay cost, S = 0.15
route4=[1, 53, 31, 11, 46, 60, 64];    % S = 0.85 and changes
route5=[1, 50, 3, 58, 17, 62, 63, 64];    % S = 0.15 and changes

% % Route data for data set_8
% % route1=[1, 10, 43, 9, 53, 64];    % S = 0.5
% % route2=[1, 11, 43, 9, 61, 4, 64];   % S = 0.8
% % route3=[1, 10, 8, 2, 9, 53, 64];    % S = 0.15
% % route4=[1, 11, 43, 9, 63, 64];    % S = 0.8 and changes
% % route5=[1, 10, 8, 2, 9, 27, 61, 4, 64];    % S = 0.15 and changes
% 
%
% 
% % [costs,paths] = dijkstra(adjMat,costDelayMat,1,dest)
% % [costs,paths] = dijkstra(adjMat,costEnrgMat,1,dest)
% % [costs,paths] = dijkstra(adjMat,actDelayMat,1,dest)
% % [costs,paths] = dijkstra(adjMat,actEnrgMat,1,dest)
% % [costs,paths] = dijkstra(adjMat,distMat,1,dest)
% 
% 
% %% Route 1, S is global and S = 0.5, i.e., equal preference
% figure(1);
% clf;
% hold on;
% box on;
% for i = 1:len
%     plot(netXloc(i), netYloc(i), '.');
%     text(netXloc(i), netYloc(i), num2str(i));
%     for j = 1:len
%         if(adjMat(i,j) == 1)
%             line([netXloc(i) netXloc(j)], [netYloc(i) netYloc(j)],'LineWidth',1,'Color','g','LineStyle', ':');  % {-}, {--}, {:}, {-.}, {none}
%         end
%     end
% end
% size = length(route1);
% for i = 2:size
%     a = route1((i-1));
%     b = route1(i);
%     line([netXloc(a) netXloc(b)], [netYloc(a) netYloc(b)], 'LineWidth',3, 'Color','k','LineStyle', '-');
% end
% 
% %% Route 3, S is global and S = 0.8, i.e., energy critical
% figure(2);
% clf;
% hold on;
% box on;
% for i = 1:len
%     plot(netXloc(i), netYloc(i), '.');
%     text(netXloc(i), netYloc(i), num2str(i));
%     for j = 1:len
%         if(adjMat(i,j) == 1)
%             line([netXloc(i) netXloc(j)], [netYloc(i) netYloc(j)],'LineWidth',1,'Color','g','LineStyle', ':');
%         end
%     end
% end
% size = length(route2);
% for i = 2:size
%     a = route2((i-1));
%     b = route2(i);
%     line([netXloc(a) netXloc(b)], [netYloc(a) netYloc(b)], 'LineWidth',3, 'Color','k','LineStyle', '-');
% end
% 
% %% Route 3, S is global and S = 0.15, i.e., delay sensitive
% figure(3);
% clf;
% hold on;
% box on;
% for i = 1:len
%     plot(netXloc(i), netYloc(i), '.');
%     text(netXloc(i), netYloc(i), num2str(i));
%     for j = 1:len
%         if(adjMat(i,j) == 1)
%             line([netXloc(i) netXloc(j)], [netYloc(i) netYloc(j)],'LineWidth',1,'Color','g','LineStyle', ':');
%         end
%     end
% end
% size = length(route3);
% for i = 2:size
%     a = route3((i-1));
%     b = route3(i);
%     line([netXloc(a) netXloc(b)], [netYloc(a) netYloc(b)], 'LineWidth',3, 'Color','k','LineStyle', '-');
% end
% 
% %% Route 4, S is local and S = 0.8, i.e., energy critical with some nodes having S=0.05
% figure(4);
% clf;
% hold on;
% box on;
% for i = 1:len
%     plot(netXloc(i), netYloc(i), '.');
%     text(netXloc(i), netYloc(i), num2str(i));
%     for j = 1:len
%         if(adjMat(i,j) == 1)
%             line([netXloc(i) netXloc(j)], [netYloc(i) netYloc(j)],'LineWidth',1,'Color','g','LineStyle', ':');
%         end
%     end
% end
% size = length(route4);
% for i = 2:size
%     a = route4((i-1));
%     b = route4(i);
%     line([netXloc(a) netXloc(b)], [netYloc(a) netYloc(b)], 'LineWidth',3, 'Color','k','LineStyle', '-');
% end
% 
% %% Route 5, S is local and S = 0.15, i.e., delay sensitive with some nodes having S=0.95
% figure(5);
% clf;
% hold on;
% for i = 1:len
%     plot(netXloc(i), netYloc(i), '.');
%     text(netXloc(i), netYloc(i), num2str(i));
%     for j = 1:len
%         if(adjMat(i,j) == 1)
%             line([netXloc(i) netXloc(j)], [netYloc(i) netYloc(j)],'LineWidth',1,'Color','g','LineStyle', ':');
%         end
%     end
% end
% size = length(route5);
% for i = 2:size
%     a = route5((i-1));
%     b = route5(i);
%     line([netXloc(a) netXloc(b)], [netYloc(a) netYloc(b)], 'LineWidth',3, 'Color','k','LineStyle', '-');
% end
% box on;



%% Route AIO
figToSave=figure(6);
clf;
hold on;
for i = 1:len
    plot(netXloc(i), netYloc(i), '.');
    text(netXloc(i), netYloc(i), num2str(i));
    for j = 1:len
        if(adjMat(i,j) == 1)
            line([netXloc(i) netXloc(j)], [netYloc(i) netYloc(j)],'LineWidth',1,'Color','k','LineStyle', ':');
        end
    end
end
size = length(route1);
for i = 2:size
    a = route1((i-1));
    b = route1(i);
    hb(i)=line([netXloc(a) netXloc(b)], [netYloc(a) netYloc(b)], 'LineWidth',3, 'Color','k','Marker','o','LineStyle', '--');
end
size = length(route2);
for i = 2:size
    a = route2((i-1));
    b = route2(i);
    line([netXloc(a) netXloc(b)], [netYloc(a) netYloc(b)], 'LineWidth',3, 'Color','k','Marker','x','LineStyle', ':');
end
size = length(route3);
for i = 2:size
    a = route3((i-1));
    b = route3(i);
    line([netXloc(a) netXloc(b)], [netYloc(a) netYloc(b)], 'LineWidth',3, 'Color','k','Marker','s','LineStyle', ':');
end
size = length(route4);
for i = 2:size
    a = route4((i-1));
    b = route4(i);
    line([netXloc(a) netXloc(b)], [netYloc(a) netYloc(b)], 'LineWidth',3, 'Color','k','Marker','^','LineStyle', '-.');
end
size = length(route5);
for i = 2:size
    a = route5((i-1));
    b = route5(i);
    line([netXloc(a) netXloc(b)], [netYloc(a) netYloc(b)], 'LineWidth',3, 'Color','k','Marker','v','LineStyle', '-.');
end
legend('CASE I : Equal Preference to both QoS','CASE II :Energy Aware Network', 'CASE III : Delay Sensitive Network','CASE IV : Energy Aware with few Delay Sensitive Nodes', 'CASE V : Delay Sensitive with few Energy Aware Nodes' );
box on;

%saveas(figToSave,'test','jpg');  % http://stackoverflow.com/a/12160411/1679643

%hgexport(gcf, 'figure1.jpg', hgexport('factorystyle'), 'Format', 'jpeg'); % http://stackoverflow.com/a/17903339/1679643

% http://stackoverflow.com/a/10605128/1679643
% get style sheet info
% snam='default1'; % The name of your style file (NO extension)
% s=hgexport('readstyle',snam);
% 
% %apply style sheet info
% fnam='myfig.jpeg'; % your file name
% s.Format = 'jpeg'; %I needed this to make it work but maybe you wont.
% hgexport(gcf,fnam,s);


%% Dijkstra

% adjMat = importdata('D:\College\Project\5\Results\Set_8\Network_Data.txt');
% costDelayMat = importdata('D:\College\Project\5\Results\Set_8\Delay_Cost_Data.txt');
% costEnrgMat = importdata('D:\College\Project\5\Results\Set_8\Energy_Cost_Data.txt');
% distMat = importdata('D:\College\Project\5\Results\Set_8\Distances_Between_Nodes.txt');
% xy_loc_mat = importdata('D:\College\Project\5\Results\Set_8\XY_Loc.txt');
% netXloc = xy_loc_mat(1,:);
% netYloc = xy_loc_mat(2,:);
% 
% dest = 64; %length(adjMat);
% % [costs,paths] = dijkstra(adjMat,costDelayMat,1,dest)
% % [costs,paths] = dijkstra(adjMat,costEnrgMat,1,dest)
%  [costs,paths] = dijkstra(adjMat,distMat,1,dest)
% 
% size = length(adjMat);

% tempMat = zeros(size,size);
% for i = 1:size
%     for j = 1:size
%         tempMat(i,j) = 99999999.0;
%         if(adjMat(i,j) == 1)
%             tempMat(i,j) = costDelayMat(i,j) + costEnrgMat(i,j);
%             % tempMat(i,j) = actDelayMat(i,j) + actEnrgMat(i,j);
%             %tempMat(i,j) = distMat(i,j) + actEnrgMat(i,j);
%         end
%     end
% end
% [costs,paths] = dijkstra(adjMat,tempMat,1,dest);

%costs

% figure(7);
% clf;
% hold on;
% for i = 1:size
%     plot(netXloc(i), netYloc(i), '.');
%     text(netXloc(i), netYloc(i), num2str(i));
%     for j = 1:size
%         if(adjMat(i,j) == 1)
%             line([netXloc(i) netXloc(j)], [netYloc(i) netYloc(j)],'LineWidth',1,'Color','g','LineStyle', ':');
%         end
%     end
% end
% 
% size = length(paths);
% for i = 2:size
%     a = paths(i-1);
%     b = paths(i);
%     line([netXloc(a) netXloc(b)], [netYloc(a) netYloc(b)], 'LineWidth',2, 'Color','r','LineStyle', ':');
% end
% box on;
