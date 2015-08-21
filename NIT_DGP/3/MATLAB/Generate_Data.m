%% Adjacency Matrix and Distance Matrix Generation
clear;
noOfNodes = 60;
R = 30; % maximum range;
L = 100;
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
            
%% Params
% delayPerUnitDist = 1/L;
% delayCostPerUnitDist = L/10;  % To transfer from one node to next some 'time' is required. 
%                             % If dist increases, delay decreases as less hops are encountered
%                             % and less processing is done at those intermediate nodes.
% energyPerUnitDist = 10;
% energyCostPerUnitDist = L/10; %10$ for every 1 k.m.
% exponentially increasing function are used for delay


%% Transmission Cost Matrix Generation
minTrnsCst = 10;
maxTrnsCst = 1000;  %seconds
trns_cst_matrix = zeros(noOfNodes,noOfNodes);
for i = 1:noOfNodes
    for j = 1:noOfNodes
        trns_cst_matrix(i,j) = flintmax;
        if( adj_matrix(i,j) == 1)
            dist = dist_matrix(i,j);
            cost = minTrnsCst + (dist)^(3);
            trns_cst_matrix(i,j) = cost; %randi([minEnergyCost, maxEnergyCost],1);
        end;
    end;
end;        

%% Write all matrices to files

% fName1 = 'Network_Data.txt';         %# A file name
% fid = fopen(fName1,'w');            %# Open the file
% dlmwrite(fName1,adj_matrix,'-append',...  %# Print the matrix
%          'delimiter','\t',...
%          'newline','pc');
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
%      
% fName2 = 'Transmission_Cost_Data.txt';         %# A file name
% fid = fopen(fName2,'w');                %# Open the file
% dlmwrite(fName2,trns_cst_matrix,'-append',...  %# Print the matrix
%          'delimiter','\t',...
%          'newline','pc');




     
% fName3 = 'Delay_Cost_Data.txt';         %# A file name
% fid = fopen(fName3,'w');                %# Open the file
% dlmwrite(fName3,delay_cost_matrix,'-append',...  %# Print the matrix
%          'delimiter','\t',...
%          'newline','pc');
% 
% fName4 = 'Energy_Actual_Data.txt';         %# A file name
% fid = fopen(fName4,'w');                %# Open the file
% dlmwrite(fName4,energy_act_matrix,'-append',...  %# Print the matrix
%          'delimiter','\t',...
%          'newline','pc');
%     
% fName5 = 'Delay_Actual_Data.txt';         %# A file name
% fid = fopen(fName5,'w');                %# Open the file
% dlmwrite(fName5,delay_act_matrix,'-append',...  %# Print the matrix
%          'delimiter','\t',...
%          'newline','pc');
%     
% fName6 = 'Tuning_Parameter.txt';         %# A file name
% fid = fopen(fName6,'w');                %# Open the file
% dlmwrite(fName6,tuning_matrix,'-append',...  %# Print the matrix
%          'delimiter','\t',...
%          'newline','pc');