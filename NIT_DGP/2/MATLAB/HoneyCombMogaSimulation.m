function HoneyCombMogaSimulation()

    % Initialize base params
    clear;
    clc;
    radiusLim=20;
    NodeWeightConsumption=0.03;
    EdgeWeightConsumption=0.05;
    NumberOfRequests=1;
    NodeCapacityLevel=0.7;      %pass values between 0.1 to 0.9
    ChannelCapacityLevel=0.8;       %pass values between 0.1 to 0.9
    
    treeCell=generateTreeCell(radiusLim);
    cellfun(@(x) strcat(num2str(x(1,1)),'-',num2str(x(1,2))),treeCell,'UniformOutput',false)
    size(treeCell)
    SchedulerTable= generateScheduleRequest(NumberOfRequests, radiusLim);
    startProcess(radiusLim,treeCell,NodeWeightConsumption,EdgeWeightConsumption,NumberOfRequests,NodeCapacityLevel,ChannelCapacityLevel,SchedulerTable);
    
%     for index1=1:9
%         failurePercent(index1) = startProcess(radiusLim,treeCell,NodeWeightConsumption,EdgeWeightConsumption,NumberOfRequests,NodeCapacityLevel,ChannelCapacityLevel,SchedulerTable);
%     end
%     failurePercent'
end

%% start the simulation by looping through each request and scheduling it
function failurePercent=startProcess(radiusLim,treeCell,NodeWeightConsumption,EdgeWeightConsumption,NumberOfRequests,NodeCapacityLevel,LinkCapacityLevel,SchedulerTable)

    % Generate random node weight and edge weight
    [NodeWeight UplinkEdgeWeight DownlinkEdgeWeight]=generateWeights(radiusLim, treeCell, NodeCapacityLevel, LinkCapacityLevel);

    tic
    % perform scheduling
    FailureCounter=0;
    for index0=1:NumberOfRequests
        index0
        [isCapacityExceded PathArray1 PathArray2 NodeWeight UplinkEdgeWeight DownlinkEdgeWeight] = scheduleRequest(SchedulerTable(index0,2),SchedulerTable(index0,3),SchedulerTable(index0,5),SchedulerTable(index0,6),treeCell,NodeWeight,UplinkEdgeWeight,DownlinkEdgeWeight,NodeWeightConsumption,EdgeWeightConsumption);
        
        % hardcoded value
        %[isCapacityExceded PathArray1 PathArray2 NodeWeight UplinkEdgeWeight DownlinkEdgeWeight] = scheduleRequest(16,34,1,3,treeCell,NodeWeight,UplinkEdgeWeight,DownlinkEdgeWeight,NodeWeightConsumption,EdgeWeightConsumption);
        
        %PathArrayAsString = cellfun(@(x) strcat(num2str(x(1,1)),'-',num2str(x(1,2))),PathArray1,'UniformOutput',false);
        %PathArrayAsString

        %PathArrayAsString = cellfun(@(x) strcat(num2str(x(1,1)),'-',num2str(x(1,2))),PathArray2,'UniformOutput',false);
        %PathArrayAsString
        if isCapacityExceded==1
            FailureCounter=FailureCounter+1;
        end
        SchedulerTable(index0,7)=isCapacityExceded;
        SchedulerTable(index0,7)
    end
    failurePercent=FailureCounter*100/NumberOfRequests;
    failurePercent
    toc
    %strcat(num2str(FailureCounter*100/NumberOfRequests),'% failed')
    
end

%% Nested Functions
function [radialIndex, dispIndex] = getDirectConnection1ForRadialNode(radialIndex, dispIndex)
    spoke=floor((dispIndex-1)/radialIndex);
    if(dispIndex+spoke-1<=0)
        dispIndex=6*(radialIndex+1);
    else
        dispIndex=dispIndex+spoke-1;
    end
    radialIndex=radialIndex+1;
end
function [radialIndex, dispIndex] = getDirectConnection2ForRadialNode(radialIndex, dispIndex)
    spoke=floor((dispIndex-1)/radialIndex);
    radialIndex=radialIndex+1;
    dispIndex=dispIndex+spoke;
end
function [radialIndex, dispIndex] = getDirectConnection3ForRadialNode(radialIndex, dispIndex)
    spoke=floor((dispIndex-1)/radialIndex);
    radialIndex=radialIndex+1;
    dispIndex=dispIndex+spoke+1;
end
function [radialIndex, dispIndex] = getInDirectConnection1ForRadialNode(radialIndex, dispIndex)
    if(dispIndex-radialIndex<=0)
        [radialIndex, dispIndex] = getDirectConnection1ForRadialNode(radialIndex, (5*radialIndex)+1);
    else
        [radialIndex, dispIndex] = getDirectConnection1ForRadialNode(radialIndex, dispIndex-radialIndex);
    end
end
function [radialIndex, dispIndex] = getInDirectConnection2ForRadialNode(radialIndex, dispIndex)
    if(dispIndex+radialIndex>6*radialIndex)
        [radialIndex, dispIndex] = getDirectConnection3ForRadialNode(radialIndex, 1);
    else
        [radialIndex, dispIndex] = getDirectConnection3ForRadialNode(radialIndex, dispIndex+radialIndex);
    end
end
function [radialIndex, dispIndex] = getDirectConnection1ForNonRadialNode(radialIndex, dispIndex)
    spoke=floor((dispIndex-1)/radialIndex);
    radialIndex=radialIndex+1;
    dispIndex=dispIndex+spoke;
end
function [radialIndex, dispIndex] = getDirectConnection2ForNonRadialNode(radialIndex, dispIndex)
    spoke=floor((dispIndex-1)/radialIndex);
    radialIndex=radialIndex+1;
    dispIndex=dispIndex+spoke+1;
end
function [radialIndex, dispIndex] = getInDirectConnection1ForNonRadialNode(radialIndex, dispIndex)
    spoke=floor((dispIndex-1)/radialIndex);
    [radialIndex, dispIndex] = getDirectConnection1ForRadialNode(radialIndex, (radialIndex*spoke)+1);
end
function [radialIndex, dispIndex] = getInDirectConnection2ForNonRadialNode(radialIndex, dispIndex)
    spoke=floor((dispIndex-1)/radialIndex);
    if(spoke+1>5)
        [radialIndex, dispIndex] = getDirectConnection3ForRadialNode(radialIndex, 1);
    else
        [radialIndex, dispIndex] = getDirectConnection3ForRadialNode(radialIndex, (spoke+1)*radialIndex +1);
    end
end

%% Obtain best routing for a request using several iterations of MOGA
function [isCapacityExceded MinimalPathArray1 MinimalPathArray2 NodeWeight UplinkEdgeWeight DownlinkEdgeWeight] = scheduleRequest(radialSrc, dispSrc, radialDest, dispDest, treeCell, NodeWeight, UplinkEdgeWeight, DownlinkEdgeWeight, NodeWeightConsumption, EdgeWeightConsumption)
    isCapacityExceded=0;
    populationSize=40;
    numberOfIteration=15;
    MinimalPathArray1={};
    MinimalPathArray2={};
    ApproximationArchive=[];

    [isGenerationPossible1 PathArray1 PathNodeValue1 PathEdgeValue1]=findInitialGenerationOfPaths(radialSrc, dispSrc, treeCell, NodeWeight, UplinkEdgeWeight, populationSize, NodeWeightConsumption, EdgeWeightConsumption);
    if isGenerationPossible1==0
        isCapacityExceded=1;
        return;
    end
    [x_lim y_lim]=size(PathArray1);
    if x_lim<populationSize
        if x_lim>10
            tempVar=uint8(x_lim/2);
            populationSize = tempVar*2;
        else
            return;
        end
    end
    %cellfun(@(x) strcat(num2str(x(1,1)),'-',num2str(x(1,2))),PathArray1,'UniformOutput',false)
    [CurrentCostArray1]=calculatePathCosts(PathArray1, PathNodeValue1, PathEdgeValue1);
    for generationIndex=1:numberOfIteration
        [NodeWeight UplinkEdgeWeight CurrentCostArray1]=fastNonDominatedSort(PathArray1, PathNodeValue1, PathEdgeValue1, treeCell, NodeWeight, UplinkEdgeWeight, NodeWeightConsumption, EdgeWeightConsumption, CurrentCostArray1);
        figure;
        scatter(CurrentCostArray1(:,3),CurrentCostArray1(:,6),CurrentCostArray1(:,7)+100,CurrentCostArray1(:,9),'d','fill');
        title('Pareto-optimal routes obtained on using MOGA on scheduling requests');
        xlabel('mean value of constraints');
        ylabel('mean deviation in constraints');

        [PathArray1 PathNodeValue1 PathEdgeValue1 CurrentCostArray1] = createNextGeneration(PathArray1, PathNodeValue1, PathEdgeValue1, CurrentCostArray1, treeCell, NodeWeight, UplinkEdgeWeight, populationSize);
        firstParetoFrontIndex=find(CurrentCostArray1(:,9)==1);
        GenerationMatrix(generationIndex, 1:3)=[generationIndex mean(CurrentCostArray1(firstParetoFrontIndex,3)) mean(CurrentCostArray1(firstParetoFrontIndex,6))];
        
        % add to ApproximationArchive
        [ff_xlim ff_ylim]=size(firstParetoFrontIndex);
        for ffindex=1:ff_xlim
            aaDominated=0;
            [aa_xlim aa_ylim]=size(ApproximationArchive);
            for aaindex=1:aa_xlim
                if ApproximationArchive(aaindex,3)==1
                    if ApproximationArchive(aaindex,1)>CurrentCostArray1(ffindex,3) && ApproximationArchive(aaindex,2)>CurrentCostArray1(ffindex,6)
                        ApproximationArchive(ffindex,3)=0;
                    end
                    if ApproximationArchive(aaindex,1)<=CurrentCostArray1(ffindex,3) && ApproximationArchive(aaindex,2)<=CurrentCostArray1(ffindex,6)
                        aaDominated=1;
                    end
                end
            end
            if aaDominated == 0
                ApproximationArchive(aa_xlim+1,1)=CurrentCostArray1(ffindex,3);
                ApproximationArchive(aa_xlim+1,2)=CurrentCostArray1(ffindex,6);
                ApproximationArchive(aa_xlim+1,3)=1;
            end
            validApproximationArchiveIndex=find(ApproximationArchive(:,3)==1);
            ApproximationArchive=ApproximationArchive(validApproximationArchiveIndex,:);
            
        end
        
        % measure approximation of population
        ApproximationValue=[];
        ApproximationCount=0;
        [aa_xlim aa_ylim]=size(ApproximationArchive);
        for aaindex=1:aa_xlim
            val1=99999;
            [ff_xlim ff_ylim]=size(CurrentCostArray1);
            for ffindex=1:ff_xlim
                val2=-99999;
                val2=max(val2, (ApproximationArchive(aaindex,1) - CurrentCostArray1(ffindex,3)));
                val2=max(val2, (ApproximationArchive(aaindex,2) - CurrentCostArray1(ffindex,6)));
                val1=min(val1, val2);
            end
            ApproximationCount=ApproximationCount+1;
            ApproximationValue(ApproximationCount)=val1;
        end
        GenerationMatrix(generationIndex, 4) = mean(ApproximationValue);
    
        % calculate hypervolume
        GenerationMatrix(generationIndex, 5) = 10*calculateHyperVolume(CurrentCostArray1(firstParetoFrontIndex,:), [1 0.1]);
    end
    
    [NodeWeight UplinkEdgeWeight]=consumeNodeAndChannelCapacity(PathArray1(1,:), treeCell, NodeWeight, UplinkEdgeWeight, NodeWeightConsumption, EdgeWeightConsumption);
    MinimalPathArray1 = PathArray1(1,:);
    MinimalPathArray2 = PathArray1(1,:);
    
    figure;
    plotyy(GenerationMatrix(:,1),GenerationMatrix(:,2),GenerationMatrix(:,1),GenerationMatrix(:,3));
    figure;
    plotyy(GenerationMatrix(:,1),GenerationMatrix(:,4),GenerationMatrix(:,1),GenerationMatrix(:,5));
    
%     [PathArray2 PathNodeValue2 PathEdgeValue2]=findInitialGenerationOfPaths(radialDest, dispDest, treeCell, NodeWeight, DownlinkEdgeWeight,populationSize);
%     cellfun(@(x) strcat(num2str(x(1,1)),'-',num2str(x(1,2))),PathArray2,'UniformOutput',false)
%     [isCapacityExceded2 MinimalPathArray2 MinimalPathNodeValue2 MinimalPathEdgeValue2 NodeWeight DownlinkEdgeWeight CurrentCostArray2]=calculatePathCosts(PathArray2, PathNodeValue2, PathEdgeValue2, treeCell, NodeWeight, DownlinkEdgeWeight, NodeWeightConsumption, EdgeWeightConsumption);
%     
%     isCapacityExceded = isCapacityExceded1||isCapacityExceded2;
end

%% find a set of possible routes which do not violate the constraints
function [pathCount PathArray PathNodeValue PathEdgeValue] = findInitialGenerationOfPaths(radialVal, displacementVal, treeCell, NodeWeight, EdgeWeight, PopulationSize,  NodeWeightConsumption, EdgeWeightConsumption)
    PathArray={};
    PathNodeValue=[];
    PathEdgeValue=[];
    pathCount=0;
    
    while pathCount<PopulationSize
        numberOfTries=0;
        while numberOfTries<5
            numberOfTries=numberOfTries+1;
            [PathArrayRandom PathNodeValueRandom PathEdgeValueRandom]=findInitialRandomPath(radialVal, displacementVal, treeCell, NodeWeight, EdgeWeight);
            isCapacityExceded = calculatePathValidity(PathArrayRandom, treeCell, NodeWeight, EdgeWeight, NodeWeightConsumption, EdgeWeightConsumption);
            doExist = 0; %findRepeatationOfPaths(PathArray, PathArrayRandom);
        
            if isCapacityExceded ==0 && doExist==0
                pathCount=pathCount+1;
                PathArray(pathCount,:)=PathArrayRandom;
                PathNodeValue(pathCount,:)=PathNodeValueRandom;
                PathEdgeValue(pathCount,:)=PathEdgeValueRandom;
                continue;
            end
        end
    end
end

%% find repeatation of paths in existing array
function doExist = findRepeatationOfPaths(PathArray, PathArrayRandom)
    doExist=0;
    [x_lim y_lim] = size(PathArray);
    for index1=1:x_lim
        isEqual=1;
        for index2=1:y_lim
            if PathArrayRandom{1,index2}(1,2) ~= PathArray{index1,index2}(1,2) || PathArrayRandom{1,index2}(1,1) ~= PathArray{index1,index2}(1,1)
                isEqual=0;
                break;
            end
        end
        if isEqual==1
            doExist=1;
            break;
        end
    end
end

%% recursive function to greedily find a possible route in the Network
function [PathArray PathNodeValue PathEdgeValue] = findInitialRandomPath(radialVal, displacementVal, treeCell, NodeWeight, EdgeWeight)
    PathArray={};
    PathNodeValue=[];
    PathEdgeValue=[];
    thisElement{1,1}=[radialVal displacementVal];
    if(radialVal ==1)
        PathArray{1,1}=[radialVal displacementVal];
        PathArray{1,2}=[0 0];
        PathNodeValue(1,2)=NodeWeight{1,4};
        PathNodeValue(1,1)=NodeWeight{displacementVal+1,4};
        PathEdgeValue(1,1)=EdgeWeight(1,displacementVal);
    else
        [x_lim_treeCell y_lim_treeCell]=size(treeCell);
        range=(radialVal*(radialVal-1)*3+1);
        index3=1;
        for index1=range-(6*(radialVal-1)):range
            for index2=2:y_lim_treeCell
                if(treeCell{index1,index2}(1,1)==radialVal && treeCell{index1,index2}(1,2)==displacementVal)
                    tempMatrixForRandomSelection(index3,:)=[treeCell{index1,1}(1,1) treeCell{index1,1}(1,2) index1 index2];
                    index3=index3+1;
                end
            end
        end
        [tempLim_x tempLim_y]=size(tempMatrixForRandomSelection);
        randomElement=randi(tempLim_x);
                    
        [SubPathArray SubPathNodeValue SubPathEdgeValue] = findInitialRandomPath(tempMatrixForRandomSelection(randomElement,1),tempMatrixForRandomSelection(randomElement,2), treeCell, NodeWeight, EdgeWeight);
        [sub_x_lim sub_y_lim]= size(SubPathArray);
        [x_lim y_lim]= size(PathArray);

        PathArray(x_lim+1:x_lim+sub_x_lim,2:sub_y_lim+1)=SubPathArray;
        ColumnSection = repmat(thisElement,sub_x_lim,1);
        PathArray(x_lim+1:x_lim+sub_x_lim,1:1)=ColumnSection;

        [sub_x_lim sub_y_lim]= size(SubPathNodeValue);
        [x_lim y_lim]= size(PathNodeValue);
        PathNodeValue(x_lim+1:x_lim+sub_x_lim,2:sub_y_lim+1)=SubPathNodeValue;
        ColumnSection = repmat(NodeWeight{3*radialVal*(radialVal-1)+displacementVal+1,4},sub_x_lim,1);
        PathNodeValue(x_lim+1:x_lim+sub_x_lim,1:1)=ColumnSection;

        [sub_x_lim sub_y_lim]= size(SubPathEdgeValue);
        [x_lim y_lim]= size(PathEdgeValue);
        PathEdgeValue(x_lim+1:x_lim+sub_x_lim,2:sub_y_lim+1)=SubPathEdgeValue;
        ColumnSection = repmat(EdgeWeight(tempMatrixForRandomSelection(randomElement,3),tempMatrixForRandomSelection(randomElement,4)),sub_x_lim,1);
        PathEdgeValue(x_lim+1:x_lim+sub_x_lim,1:1)=ColumnSection;
    end
end

%% function to check the validity of the path as per constraints
function isCapacityExceded=calculatePathValidity(PathArray, treeCell, NodeWeight, EdgeWeight, NodeWeightConsumption, EdgeWeightConsumption)
    isCapacityExceded=0;
    [x_lim y_lim] = size(PathArray);
    for index1=1:x_lim
        isCapacityExceded=0;
        
        %Consume Node Capacity
        [x_lim_1 y_lim_1]=size(NodeWeight);
        for index2=y_lim:-1:1
            search_r = PathArray{index1,index2}(1,1);
            search_d = PathArray{index1,index2}(1,2);
            for index3 = 1:x_lim_1
                if(NodeWeight{index3,1}==search_r && NodeWeight{index3,2}==search_d)
                    if(NodeWeight{index3,4}<NodeWeightConsumption)
                       isCapacityExceded=1;
                    end
                end
                if(isCapacityExceded==1)
                    break;
                end
            end
        end
        %Consume Edge Capacity
        [x_lim_1 y_lim_1]=size(EdgeWeight);
        for index2=y_lim:-1:2 
           search_r = PathArray{index1,index2}(1,1);
           search_d = PathArray{index1,index2}(1,2); 
           sequentialIndex=3*search_r*(search_r -1)+search_d+1;
           for index3=1:6
               if(treeCell{sequentialIndex,index3}(1,1)==search_r && treeCell{sequentialIndex,index3}(1,2)==search_d)
                   if(EdgeWeight(sequentialIndex,index3)<EdgeWeightConsumption)
                       isCapacityExceded=1;
                   end
               end
           end
           if (isCapacityExceded==1)
               break;
           end
        end
    end
end

%% function to consume node and channel capacity after every successful routing
function [NodeWeight EdgeWeight]=consumeNodeAndChannelCapacity(PathArray, treeCell, NodeWeight, EdgeWeight, NodeWeightConsumption, EdgeWeightConsumption)
    [x_lim y_lim] = size(PathArray);
    for index1=1:x_lim
        isCapacityExceded=0;
        
        [x_lim_1 y_lim_1]=size(NodeWeight);
        for index2=y_lim:-1:1
            search_r = PathArray{index1,index2}(1,1);
            search_d = PathArray{index1,index2}(1,2);
            for index3 = 1:x_lim_1
                if(NodeWeight{index3,1}==search_r && NodeWeight{index3,2}==search_d)
                    if(NodeWeight{index3,4}>NodeWeightConsumption)
                       NodeWeight{index3,4}=NodeWeight{index3,4}-NodeWeightConsumption;
                    end
                end
            end
        end
        for index2=y_lim:-1:2 
           search_r = PathArray{index1,index2}(1,1);
           search_d = PathArray{index1,index2}(1,2); 
           sequentialIndex=3*search_r*(search_r -1)+search_d+1;
           for index3=1:6
               if(treeCell{sequentialIndex,index3}(1,1)==search_r && treeCell{sequentialIndex,index3}(1,2)==search_d)
                   if(EdgeWeight(sequentialIndex,index3)>EdgeWeightConsumption)
                       EdgeWeight(sequentialIndex,index3)=EdgeWeight(sequentialIndex,index3) - EdgeWeightConsumption;
                   end
               end
           end
        end
    end
end

%% calculate the Multi Objective cost corresponding to the Path
function [CurrentCostArray]=calculatePathCosts(PathArray, PathNodeValue, PathEdgeValue)        
    [x_lim y_lim] = size(PathArray);
    CurrentCostArray=zeros(x_lim,9);
    for index1=1:x_lim
        % calculate the objective functions for evaluation of the candidate paths
        MeanOfNodeFactor=mean(PathNodeValue(index1,:));
        MeanOfEdgeFactor=mean(PathEdgeValue(index1,:));
        FirstObjective=(MeanOfNodeFactor+MeanOfEdgeFactor)/2;
        STDofNodeFactor=std(PathNodeValue(index1,:));
        STDofEdgeFactor=std(PathEdgeValue(index1,:));
        SecondObjective=(STDofNodeFactor+STDofEdgeFactor)/2;
        CurrentCostArray(index1,1:6) = [MeanOfNodeFactor MeanOfEdgeFactor FirstObjective STDofNodeFactor STDofEdgeFactor SecondObjective];
    end    
end
    
function [NodeWeight EdgeWeight CurrentCostArray]=fastNonDominatedSort(PathArray, PathNodeValue, PathEdgeValue, treeCell, NodeWeight, EdgeWeight, NodeWeightConsumption, EdgeWeightConsumption, CurrentCostArray) 
    % Fast Non-Dominated Sort on CurrentCostArray
    % Considering minimization of 2 objective functions
    [x_lim y_lim] = size(CurrentCostArray);
    CurrentCostArray(:,7:9)=zeros(x_lim,3);
    front(1,1)=java.util.HashSet;
    for index1=1:x_lim
        CurrentCostArray(index1,7) = 0;
        CurrentCostArray(index1,8) = 0;
        CurrentCostArray(index1,9) = 0;
        s_p(index1,1)=java.util.HashSet;
        for index2=1:x_lim
            if (CurrentCostArray(index1,3)<CurrentCostArray(index2,3) && CurrentCostArray(index1,6)<CurrentCostArray(index2,6))
                s_p(index1,1).add(index2);
            end
            if (CurrentCostArray(index1,3)>CurrentCostArray(index2,3) && CurrentCostArray(index1,6)>CurrentCostArray(index2,6))
                CurrentCostArray(index1,8)=CurrentCostArray(index1,8)+1  ;
            end
        end
        if CurrentCostArray(index1,8)==0
            CurrentCostArray(index1,9)=1;
            front(1,1).add(index1);
        end
    end
    
    frontIndex=1;
    while front(frontIndex,1).size>0
        Q=java.util.HashSet;
        s_p_index=1;
        for index1 = 1:x_lim
            if CurrentCostArray(index1,9) == frontIndex
                for index2=1:x_lim
                    if s_p(index1,1).contains(index2)
                        CurrentCostArray(index2,8)=CurrentCostArray(index2,8) -1;
                        if CurrentCostArray(index2,8)==0
                            CurrentCostArray(index2,9)=frontIndex+1;
                            Q.add(index2);
                        end
                    end
                end
            end
        end
        frontIndex=frontIndex+1;
        front(frontIndex,1)=Q;
    end
    % End of Fast Non-Dominated Sort
end

function [PathArray PathNodeValue PathEdgeValue CurrentCostArray] = createNextGeneration(PathArray, PathNodeValue, PathEdgeValue, CurrentCostArray, treeCell, NodeWeight, EdgeWeight, populationSize)
    crossoverSize=floor(populationSize/2);
    % sort all the matrices following the pareto front
    [CurrentCostArray, idx] = sortrows(CurrentCostArray,9);
    PathArray=PathArray(idx,:);
    PathNodeValue=PathNodeValue(idx,:);
    PathEdgeValue=PathEdgeValue(idx,:);
    % create random pair between better half of population
    randomOrder=randsample(1:crossoverSize,crossoverSize);
    for index1=1:crossoverSize
        if CurrentCostArray(randomOrder(index1),7)==0
            for index2=index1+1:crossoverSize
                if CurrentCostArray(randomOrder(index1),7)==0
                    % check if crossover possible
                    crossoverPoint=0;
                    crossoverPoint=checkCrossoverPossibility(PathArray(randomOrder(index1),:),PathArray(randomOrder(index2),:),treeCell);
                    if(crossoverPoint==0)
                        continue;
                    else
                        % crossover possible, book the parents and perform crossover
                        CurrentCostArray(randomOrder(index1),7)=1;
                        CurrentCostArray(randomOrder(index2),7)=1;
                        [PathArray PathNodeValue PathEdgeValue CurrentCostArray]=performCrossover(PathArray, PathNodeValue, PathEdgeValue, CurrentCostArray, treeCell, randomOrder(index1), randomOrder(index2),crossoverPoint, crossoverSize);
                    end
                end
            end
        end
    end
    for index1=1:crossoverSize
        if CurrentCostArray(randomOrder(index1),7)==0
            % check if mutation possible
            MutationMatrix=checkMutationPossibility(PathArray(randomOrder(index1),:),treeCell);
            [PathArray PathNodeValue PathEdgeValue CurrentCostArray]=performSinglePointMutation(PathArray, PathNodeValue, PathEdgeValue, CurrentCostArray, treeCell, MutationMatrix, index1);
        end
    end
end

function [PathArray PathNodeValue PathEdgeValue CurrentCostArray]=performSinglePointMutation(PathArray, PathNodeValue, PathEdgeValue, CurrentCostArray, treeCell, MutationMatrix, index)
    
    [x_lim, y_lim] = size(MutationMatrix);
    randomOrder = randsample(1:x_lim,x_lim);
    mutationPoint = MutationMatrix(randomOrder(1),1);
    
    [x_lim y_lim]=size(PathArray);
    
%     PathArrayChild1=[PathArray(index1,1:crossoverPoint),PathArray(index2,crossoverPoint+1:y_lim)];
%     PathNodeValueChild1=[PathNodeValue(index1,1:crossoverPoint),PathNodeValue(index2,crossoverPoint+1:y_lim)];
%     PathEdgeValueChild1=[PathEdgeValue(index1,1:crossoverPoint-1),PathEdgeValue(index2,crossoverPoint:y_lim-1)];
%     
%     PathArrayChild2=[PathArray(index2,1:crossoverPoint),PathArray(index1,crossoverPoint+1:y_lim)];
%     PathNodeValueChild2=[PathNodeValue(index2,1:crossoverPoint),PathNodeValue(index1,crossoverPoint+1:y_lim)];
%     PathEdgeValueChild2=[PathEdgeValue(index2,1:crossoverPoint-1),PathEdgeValue(index1,crossoverPoint:y_lim-1)];
%     
%     index1=index1+crossoverSize;
%     index2=index2+crossoverSize;
%     
%     PathArray(index1,:)=PathArrayChild1;
%     PathNodeValue(index1,:)=PathNodeValueChild1;
%     PathEdgeValue(index1,:)=PathEdgeValueChild1;
%     
%     PathArray(index2,:)=PathArrayChild2;
%     PathNodeValue(index2,:)=PathNodeValueChild2;
%     PathEdgeValue(index2,:)=PathEdgeValueChild2;
%     
%     for index=[index1 index2]
%         MeanOfNodeFactor=mean(PathNodeValue(index,:));
%         MeanOfEdgeFactor=mean(PathEdgeValue(index,:));
%         FirstObjective=(MeanOfNodeFactor+MeanOfEdgeFactor)/2;
%         STDofNodeFactor=std(PathNodeValue(index,:));
%         STDofEdgeFactor=std(PathEdgeValue(index,:));
%         SecondObjective=(STDofNodeFactor+STDofEdgeFactor)/2;
%         CurrentCostArray(index,1:6) = [MeanOfNodeFactor MeanOfEdgeFactor FirstObjective STDofNodeFactor STDofEdgeFactor SecondObjective];
%     end
end

function MutationMatrix = checkMutationPossibility(Path,treeCell)
    MutationMatrix=[];
    mutationPointCounter=1;
    [x_lim, y_lim] = size(Path);
    for index1 = y_lim:-1:3
        radialVal1=Path{index1}(1,1);
        dispVal1=Path{index1}(1,2);
        treeCellIndex1=(1+3*radialVal1*(radialVal1-1)) + dispVal1;
        for index2=2:6
            % check successive rows of treeCell, then crossover is possible
            radialVal2 = treeCell{treeCellIndex1,index2}(1,1);
            dispVal2 = treeCell{treeCellIndex1,index2}(1,2);
            if radialVal2 == Path{index1-1}(1,1) && dispVal2 == Path{index1-1}(1,2)
                continue;
            end
            treeCellIndex2=(1+3*radialVal2*(radialVal2-1)) + dispVal2;
            for index3=2:6
                if treeCell{treeCellIndex2,index3}(1,1) == Path{index1-2}(1,1) && treeCell{treeCellIndex2,index3}(1,2) == Path{index1-2}(1,2)
                    MutationMatrix(mutationPointCounter,1) = index1-1;
                    MutationMatrix(mutationPointCounter,2) = radialVal2;
                    MutationMatrix(mutationPointCounter,3) = dispVal2;
                    mutationPointCounter = mutationPointCounter+1;
                end
            end
        end
    end
    MutationMatrix
end

function [PathArray PathNodeValue PathEdgeValue CurrentCostArray]=performCrossover(PathArray, PathNodeValue, PathEdgeValue, CurrentCostArray, treeCell, index1, index2, crossoverPoint, crossoverSize)
    [x_lim y_lim]=size(PathArray);
    
    PathArrayChild1=[PathArray(index1,1:crossoverPoint),PathArray(index2,crossoverPoint+1:y_lim)];
    PathNodeValueChild1=[PathNodeValue(index1,1:crossoverPoint),PathNodeValue(index2,crossoverPoint+1:y_lim)];
    PathEdgeValueChild1=[PathEdgeValue(index1,1:crossoverPoint-1),PathEdgeValue(index2,crossoverPoint:y_lim-1)];
    
    PathArrayChild2=[PathArray(index2,1:crossoverPoint),PathArray(index1,crossoverPoint+1:y_lim)];
    PathNodeValueChild2=[PathNodeValue(index2,1:crossoverPoint),PathNodeValue(index1,crossoverPoint+1:y_lim)];
    PathEdgeValueChild2=[PathEdgeValue(index2,1:crossoverPoint-1),PathEdgeValue(index1,crossoverPoint:y_lim-1)];
    
    index1=index1+crossoverSize;
    index2=index2+crossoverSize;
    
    PathArray(index1,:)=PathArrayChild1;
    PathNodeValue(index1,:)=PathNodeValueChild1;
    PathEdgeValue(index1,:)=PathEdgeValueChild1;
    
    PathArray(index2,:)=PathArrayChild2;
    PathNodeValue(index2,:)=PathNodeValueChild2;
    PathEdgeValue(index2,:)=PathEdgeValueChild2;
    
    for index=[index1 index2]
        MeanOfNodeFactor=mean(PathNodeValue(index,:));
        MeanOfEdgeFactor=mean(PathEdgeValue(index,:));
        FirstObjective=(MeanOfNodeFactor+MeanOfEdgeFactor)/2;
        STDofNodeFactor=std(PathNodeValue(index,:));
        STDofEdgeFactor=std(PathEdgeValue(index,:));
        SecondObjective=(STDofNodeFactor+STDofEdgeFactor)/2;
        CurrentCostArray(index,1:6) = [MeanOfNodeFactor MeanOfEdgeFactor FirstObjective STDofNodeFactor STDofEdgeFactor SecondObjective];
    end
end

function crossoverPoint = checkCrossoverPossibility(Path1,Path2,treeCell)
    crossoverPoint = 0;
    cellfun(@(x) strcat(num2str(x(1,1)),'-',num2str(x(1,2))),Path1,'UniformOutput',false)
    cellfun(@(x) strcat(num2str(x(1,1)),'-',num2str(x(1,2))),Path2,'UniformOutput',false)
    [x_lim y_lim]=size(Path1);
    midpoint=floor(y_lim/2);
    for index1=midpoint:y_lim-2
        radialVal=Path1{index1}(1,1);
        range=(1+3*radialVal*(radialVal-1));
        for index2=range-((radialVal-1)*6):range
            foundCounter=0;
            for index3=2:6
                % if same position elements of both paths lies in single
                % row of treeCell, then crossover is possible
                if Path1{index1}(1,2)==treeCell{index2,index3}(1,2) && Path2{index1+1}(1,2)==treeCell{index2,1}(1,2)
                    foundCounter=foundCounter+1;
                end
                if Path2{index1}(1,2)==treeCell{index2,index3}(1,2) && Path1{index1+1}(1,2)==treeCell{index2,1}(1,2)
                    foundCounter=foundCounter+1;
                end
            end
            if foundCounter==2
                crossoverPoint = index1;
                break;
            end
        end
        if crossoverPoint ~=0
            break;
        end
    end
    crossoverPoint
end
function [radialVal dispVal] = getRadDispFromSeq(sequenceVal)
    for index1=1:sequenceVal
        if(1+3*index1*(index1-1)>=sequenceVal)
            break;
        end
    end
    radialVal=index1-1;
    dispVal=sequenceVal-(1+3*radialVal*(radialVal-1));
end
function SchedulerTable= generateScheduleRequest(NumberOfRequests, radiusLimit)
    sequenceLimit=1+3*radiusLimit*(radiusLimit+1);
    SchedulerTable=[];
    SchedulerTable(1:NumberOfRequests,1)=randi(sequenceLimit,NumberOfRequests,1);
    SchedulerTable(1:NumberOfRequests,4)=randi(sequenceLimit,NumberOfRequests,1);
    
    for index0=1:NumberOfRequests
        [r d] = getRadDispFromSeq(SchedulerTable(index0,1));
        SchedulerTable(index0,2)=r;
        SchedulerTable(index0,3)=d;
        [r d] = getRadDispFromSeq(SchedulerTable(index0,4));
        SchedulerTable(index0,5)=r;
        SchedulerTable(index0,6)=d;
    end
end
function treeCell = generateTreeCell(radiusLim)
    %% Iteratively add nodes
    treeCell{1,1}=[0 0];
    for dispIndex=1:6
        treeCell{1,dispIndex+1}=[1 dispIndex];
    end
    for radialIndex = 1:radiusLim
        for dispIndex = 1:(6*radialIndex)
            if(mod((dispIndex-1),radialIndex)==0)
                
                % radial node - itself
                [x_lim y_lim]=size(treeCell);
                treeCell{x_lim+1,1}=[radialIndex dispIndex];
                
                % radial node - direct connection
                [dc1RadialIndex dc1DispIndex]=getDirectConnection1ForRadialNode(radialIndex, dispIndex);
                treeCell{x_lim+1,2}=[dc1RadialIndex dc1DispIndex];
                [dc2RadialIndex dc2DispIndex]=getDirectConnection2ForRadialNode(radialIndex, dispIndex);
                treeCell{x_lim+1,3}=[dc2RadialIndex dc2DispIndex];
                [dc3RadialIndex dc3DispIndex]=getDirectConnection3ForRadialNode(radialIndex, dispIndex);
                treeCell{x_lim+1,4}=[dc3RadialIndex dc3DispIndex];

                % radial node - indirect connection
                [idc1RadialIndex idc1DispIndex]=getInDirectConnection1ForRadialNode(radialIndex, dispIndex);
                treeCell{x_lim+1,5}=[idc1RadialIndex idc1DispIndex];
                [idc2RadialIndex idc2DispIndex]=getInDirectConnection2ForRadialNode(radialIndex, dispIndex);
                treeCell{x_lim+1,6}=[idc2RadialIndex idc2DispIndex];
                
                % dummy
                treeCell{x_lim+1,7}=[0 0];
            else
                % non radial node - itself
                [x_lim y_lim]=size(treeCell);
                treeCell{x_lim+1,1}=[radialIndex dispIndex];
                
                % non radial node - direct connection
                [dc1RadialIndex dc1DispIndex]=getDirectConnection1ForNonRadialNode(radialIndex, dispIndex);
                treeCell{x_lim+1,2}=[dc1RadialIndex dc1DispIndex];
                [dc2RadialIndex dc2DispIndex]=getDirectConnection2ForNonRadialNode(radialIndex, dispIndex);
                treeCell{x_lim+1,3}=[dc2RadialIndex dc2DispIndex];
                
                % dummy
                treeCell{x_lim+1,4}=[0 0];
                
                % non radial node - indirect connection
                [idc1RadialIndex idc1DispIndex]=getInDirectConnection1ForNonRadialNode(radialIndex, dispIndex);
                treeCell{x_lim+1,5}=[idc1RadialIndex idc1DispIndex];
                [idc2RadialIndex idc2DispIndex]=getInDirectConnection2ForNonRadialNode(radialIndex, dispIndex);
                treeCell{x_lim+1,6}=[idc2RadialIndex idc2DispIndex];
                
                % dummy
                treeCell{x_lim+1,7}=[0 0];
            end
        end
    end
end
function [NodeWeight UplinkEdgeWeight DownlinkEdgeWeight]=generateWeights(radiusLim,treeCell,NodeCapacityLevel,LinkCapacityLevel)
    NodeWeight={0,0,'0-0',0.99};
    index1=2;
    for radialIndex = 1:radiusLim
        for dispIndex = 1:(6*radialIndex)
            NodeWeight{index1,1}=radialIndex;
            NodeWeight{index1,2}=dispIndex;
            NodeWeight{index1,3}=strcat(num2str(radialIndex),'-',num2str(dispIndex));
            NodeWeight{index1,4}=NodeCapacityLevel-0.05+rand(1)/10;
            index1=index1+1;
        end
    end
    [x_lim y_lim]=size(treeCell);
    UplinkEdgeWeight=LinkCapacityLevel-0.05+rand(x_lim,y_lim-1)/10;
    DownlinkEdgeWeight=LinkCapacityLevel-0.05+rand(x_lim,y_lim-1)/10;    
end

function hypervolume = calculateHyperVolume(FirstParetoFront, ReferencePoint)
    % sort by first objective
    [FirstParetoFront, idx] = sortrows(FirstParetoFront,3);
    [x_lim y_lim]=size(FirstParetoFront);
    hypervolume=0;
    for index1=1:x_lim
        hypervolume=hypervolume+ ((ReferencePoint(1,1)-FirstParetoFront(index1,3))*(ReferencePoint(1,2)-FirstParetoFront(index1,6)));
        ReferencePoint(1,2)=FirstParetoFront(index1,6);
    end
end
%% End