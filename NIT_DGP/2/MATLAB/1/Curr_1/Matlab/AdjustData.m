clear;
adjMat = load('Network_Data.txt');
dist_matrix = load('Distances_Between_Nodes.txt');
noOfNodes = length(adjMat);

delayCostPerUnitDist = 7;
minDelay = 10;
maxDelay = 1e7;  %seconds
delay_act_matrix = zeros(noOfNodes,noOfNodes);
delay_cost_matrix = zeros(noOfNodes,noOfNodes);
for i = 1:noOfNodes
    for j = 1:noOfNodes
        delay_act_matrix(i,j) = flintmax;
        delay_cost_matrix(i,j) = flintmax;
        if( adjMat(i,j) == 1)
            %dist = sqrt(((netXloc(i) - netXloc(j))^2) + ((netYloc(i) - netYloc(j))^2));
            %delayReq = minDelay + dist*delayPerUnitDist;    %route with more no of hops are being preferred in this case
            dist = dist_matrix(i,j);
            %opposite of energy, hence if one increased , the other will decrease and conflict occurs
            delayReq = maxDelay - (dist)^(2.5); % minDelay + (dist)^(-2); %maxDelay - exp(-(dist));
            delay_act_matrix(i,j) = delayReq; %randi([minEnergyCost, maxEnergyCost],1);
            delay_cost_matrix(i,j) = (delayReq-(dist)^(0.5))*delayCostPerUnitDist; %delayReq*(exp(-dist));%besi delay mane besi khoroch for video/VOIP etc(delay sensitive)
            % as dist increases , delay decreases
        end;
    end;
end;


fName1 = 'Delay_Actual_Data.txt';         %# A file name
fid = fopen(fName1,'w');                %# Open the file
dlmwrite(fName1,delay_act_matrix,'-append',...  %# Print the matrix
         'delimiter','\t',...
         'newline','pc');
     
fName2 = 'Delay_Cost_Data.txt';         %# A file name
fid = fopen(fName2,'w');                %# Open the file
dlmwrite(fName2,delay_cost_matrix,'-append',...  %# Print the matrix
         'delimiter','\t',...
         'newline','pc');