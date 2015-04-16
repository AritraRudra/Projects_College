%% Adjacency Matrix and Distance Matrix Generation
clear;
noOfNodes = 64;
R = 100; % maximum range;
L = 450;
minInt = 1;
maxInt = L;
adj_matrix = zeros(noOfNodes,noOfNodes);
dist_matrix = zeros(noOfNodes,noOfNodes);
%rand('state', 0);     %To generate the same random numbers in a session
rand();
figure(1);
clf;
hold on;
netXloc = randi([minInt, maxInt],[1,noOfNodes]);
netYloc = randi([minInt, maxInt],[1,noOfNodes]);
xy_loc_mat = zeros(2,noOfNodes);
xy_loc_mat(1,:) = netXloc;
xy_loc_mat(2,:) = netYloc;
for i = 1:noOfNodes
    plot(netXloc(i), netYloc(i), '.');
    if((i == 1)||(i == noOfNodes))
        plot(netXloc(i), netYloc(i),'O','MarkerEdgeColor','w','MarkerFaceColor','r','MarkerSize', 15);
    end
    text(netXloc(i), netYloc(i), num2str(i));
    for j = 1:noOfNodes
        dist = sqrt((netXloc(i) - netXloc(j))^2 + (netYloc(i) - netYloc(j))^2);
        if dist <= R
            if ( i == j )
                adj_matrix(i,j)=0;
            else
                adj_matrix(i,j) = 1;
                dist_matrix(i,j) = dist; 
				line([netXloc(i) netXloc(j)], [netYloc(i) netYloc(j)], 'Color','g','LineStyle', ':');
            end;
            %line([netXloc(i) netXloc(j)], [netYloc(i) netYloc(j)], 'Color','g','LineStyle', ':');
        end;
    end;
end;
%adj_matrix

%% Energy Cost Matrix Generation and Delay matrix as they are related somewhat inversely
minEnergyCost = 100;
maxEnergyCost = 1000;
energy_cost_matrix = zeros(noOfNodes,noOfNodes);
delay_cost_matrix = zeros(noOfNodes,noOfNodes);
for i = 1:noOfNodes
    for j = 1:noOfNodes
        energy_cost_matrix(i,j) = 9999;
        delay_cost_matrix(i,j) = 9999;
        if( adj_matrix(i,j) == 1)
            sumCost = randi([900, 1800],1);
            energy_cost_matrix(i,j) = randi([minEnergyCost, maxEnergyCost],1);
            delay_cost_matrix(i,j) = sumCost - energy_cost_matrix(i,j);
        end;
    end;
end;

%% Delay Cost Matrix Generation
% minDelayCost = 100;
% maxDelayCost = 1000;
% delay_cost_matrix = zeros(noOfNodes,noOfNodes);
% for i = 1:noOfNodes
%     for j = 1:noOfNodes
%         delay_cost_matrix(i,j) = 9999;
%         if( adj_matrix(i,j) == 1)
%             delay_cost_matrix(i,j) = randi([minDelayCost, maxDelayCost],1);
%         end;
%     end;
% end;

%% Tuning Parameter
tuning_matrix = zeros(noOfNodes,noOfNodes);
for i = 1:noOfNodes
    for j = 1:noOfNodes
        tuning_matrix(i,j) = 0.8;
    end;
end;

%% Write all matrices to files

% fName1 = 'Network_Data.txt';         %# A file name
% fid = fopen(fName1,'w');            %# Open the file
% dlmwrite(fName1,adj_matrix,'-append',...  %# Print the matrix
%          'delimiter','\t',...
%          'newline','pc');
% 
% fName2 = 'Energy_Cost_Data.txt';         %# A file name
% fid = fopen(fName2,'w');                %# Open the file
% dlmwrite(fName2,energy_cost_matrix,'-append',...  %# Print the matrix
%          'delimiter','\t',...
%          'newline','pc');
% 
%      
% fName3 = 'Delay_Cost_Data.txt';         %# A file name
% fid = fopen(fName3,'w');                %# Open the file
% dlmwrite(fName3,delay_cost_matrix,'-append',...  %# Print the matrix
%          'delimiter','\t',...
%          'newline','pc');
% 
%     
% fName4 = 'Tuning_Parameter.txt';         %# A file name
% fid = fopen(fName4,'w');                %# Open the file
% dlmwrite(fName4,tuning_matrix,'-append',...  %# Print the matrix
%          'delimiter','\t',...
%          'newline','pc');
% 
% 
% fName7 = 'Distances_Between_Nodes.txt';         %# A file name
% fid = fopen(fName7,'w');                %# Open the file
% dlmwrite(fName7,dist_matrix,'-append',...  %# Print the matrix
%          'delimiter','\t',...
%          'newline','pc');
% 
% fName8 = 'XY_Loc.txt';         %# A file name
% fid = fopen(fName8,'w');                %# Open the file
% dlmwrite(fName8,xy_loc_mat,'-append',...  %# Print the matrix
%          'delimiter','\t',...
%          'newline','pc');
