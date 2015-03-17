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
%count=0;
%temp_Nbrs=zeros(1,noOfNodes);
%arr_Nbrs=cell(1,noOfNodes);
for i = 1:noOfNodes
    plot(netXloc(i), netYloc(i), '.');
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
            %temp_Nbrs(j)=j; % isNbr;
            %count=count+1;
            %line([netXloc(i) netXloc(j)], [netYloc(i) netYloc(j)], 'Color','g','LineStyle', ':');
        end;
    end;
end;
%adj_matrix

% %% Distance Matrix
% dist_matrix = zeros(noOfNodes,noOfNodes);
% for i = 1:noOfNodes
%     for j = 1:noOfNodes
%         % dist_matrix(i,j) = 0;
%         if( adj_matrix(i,j) == 1)
%             dist = sqrt(((netXloc(i) - netXloc(j))^2) + ((netYloc(i) - netYloc(j))^2));
%             dist_matrix(i,j) = dist;
%         end;
%     end;
% end;
            
%% Params
delayPerUnitDist = 1/L;
delayCostPerUnitDist = L/10;  % To transfer from one node to next some 'time' is required. 
                            % If dist increases, delay decreases as less hops are encountered
                            % and less processing is done at those intermediate nodes.
energyPerUnitDist = 10;
energyCostPerUnitDist = L/10; %10$ for every 1 k.m.
% exponentially increasing function are used for delay


%% Actual Delay Matrix Generation
minDelay = 10;
maxDelay = 1000;  %seconds
delay_act_matrix = zeros(noOfNodes,noOfNodes);
delay_cost_matrix = zeros(noOfNodes,noOfNodes);
for i = 1:noOfNodes
    for j = 1:noOfNodes
        delay_act_matrix(i,j) = flintmax;
        delay_cost_matrix(i,j) = flintmax;
        if( adj_matrix(i,j) == 1)
            %dist = sqrt(((netXloc(i) - netXloc(j))^2) + ((netYloc(i) - netYloc(j))^2));
            %delayReq = minDelay + dist*delayPerUnitDist;    %route with more no of hops are being preferred in this case
            dist = dist_matrix(i,j);
            %opposite of energy, hence if one increased , the other will decrease and conflict occurs
            delayReq = minDelay + (dist)^(-2); %maxDelay - exp(-(dist));
            delay_act_matrix(i,j) = delayReq; %randi([minEnergyCost, maxEnergyCost],1);
            delay_cost_matrix(i,j) = (delayReq + ((dist)^(-3)))*delayCostPerUnitDist; %delayReq*(exp(-dist));%besi delay mane besi khoroch for video/VOIP etc(delay sensitive)
            % as dist increases , delay decreases
        end;
    end;
end;

%% Actual Energy Matrix Generation
minEnergy = 100;
%maxEnergyCost = 1000;
energy_act_matrix = zeros(noOfNodes,noOfNodes);
energy_cost_matrix = zeros(noOfNodes,noOfNodes);
for i = 1:noOfNodes
    for j = 1:noOfNodes
        energy_act_matrix(i,j) = flintmax;
        energy_cost_matrix(i,j) = flintmax;
        if( adj_matrix(i,j) == 1)
            %dist = sqrt(((netXloc(i) - netXloc(j))^2) + ((netYloc(i) - netYloc(j))^2));
            dist = dist_matrix(i,j);
            energyReq = minEnergy + (dist)^(3);   %(exp(dist)/(2.^dist));    %dist*((1.5).^dist);      %dist*energyCostPerUnitDist;   %randi([900, 1800],1);
            energy_act_matrix(i,j) = energyReq; %randi([minEnergyCost, maxEnergyCost],1);
            energy_cost_matrix(i,j) = (energyReq + ((1.5).^dist))*energyCostPerUnitDist;
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

fName1 = 'Network_Data.txt';         %# A file name
fid = fopen(fName1,'w');            %# Open the file
dlmwrite(fName1,adj_matrix,'-append',...  %# Print the matrix
         'delimiter','\t',...
         'newline','pc');
     
fName2 = 'Energy_Cost_Data.txt';         %# A file name
fid = fopen(fName2,'w');                %# Open the file
dlmwrite(fName2,energy_cost_matrix,'-append',...  %# Print the matrix
         'delimiter','\t',...
         'newline','pc');
    
fName3 = 'Delay_Cost_Data.txt';         %# A file name
fid = fopen(fName3,'w');                %# Open the file
dlmwrite(fName3,delay_cost_matrix,'-append',...  %# Print the matrix
         'delimiter','\t',...
         'newline','pc');

fName4 = 'Energy_Actual_Data.txt';         %# A file name
fid = fopen(fName4,'w');                %# Open the file
dlmwrite(fName4,energy_act_matrix,'-append',...  %# Print the matrix
         'delimiter','\t',...
         'newline','pc');
    
fName5 = 'Delay_Actual_Data.txt';         %# A file name
fid = fopen(fName5,'w');                %# Open the file
dlmwrite(fName5,delay_act_matrix,'-append',...  %# Print the matrix
         'delimiter','\t',...
         'newline','pc');
    
fName6 = 'Tuning_Parameter.txt';         %# A file name
fid = fopen(fName6,'w');                %# Open the file
dlmwrite(fName6,tuning_matrix,'-append',...  %# Print the matrix
         'delimiter','\t',...
         'newline','pc');
     
fName7 = 'Distances_Between_Nodes.txt';         %# A file name
fid = fopen(fName7,'w');                %# Open the file
dlmwrite(fName7,dist_matrix,'-append',...  %# Print the matrix
         'delimiter','\t',...
         'newline','pc');

fName8 = 'XY_Loc.txt';         %# A file name
fid = fopen(fName8,'w');                %# Open the file
dlmwrite(fName8,xy_loc_mat,'-append',...  %# Print the matrix
         'delimiter','\t',...
         'newline','pc');
