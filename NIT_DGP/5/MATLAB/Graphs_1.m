% %% Graph for fitness vs generation
%fitnessEachGen = load('SOGA/Fitness_vs_Gen.txt');
%dirPath = dir('D:\College\Project\5\Results\Set_8\');
%fitnessEachGen = load(dirPath.'Fitness_vs_Gen.txt');
fitnessEachGen = importdata('D:\College\Project\5\Results\Set_5\Fitness_vs_Gen.txt');

len = length(fitnessEachGen);
X1 = zeros(1,len);
for i = 1:len
    X1(1,i) = i;
end
% X2 = X1;

Y1 = fitnessEachGen(1,:);
Y2 = fitnessEachGen(2,:);
%Y3 = fitnessEachGen(3,:);
    
figure(1);
clf;
set(gcf, 'Color', 'white'); % white bckgr
% plot(X1,Y1,'-.ok','Linewidth',2);
plot(X1,Y1,'--^k',X1,Y2,'-.vk','Linewidth',2);
axis([1, 19, 2000,11000]);
xlabel('No of generations -->');
%ylabel({'Fitness of best solution','across each generation  -->'});
legend('Fitness of best solution','Average fitness of popultion');


% subplot(2,1,1);
% plot(X1,Y1,'-.ok','Linewidth',2);
% axis([1, 10, 2500,2900]);
% xlabel('No of generations -->');
% ylabel({'Fitness of best solution','across each generation  -->'});
% subplot(2,1,2);
% plot(X1,Y2,'-.ok','Linewidth',2);
% axis([1, 20,2000,12000]);
% xlabel('No of generations -->');
% ylabel({'Average fitness of popultion','across each generation  -->'});
%set(gca,'Xtick',linspace(0,15,16));
% legend('Population size = 512');
% axis([0,20,(min(Y1) - min(Y1)/1e4),(max(Y1) + max(Y1)/1e4)]);




%%
QOSEachGen = importdata('D:\College\Project\5\Results\Set_8\QOS_across_Gen.txt');

len = length(QOSEachGen);
X1 = zeros(1,len);
for i = 1:len
    X1(1,i) = i;
end

Y1 = QOSEachGen(1,:);
Y2 = QOSEachGen(2,:);
Y3 = QOSEachGen(3,:);
Y4 = QOSEachGen(4,:);

% figure(2);
% clf;
% set(gcf, 'Color', 'white'); % white bckgr
% subplot(2,1,1);
% plot(X1,Y1,'-.ok','Linewidth',2);
% axis([1, 10, 1800,3000]);
% xlabel('No of generations -->');
% ylabel({'Delay cost of best solution','across each generation  -->'});
% subplot(2,1,2);
% plot(X1,Y2,'-.ok','Linewidth',2);
% axis([1, 10,1500,4000]);
% xlabel('No of generations -->');
% ylabel({'Energy cost of best solution','across each generation  -->'});

%alt. to fig 2
figure(2);
clf;
set(gcf, 'Color', 'white'); % white bckgr
plot(X1,Y1,'--^k',X1,Y2,'-.vk','Linewidth',2);
axis([1, 10, 1500,4000]);
xlabel('No of generations -->');
ylabel('Cost of best solution across each generation  -->');
legend(' Delay Cost ','Energy Cost ');

figure(3);
clf;
set(gcf, 'Color', 'white'); % white bckgr
subplot(2,1,1);
plot(X1,Y3,'-.ok','Linewidth',2);
axis([1, 22, 2000,16000]);
xlabel('No of generations -->');
ylabel({'Average delay cost of popultion','across each generation  -->'});
subplot(2,1,2);
plot(X1,Y4,'-.ok','Linewidth',2);
axis([1, 22,2000,16000]);
xlabel('No of generations -->');
ylabel({'Average energy cost of popultion','across each generation  -->'});

%alt. to fig 3
% figure(3);
% clf;
% set(gcf, 'Color', 'white'); % white bckgr
% plot(X1,Y3,'--^k',X1,Y4,'-.vk','Linewidth',2);
% axis([1, 22, 2000,16000]);
% xlabel('No of generations -->');
% ylabel('Average cost of popultion across each generation  -->');
% legend(' Delay cost ','Energy cost ');



%% Graph for comparision with Dijkstra
djMatrix = importdata('D:\College\Project\5\Results\Set_5\Dijkstra_Comparision.txt');
%len = length(djMatrix);
len = 21;
X1 = zeros(1,len);
for i = 1:len
    X1(1,i) = i;
end

delayY = djMatrix(4,(1:21));
enrgY = djMatrix(2,(1:21));
%delayY = djMatrix(4,:);
%enrgY = djMatrix(2,:);

djDelay = zeros(1,len);
djEnrg = zeros(1,len);
for i = 1:len
    djDelay(1,i) = 2390;    % 1->50->3->47->44->48->64
    djEnrg(1,i) = 1880;    % 1->53->31->11->46->24->60->64
end

figure(4);
clf;
set(gcf, 'Color', 'white'); % white bckgr
subplot(2,1,1);
plot(X1, djDelay, '-ok', X1,delayY,'--^k', 'Linewidth', 2);
axis([ 1, 21, 2000, 17000 ]);
xlabel('No of generations -->');
ylabel('Delay Cost -->');
legend('Delay cost as obtained by Dijkstras algorithm','Delay cost as obtained by Proposed algorithm');
% axis([ 0, 10, (min(djDelay) - min(djDelay)/1e4), (max(delayY) + max(delayY)/1e4) ]); % not working

subplot(2,1,2);
plot(X1,djEnrg,'-ok', X1, enrgY, '--^k', 'Linewidth', 2);
axis([ 1, 21, 1700, 17000 ]);
xlabel('No of generations -->');
ylabel('Energy Cost -->');
legend('Energy cost as obtained by Dijkstras algorithm','Energy cost as obtained by Proposed algorithm');




















% %% with out applying S, traditional approach
% noSCostMat = load('SOGA/Without_S.txt');
% noSDelayCost = noSCostMat(1,:);
% noSEnrgCost = noSCostMat(2,:);
% noSTotCost = noSCostMat(3,:);
% 
% valuesForSMat = load('SOGA/Values_For_Low_S.txt');
% delArrLowS = valuesForSMat(1,:);
% enrgArrLowS = valuesForSMat(2,:);
% totArrLowS = valuesForSMat(3,:);
% 
% valuesForSMat = load('SOGA/Values_For_High_S.txt');
% delArrHighS = valuesForSMat(1,:);
% enrgArrHighS = valuesForSMat(2,:);
% totArrHighS = valuesForSMat(3,:);
% 
% len = length(noSCostMat);
% X1 = zeros(1,len);
% for i = 1:len
%     X1(1,i) = i;
% end
% 
% figure(5);
% clf;
% set(gcf, 'Color', 'white'); % white bckgr
% 
% subplot(2,1,1);
% plot(X1, noSDelayCost, '-^k', X1,delArrLowS,'-ok', 'Linewidth', 0.5);
% xlabel('No of generations -->');
% ylabel('Delay Cost -->');
% legend('Traditional approach','Proposed approach to minimize energy cost');
% 
% subplot(2,1,2);
% plot(X1, noSDelayCost, '-^k', X1,delArrHighS,'-ok', 'Linewidth', 0.5);
% xlabel('No of generations -->');
% ylabel('Delay Cost -->');
% legend('Traditional approach','Proposed approach to minimize delay cost');
% 
% figure(6);
% clf;
% set(gcf, 'Color', 'white'); % white bckgr
% 
% subplot(2,1,1);
% plot(X1, noSEnrgCost, ':k', X1,enrgArrLowS,'-k', 'Linewidth', 2);
% xlabel('No of generations -->');
% ylabel('Energy Cost -->');
% legend('Traditional approach','Proposed approach to minimize energy cost');
% 
% subplot(2,1,2);
% plot(X1, noSEnrgCost, ':k', X1,enrgArrHighS,'-k', 'Linewidth', 2);
% xlabel('No of generations -->');
% ylabel('Energy Cost -->');
% legend('Traditional approach','Proposed approach to minimize delay cost');
% 
% 
% figure(7);
% clf;
% set(gcf, 'Color', 'white'); % white bckgr
% 
% subplot(2,1,1);
% plot(X1, noSTotCost, ':k', X1,totArrLowS,'-k', 'Linewidth', 2);
% xlabel('No of generations -->');
% ylabel('Total Cost -->');
% legend('Traditional approach','Proposed approach to minimize energy cost');
% 
% subplot(2,1,2);
% plot(X1, noSTotCost, ':k', X1,totArrHighS,'-k', 'Linewidth', 2);
% xlabel('No of generations -->');
% ylabel('Total Cost -->');
% legend('Traditional approach','Proposed approach to minimize delay cost');
% 
% 
% %% low S vs high S
% figure(8);
% clf;
% set(gcf, 'Color', 'white'); % white bckgr
% 
% subplot(1,2,1);
% plot(X1, delArrLowS, ':k', X1,delArrHighS,'-k', 'Linewidth', 1.5);
% xlabel('No of generations -->');
% ylabel('Delay Cost -->');
% legend('S = 0.2', 'S = 0.85');
% 
% subplot(1,2,2);
% plot(X1, enrgArrLowS, '-.k', X1,enrgArrHighS,'-k', 'Linewidth', 2.5);
% xlabel('No of generations -->');
% ylabel('Energy Cost -->');
% legend('S = 0.2', 'S = 0.85');
% 
% 
% %% Graph for comparision with Dijkstra
% djMatrix = load('SOGA/Dijkstra_Comparision.txt');
% len = length(djMatrix);
% X1 = zeros(1,len);
% for i = 1:len
%     X1(1,i) = i;
% end
% 
% delayY = djMatrix(1,:);
% enrgY = djMatrix(4,:);
% 
% djDelay = zeros(1,len);
% djEnrg = zeros(1,len);
% for i = 1:len
% djDelay(1,i) = 278214000;    % 1-12-35-19-64
% djEnrg(1,i) = 3.75360666231636e15;    % 1    29    16    59    17    48     4    37    25    64
% end
% 
% figure(9);
% clf;
% set(gcf, 'Color', 'white'); % white bckgr
% 
% subplot(2,1,1);
% plot(X1, djDelay, ':k', X1,delayY,'-k', 'Linewidth', 2);
% xlabel('No of generations -->');
% ylabel('Delay Cost -->');
% legend('Delay cost as obtained by Dijkstras algorithm','Delay cost as obtained by Proposed algorithm');
% % axis([ 0, 10, (min(djDelay) - min(djDelay)/1e4), (max(delayY) + max(delayY)/1e4) ]); % not working
% 
% subplot(2,1,2);
% plot(X1,djEnrg,'-.k', X1, enrgY, '-k', 'Linewidth', 2);
% xlabel('No of generations -->');
% ylabel('Energy Cost -->');
% legend('Energy cost as obtained by Dijkstras algorithm','Energy cost as obtained by Proposed algorithm');
% % %axis([ 1, 10, ((djEnrg(1,1)) - (djEnrg(1,1))/10), (max(enrgY) + max(enrgY)/1e2) ]); % not working
% % axis([ 1, 10, ((djEnrg(1,1))/10), (max(enrgY) + max(enrgY)/1e2) ]); % not working
% 
% % hold on;
% 
% % h = gca;
% % xlim([1,10]),ylim([(djEnrg(1,1))/10, (max(enrgY) + max(enrgY)/1e2) ]);
% % for i = 1:len
% %     plot(h, X1,djEnrg(1,i),'-.k', X1, enrgY(1,i), '-k', 'Linewidth', 2);
% % end
% 
% 
% % hAxes = axes;                     %# Create a set of axes
% % hData = plot(hAxes,nan,nan,'*');  %# Initialize a plot object (NaN values will
% %                                   %#   keep it from being displayed for now)
% % axis(hAxes,[ 1, 10, ((djEnrg(1,1))/10), (max(enrgY) + max(enrgY)/1e5)]);            %# Fix your axes limits, with x going from 0
% %                                   %#   to 2 and y going from 0 to 4
% % for i = 1:len                 %# Loop 100 times
% %   set(hData,'XData',djEnrg(1,i),...    %# Set the XData and YData of your plot object
% %             'YData',enrgY(1,i));      %#   to random values in the axes range
% %   drawnow                         %# Force the graphics to update
% % end
% 
% % % subplot(2,2,[3,4]);
% % % plot(X1,enrgY,':k', X1, djEnrg, '-k', 'Linewidth', 2);
% % % xlabel('No of generations -->');
% % % ylabel('Total Cost -->');
% % % hleg1 = legend('Energy cost as obtained by Dijkstra''s algo','Generation no. = 512, Population size = 512');
% 
% 
% 
% %% MOGA 
% % Graph for the pareto front
% frontMatrix = load('MOGA/Front_For_Delay.txt');
% % len = length(frontMatrix);
% delayFrontDelay = frontMatrix(1,:);
% enrgFrontDelay = frontMatrix(2,:);
% 
% figure(11);
% clf;
% set(gcf, 'Color', 'white'); % white bckgr
% 
% subplot(2,2,1);
% % plot(delay,enrg,'O','MarkerEdgeColor','r','MarkerFaceColor','g','MarkerSize',10);
% plot(delayFrontDelay,enrgFrontDelay,'-rO','MarkerEdgeColor','k','MarkerFaceColor','g','MarkerSize', 10,'Linewidth', 2);
% axis([3.96e7 4.05e7 0.8e6 2.5e6]);
% xlabel('Delay -->');
% ylabel('Energy -->');
% %set(gca,'Xtick',linspace(35,45,length(10)));
% %legend('Generation no. = 16, Population size = 512');
% 
% 
% frontMatrix = load('MOGA/Front_For_Energy.txt');
% % len = length(frontMatrix);
% delayFrontEnrg = frontMatrix(1,:);
% enrgFrontEnrg = frontMatrix(2,:);
% 
% %figure(12);
% %clf;
% %set(gcf, 'Color', 'white'); % white bckgr
% 
% subplot(2,2,3);
% % plot(delay,enrg,'O','MarkerEdgeColor','r','MarkerFaceColor','g','MarkerSize',10);
% plot(delayFrontEnrg,enrgFrontEnrg,'-rO','MarkerEdgeColor','k','MarkerFaceColor','g','MarkerSize', 10,'Linewidth', 2);
% axis([3.7e7 6.05e7 8e5 10.5e5]);
% xlabel('Delay -->');
% ylabel('Energy -->');
% % set(gca,'Xtick',linspace(35,45,length(10)));
% % legend('Generation no. = 16, Population size = 512');
% 
% 
% %% Comparision with NSGA-II
% nsgaMat = load('MOGA/NSGA_II_Front.txt');
% % len = length(nsgaMat);
% delayNSGA = nsgaMat(1,:);
% enrgNSGA = nsgaMat(2,:);
% 
% % figure(13);
% % clf;
% % set(gcf, 'Color', 'white'); % white bckgr
% subplot(2,2,[2,4]);
% plot(delayNSGA,enrgNSGA,'-rO','MarkerEdgeColor','k','MarkerFaceColor','g','MarkerSize', 10,'Linewidth', 2);
% % plot(delayNSGA,enrgNSGA, '-rs', 'Linewidth', 2);
% axis([3.9e7 6.05e7 0.7e6 2.5e6]);
% xlabel('Delay -->');
% ylabel('Energy -->');
% % legend('Generation no. = 16, Population size = 512');
% 
% % xAxisVals = (35:45);
% figure(14);
% clf;
% set(gcf, 'Color', 'white'); % white bckgr
% % plot(delay,enrg,'O','MarkerEdgeColor','r','MarkerFaceColor','b','MarkerSize', 15,'Linewidth', 2);
% % plot(delayNSGA,enrgNSGA,'O','MarkerEdgeColor','k','MarkerFaceColor','g','MarkerSize', 10,'Linewidth', 2);
% % plot(delayFrontEnrg,enrgFrontEnrg,'-kS', delayNSGA,enrgNSGA,':kX','MarkerEdgeColor','k','MarkerFaceColor','w','MarkerSize', 15,'Linewidth', 2);
% plot(delayFrontDelay,enrgFrontDelay,'-.bD',delayFrontEnrg,enrgFrontEnrg,'--gS', delayNSGA,enrgNSGA,':rX','MarkerEdgeColor','k','MarkerFaceColor','w','MarkerSize', 15,'Linewidth', 2);
% axis([3.9e7 6.05e7 0.6e6 2.5e6]);
% xlabel('Delay -->');
% ylabel('Energy -->');
% legend('Front obtained by proposed method with preference towards minimizing delay','Front obtained by proposed method with preference towards minimizing energy','Front obtained by NSGA-II approach');
% % set(gca,'XTickLabel',xAxisVals);
% % set(gca,'Xtick',linspace(35,45,length(nsgaMat)));