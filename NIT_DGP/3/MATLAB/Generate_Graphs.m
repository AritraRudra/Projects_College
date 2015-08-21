%% Scatter Plot
scatterData = load('Data_Graphs/Scatter_Data.txt');
len = length(scatterData);
X1 = zeros(1,len);
for i = 1:len
    X1(1,i) = i;
end
% X2 = X1;

Y1 = scatterData(1,:);
Y2 = scatterData(2,:);
    
figure(3);
clf;
set(gcf, 'Color', 'white'); % white bckgr
plot(Y2,Y1,'O','MarkerEdgeColor','w','MarkerFaceColor','k','MarkerSize', 10);
%axis([0, 60, 0, 3.5e5]);
% axis([0, 20, 1e4, 7e4]);
xlabel('h_c -->');
ylabel('t_c  -->');
%set(gca,'Xtick',linspace(0,15,16));
% legend('Population size = 512');
% axis([0,20,(min(Y1) - min(Y1)/1e4),(max(Y1) + max(Y1)/1e4)]);

figure(4);
clf;
set(gcf, 'Color', 'white'); % white bckgr
% frontData = load('Data_Graphs/Front.txt');
frontData = load('Data_Graphs/Fronts.txt');
X1 = frontData(1,:);
Y1 = frontData(2,:);
plot(X1,Y1,'-.kO','MarkerEdgeColor','w','MarkerFaceColor','k','MarkerSize', 15,'Linewidth', 2);
axis([0, 22, 1e4, 7e4]);
% axis([0, 60, 0, 3.5e5]);
xlabel('h_c -->');
ylabel('t_c  -->');
% legend('Preference towards delay','Preference towards energy');
% hleg1 = legend('Preference towards delay','Preference towards energy');
% set(hleg1,'Location','NorthWest');


%% mean and SD calculation, run only once
routeData = load('Data_Graphs/Fronts.txt');
routeHops = routeData(1,:);
routeTrnsCst = routeData(2,:);
len = length(routeData);
sumHops = 0;
sumTrnsCst = 0;
for i = 1:len
    sumHops = sumHops + routeHops(1,i);
    sumTrnsCst = sumTrnsCst + routeTrnsCst(1,i);
end
meanHops = sumHops/len;
meanTrnsCst = sumTrnsCst/len;
sumVarHops = 0;
sumVarTrnsCst = 0;
for i = 1:len
    var = routeHops(1,i) - meanHops;
    sumVarHops = sumVarHops + (var*var);
    var = routeTrnsCst(1,i) - meanTrnsCst;
    sumVarTrnsCst = sumVarTrnsCst + (var*var);
end
sdHops = sqrt(sumVarHops/len);
sdTrnsCst = sqrt(sumVarTrnsCst/len);


ro = [-0.5 0.0 0.5];
fTcHc = zeros(3,len);
A = zeros(1,len);
B = zeros(1,len);
for i = 1:3
    coeff = 1/(2*pi*sdHops*sdTrnsCst*(sqrt(1-ro(1,i)*ro(1,i))));
    exCoeff = 1/(2*(1-ro(1,i)*ro(1,i)));
    for j = 1:len
       A(1,j) = (routeTrnsCst(1,j) - meanTrnsCst)/sdTrnsCst;
       B(1,j) = (routeHops(1,j) - meanHops)/sdHops;
       fTcHc(i,j) = coeff*exp(exCoeff*((A(1,j)*A(1,j) + B(1,j)*B(1,j)) - (2*ro(1,i)*(A(1,j)*B(1,j)))));
    end
end
% fTcHc

figure(5);
clf;
set(gcf, 'Color', 'white'); % white bckgr
hold on;
% subplot(1,2,1);
% for i = 1:3
%     for j = 1:len
%         plot(routeHops(1,j),fTcHc(i,j),'O','MarkerEdgeColor','w','MarkerFaceColor','k','MarkerSize', 15)%,'Linewidth', 2);
%     end
% end
plot(routeHops, fTcHc(1,:),'-.kO','MarkerEdgeColor','k','MarkerFaceColor','w','MarkerSize', 15,'Linewidth', 2);
plot(routeHops, fTcHc(2,:),'-.kD','MarkerEdgeColor','k','MarkerFaceColor','w','MarkerSize', 15,'Linewidth', 2);
plot(routeHops, fTcHc(3,:),'-.kS','MarkerEdgeColor','k','MarkerFaceColor','w','MarkerSize', 15,'Linewidth', 2);
%axis([0, 22, 0, 3e-5]);
axis([0, 22, 0, 1.5e-4]);
%axis([0, 22, 0, 1.2e-3]);
xlabel('h_c -->');
ylabel(' f(t_c, h_c) -->');
legend('\rho = -0.5','\rho = 0','\rho = +0.5');

figure(6);
clf;
set(gcf, 'Color', 'white'); % white bckgr
hold on;
% subplot(1,2,2);
% for i = 1:3
%     for j = 1:len
%         plot(routeTrnsCst(1,j),fTcHc(i,j),'O','MarkerEdgeColor','w','MarkerFaceColor','k','MarkerSize', 15)%,'Linewidth', 2);
%     end
% end
plot(routeTrnsCst,fTcHc(1,:),'-.kO','MarkerEdgeColor','k','MarkerFaceColor','w','MarkerSize', 15,'Linewidth', 2);
plot(routeTrnsCst,fTcHc(2,:),'-.kD','MarkerEdgeColor','k','MarkerFaceColor','w','MarkerSize', 15,'Linewidth', 2);
plot(routeTrnsCst,fTcHc(3,:),'-.kS','MarkerEdgeColor','k','MarkerFaceColor','w','MarkerSize', 15,'Linewidth', 2);
%axis([1e4, 7e4, 0, 3e-5]);
axis([1e4, 7e4, 0, 1.5e-4]);
%axis([1e4, 7e4, 0, 1.2e-3]);
hold off;
xlabel('t_c -->');
ylabel(' f(t_c, h_c) -->');
legend('\rho = -0.5','\rho = 0','\rho = +0.5');


figure(7);
clf;
set(gcf, 'Color', 'white'); % white bckgr
% hold on;
subplot(2,1,1);
plot(routeTrnsCst,A,'-.kO','MarkerEdgeColor','k','MarkerFaceColor','w','MarkerSize', 15,'Linewidth', 2);
subplot(2,1,2);
plot(routeHops,B,'-.kO','MarkerEdgeColor','k','MarkerFaceColor','w','MarkerSize', 15,'Linewidth', 2);

% for i = 1:len    
%     %plot3(routeHops(1,i),routeTrnsCst(1,i),fTcHc(1,i),'O','MarkerEdgeColor','w','MarkerFaceColor','k','MarkerSize', 15)%,'Linewidth', 2);
% end
% %[x,y] = meshgrid(routeHops,routeTrnsCst);
% %z=griddata(routeHops,routeTrnsCst,fTcHc,x,y);
% % x=meshgrid(routeHops);
% % y=meshgrid(routeTrnsCst);
% % z=meshgrid(fTcHc);
% % mesh(x,y,z);
% %hold on;
% %plot3(x,y,z);
% %hold off;
% %plot3(routeHops,routeTrnsCst,fTcHc);%,'O','MarkerEdgeColor','w','MarkerFaceColor','k','MarkerSize', 15)%,'Linewidth', 2);
% %axis([1e4, 7e4, 0, 3e-5]);
% xlabel(' (h_c - \mu_h_c)/\sigma_h_c -->');
% ylabel(' (t_c - \mu_t_c)/\sigma_t_c -->');
% zlabel('Z -->');
%grid on
%axis square;


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