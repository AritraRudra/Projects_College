bufferSize=[10 20 30 40 50 60 70 80 90 100];
x=bufferSize;

% waiting time =50 ms
callsDropped=[117 105 96 84 79 68 57 49 41 36];
y1=callsDropped;

% waiting time =100 ms
callsDropped=[109 92 86 79 68 60 55 49 39 34];
y2=callsDropped;

% waiting time =250 ms
callsDropped=[86 75 68 61 59 58 51 56 49 41];
y3=callsDropped;

% waiting time =500 ms
callsDropped=[59 54 47 40 38 34 30 33 27 30];
y4=callsDropped;

% waiting time =1 s
callsDropped=[21 19 18 20 24 19 16 15 12 11];
y5=callsDropped;

figure(1);
plot(x,y1,'-ok',x,y2,'-xk',x,y3,'-sk',x,y4,'-dk',x,y5,'-^k');
xlabel('Buffer Size -->');
ylabel('No. of calls Dropped  -->');
hleg1 = legend('Avg waiting time = 50 ms','Avg waiting time = 100 ms','Avg waiting time = 250 ms','Avg waiting time = 500 ms','Avg waiting time = 1 s');

set(gcf, 'Color', 'white'); % white bckgr

% export_fig( gcf, ...      % figure handle
%     'Buffer_Size_vs_Calls_Dropped',... % name of output file without extension
%     '-painters', ...      % renderer
%     '-bmp', ...           % file format
%     '-r72' ); 