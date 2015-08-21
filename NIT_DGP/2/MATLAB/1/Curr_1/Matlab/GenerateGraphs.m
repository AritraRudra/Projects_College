% %% Graph for fitness vs generation
fitnessEachGen = load('SOGA/Fitness_vs_Gen.txt');
len = length(fitnessEachGen);
X1 = zeros(len);
for i = 1:len
    X1(i) = i;
end
X2 = X1;

Y1 = fitnessEachGen(1,:);
Y2 = fitnessEachGen(2,:);
    
figure(3);
clf;
set(gcf, 'Color', 'white'); % white bckgr
plot(X1,Y1,'-.ok','Linewidth',2);
xlabel('No of generations -->');
ylabel('Fitness of best solution across each generation  -->');
hleg1 = legend('Population size = 512');
axis([0,20,(min(Y1) - min(Y1)/1e4),(max(Y1) + max(Y1)/1e4)]);

figure(4);
clf;
set(gcf, 'Color', 'white'); % white bckgr
plot(X2,Y2,'-xk','Linewidth',2);
xlabel('No of generations -->');
ylabel('Average fitness of each generation  -->');
hleg1 = legend('Population size = 512');

 
%% with out applying S, traditional approach
noSCostMat = load('SOGA/Without_S.txt');
noSDelayCost = noSCostMat(1,:);
noSEnrgCost = noSCostMat(2,:);
noSTotCost = noSCostMat(3,:);

valuesForSMat = load('SOGA/Values_For_Low_S.txt');
delArrLowS = valuesForSMat(1,:);
enrgArrLowS = valuesForSMat(2,:);
totArrLowS = valuesForSMat(3,:);

valuesForSMat = load('SOGA/Values_For_High_S.txt');
delArrHighS = valuesForSMat(1,:);
enrgArrHighS = valuesForSMat(2,:);
totArrHighS = valuesForSMat(3,:);

len = length(noSCostMat);
X1 = zeros(len);
for i = 1:len
    X1(i) = i;
end

figure(5);
clf;
% set(gcf, 'Color', 'white'); % white bckgr

subplot(3,2,1);
title('Blah');
plot(X1, noSDelayCost, '-.k', X1,delArrLowS,'-k', 'Linewidth', 2);
xlabel('No of generations -->');
ylabel('Delay Cost -->');
% hleg1 = legend('Traditional approach','Proposed approach for minimizing energy cost');

subplot(3,2,2);
title('Blah');
plot(X1, noSDelayCost, '-.k', X1,delArrHighS,'-k', 'Linewidth', 2);
xlabel('No of generations -->');
ylabel('Delay Cost -->');
hleg1 = legend('Traditional approach','Proposed approach'); % for minimizing delay cost');


subplot(3,2,3);
title('Blah');
plot(X1, noSEnrgCost, '-.k', X1,enrgArrLowS,'-k', 'Linewidth', 2);
xlabel('No of generations -->');
ylabel('Energy Cost -->');
% hleg1 = legend('Traditional approach','Proposed approach for minimizing energy cost');

subplot(3,2,4);
title('Blah');
plot(X1, noSEnrgCost, '-.k', X1,enrgArrHighS,'-k', 'Linewidth', 2);
xlabel('No of generations -->');
ylabel('Energy Cost -->');
% hleg1 = legend('Traditional approach','Proposed approach for minimizing delay cost');

subplot(3,2,5);
title('Blah');
plot(X1, noSTotCost, '-.k', X1,totArrLowS,'-k', 'Linewidth', 2);
xlabel('No of generations -->');
ylabel('Total Cost -->');
% hleg1 = legend('Traditional approach','Proposed approach for minimizing energy cost');

subplot(3,2,6);
title('Blah');
plot(X1, noSTotCost, '-.k', X1,totArrHighS,'-k', 'Linewidth', 2);
xlabel('No of generations -->');
ylabel('Total Cost -->');
% hleg1 = legend('Traditional approach','Proposed approach for minimizing delay cost');
set(gcf, 'Color', 'white'); % white bckgr


%% low S vs high S
figure(6);
clf;
set(gcf, 'Color', 'white'); % white bckgr

subplot(1,2,1);
title('Blah');
plot(X1, delArrLowS, '-.k', X1,delArrHighS,'-k', 'Linewidth', 2);
xlabel('No of generations -->');
ylabel('Delay Cost -->');
hleg1 = legend('S = 0.25', 'S = 0.85');
% hleg1 = legend('Delay cost when minimizing energy cost','Delay cost when minimizing delay cost');
set(hleg1,'Location','NorthEast');

subplot(1,2,2);
plot(X1, enrgArrLowS, '-.k', X1,enrgArrHighS,'-k', 'Linewidth', 2);
xlabel('No of generations -->');
ylabel('Energy Cost -->');
hleg1 = legend('S = 0.15', 'S = 0.8');
% hleg1 = legend('Energy cost when minimizing energy cost','Energy cost when minimizing delay cost');


%% Graph for comparision with Dijkstra
djMatrix = load('SOGA/Dijkstra_Comparision.txt');
len = length(djMatrix);
delayY = djMatrix(1,:);
enrgY = djMatrix(2,:);

djDelay = zeros(len);
djEnrg = zeros(len);
for i = 1:len
djDelay(i) = 278214000;    % 1-12-35-19-64
djEnrg(i) = 3.75360666231636e15;    % 1    29    16    59    17    48     4    37    25    64
end

figure(7);
clf;
set(gcf, 'Color', 'white'); % white bckgr

subplot(2,2,1);
plot(X1, djDelay, '-.k', X1,delayY,'-k', 'Linewidth', 2);
xlabel('No of generations -->');
ylabel('Delay Cost -->');
hleg1 = legend('Delay cost as obtained by Dijkstras algorithm','Delay cost as obtained by Proposed algorithm');

subplot(2,2,2);
plot(X1,enrgY,'-k', X1, djEnrg, '-.k', 'Linewidth', 2);
xlabel('No of generations -->');
ylabel('Energy Cost -->');
hleg1 = legend('Energy cost as obtained by Dijkstra''s algo','Generation no. = 512, Population size = 512');

% subplot(2,2,[3,4]);
% plot(X1,enrgY,':k', X1, djEnrg, '-k', 'Linewidth', 2);
% xlabel('No of generations -->');
% ylabel('Total Cost -->');
% hleg1 = legend('Energy cost as obtained by Dijkstra''s algo','Generation no. = 512, Population size = 512');



%% MOGA 
% Graph for the pareto front
frontMatrix = load('MOGA/Front_For_Graph.txt');
% len = length(frontMatrix);
delayFrontEnrg = frontMatrix(1,:);
enrgFrontEnrg = frontMatrix(2,:);

figure(11);
clf;
set(gcf, 'Color', 'white'); % white bckgr
% plot(delay,enrg,'O','MarkerEdgeColor','r','MarkerFaceColor','g','MarkerSize',10);
plot(delayFrontEnrg,enrgFrontEnrg,'-rO','MarkerEdgeColor','k','MarkerFaceColor','g','MarkerSize', 10,'Linewidth', 2);
xlabel('Delay -->');
ylabel('Energy -->');
%set(gca,'Xtick',linspace(35,45,length(10)));
hleg1 = legend('Generation no. = 16, Population size = 512');

%% Comparision with NSGA-II
nsgaMat = load('MOGA/NSGA_II_Front.txt');
% len = length(nsgaMat);
delayNSGA = nsgaMat(1,:);
enrgNSGA = nsgaMat(2,:);

figure(12);
clf;
set(gcf, 'Color', 'white'); % white bckgr
plot(delayNSGA,enrgNSGA,'-rO','MarkerEdgeColor','k','MarkerFaceColor','g','MarkerSize', 10,'Linewidth', 2);
% plot(delayNSGA,enrgNSGA, '-rs', 'Linewidth', 2);
xlabel('Delay -->');
ylabel('Energy -->');
hleg1 = legend('Generation no. = 16, Population size = 512');

% xAxisVals = (35:45);
figure(13);
clf;
set(gcf, 'Color', 'white'); % white bckgr
% plot(delay,enrg,'O','MarkerEdgeColor','r','MarkerFaceColor','b','MarkerSize', 15,'Linewidth', 2);
% plot(delayNSGA,enrgNSGA,'O','MarkerEdgeColor','k','MarkerFaceColor','g','MarkerSize', 10,'Linewidth', 2);
% plot(delayFrontEnrg,enrgFrontEnrg,'-kS', delayNSGA,enrgNSGA,':kX','MarkerEdgeColor','k','MarkerFaceColor','w','MarkerSize', 15,'Linewidth', 2);
plot(delayFrontEnrg,enrgFrontEnrg,'--gS', delayNSGA,enrgNSGA,'-.rX','MarkerEdgeColor','k','MarkerFaceColor','w','MarkerSize', 15,'Linewidth', 2);
xlabel('Delay -->');
ylabel('Energy -->');
hleg1 = legend('Front obtained by proposed method with preference towards energy','Front obtained by NSGA-II method');
% set(gca,'XTickLabel',xAxisVals);
% set(gca,'Xtick',linspace(35,45,length(nsgaMat)));