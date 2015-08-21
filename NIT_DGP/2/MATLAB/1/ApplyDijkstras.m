clear;
adjMat = load('Network_Data.txt');
costDelayMat = load('Delay_Cost_Data.txt');
costEnrgMat = load('Energy_Cost_Data.txt');
actDelayMat = load('Delay_Actual_Data.txt');
actEnrgMat = load('Energy_Actual_Data.txt');
distMat = load('Distances_Between_Nodes.txt');

xy_loc_mat = load('XY_Loc.txt');
netXloc = xy_loc_mat(1,:);
netYloc = xy_loc_mat(2,:);

dest = 64; %length(adjMat);
% [costs,paths] = dijkstra(adjMat,costDelayMat,1,dest)
% [costs,paths] = dijkstra(adjMat,costEnrgMat,1,dest)
% [costs,paths] = dijkstra(adjMat,actDelayMat,1,dest)
% [costs,paths] = dijkstra(adjMat,actEnrgMat,1,dest)
 [costs,paths] = dijkstra(adjMat,distMat,1,dest)

size = length(adjMat);

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

figure(2);
clf;
hold on;
for i = 1:size
    plot(netXloc(i), netYloc(i), '.');
    text(netXloc(i), netYloc(i), num2str(i));
    for j = 1:size
        if(adjMat(i,j) == 1)
            line([netXloc(i) netXloc(j)], [netYloc(i) netYloc(j)],'LineWidth',1,'Color','g','LineStyle', ':');
        end
    end
end

size = length(paths);
for i = 1:(size - 1)
    a = paths(i);
    b = paths((i + 1));
    line([netXloc(a) netXloc(b)], [netYloc(a) netYloc(b)], 'LineWidth',2, 'Color','r','LineStyle', ':');
end