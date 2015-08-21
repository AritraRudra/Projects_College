%% Adjacency Matrix Generation
clear;
noOfNodes = 50;
R = 25; % maximum range;
L = 100;
minInt = 1;
maxInt = L;
adj_matrix=zeros(noOfNodes,noOfNodes);
%rand('state', 0);     %To generate the same random numbers in a session
rand();
figure(1);
clf;
hold on;
netXloc = randi([minInt, maxInt],[1,noOfNodes]);
netYloc = randi([minInt, maxInt],[1,noOfNodes]);
%count=0;
%temp_Nbrs=zeros(1,noOfNodes);
%arr_Nbrs=cell(1,noOfNodes);
for i = 1:noOfNodes
    plot(netXloc(i), netYloc(i), '.');
    %plot(netXloc(i), netYloc(i), '.','MarkerEdgeColor','k','MarkerFaceColor','w','MarkerSize', 50,'Linewidth', 2);
    text(netXloc(i), netYloc(i), num2str(i));
    for j = 1:noOfNodes
        distance = sqrt((netXloc(i) - netXloc(j))^2 + (netYloc(i) - netYloc(j))^2);
        if distance <= R
            if ( i == j )
                adj_matrix(i,j)=0;
            else
                adj_matrix(i,j)=1;
				line([netXloc(i) netXloc(j)], [netYloc(i) netYloc(j)], 'Color','g','LineStyle', ':');
                %line([netXloc(i) netXloc(j)], [netYloc(i) netYloc(j)], 'Color','g','LineWidth',1,'Color','g','LineStyle', ':');
            end;
            %temp_Nbrs(j)=j; % isNbr;
            %count=count+1;
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

%% Actual Delay-Energy Matrix Generation
minDelay = 5;   % second
maxDelay = 60;
delay_matrix = zeros(noOfNodes,noOfNodes);
energy_matrix = zeros(noOfNodes,noOfNodes);
for i = 1:noOfNodes
    for j = 1:noOfNodes
        delay_matrix(i,j) = 180;
		energy_matrix(i,j) = 600;
        if( adj_matrix(i,j) == 1)
            sumAct = randi([300, 600],1);
            delay_matrix(i,j) = randi([minDelay, maxDelay],1);
            energy_matrix(i,j) = sumAct - delay_matrix(i,j);
        end;
    end;
end;

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
% fName3 = 'Delay_Cost_Data.txt';         %# A file name
% fid = fopen(fName3,'w');                %# Open the file
% dlmwrite(fName3,delay_cost_matrix,'-append',...  %# Print the matrix
%          'delimiter','\t',...
%          'newline','pc');
% 
% fName4 = 'Energy_Actual_Data.txt';         %# A file name
% fid = fopen(fName4,'w');                %# Open the file
% dlmwrite(fName4,energy_matrix,'-append',...  %# Print the matrix
%          'delimiter','\t',...
%          'newline','pc');
%     
% fName5 = 'Delay_Actual_Data.txt';         %# A file name
% fid = fopen(fName5,'w');                %# Open the file
% dlmwrite(fName5,delay_matrix,'-append',...  %# Print the matrix
%          'delimiter','\t',...
%          'newline','pc');
%     
% fName6 = 'Tuning_Parameter.txt';         %# A file name
% fid = fopen(fName6,'w');                %# Open the file
% dlmwrite(fName6,tuning_matrix,'-append',...  %# Print the matrix
%          'delimiter','\t',...
%          'newline','pc');