% % format long;
% % arrRate=60;
% % %handRate=10;
% % R=7;%57;
% % mu=100;
% % S=6;
% % 
% % sum=0.0;
% % sum2=0.0;
% % X=1:R;
% % 
% % for count=1:R
% %     %Code for calculating no. of mobile users
% % 
% %     %%/Code for handoff rate with param R
% %     syms j
% %     sum=symsum((2*j+1), 1, count)
% %     handRate= arrRate*mu/((mu-arrRate)*sum)
% %     %%/
% %     sum=0.0;
% % 
% %     N(count)=handRate/(arrRate-handRate);
% % 
% %     %Code for calculating Blocking Probability
% %     rate=arrRate+handRate;
% % 
% %     a1=power(rate,S);
% %     a2=factorial(S)
% %     a3=power(mu,S)
% %     a=a1/(a2*a3)
% %     for i=1:S
% %         b1=power(rate,i);
% %         b2=factorial(i)
% %         b3=power(mu,i)
% %         b=b1/(b2*b3)
% %         sum=sum+b;
% %     end
% %     P_blk(count)=a/sum;
% % 
% %     %Eof Blocking Probability
% %     sum2=sum2+N(count)*P_blk(count)
% % 
% %     usr_blk(count)=sum2;
% % end
% % 
% % figure;
% % subplot(3,2,1);
% % plot(N,usr_blk);
% % grid on;
% % title('Total no. of MUs present vs No. of MUs blocked');
% % xlabel('N_i -->');
% % ylabel('P -->');
% % 
% % subplot(3,2,2);
% % plot(P_blk,usr_blk);
% % grid on;
% % title('Blocking probability vs No. of MUs blocked');
% % xlabel('B_o_i -->');
% % ylabel('P -->');
% % 
% % subplot(3,2,[3 4]);
% % plot(X,usr_blk);
% % grid on;
% % title('Radial Distance vs No. of MUs blocked');
% % xlabel('R -->');
% % ylabel('P -->');






format long;
arrRate=60;
handRate=10;
R=7;%57;
mu=40;
S=6;

rate=arrRate+handRate;
sum=0.0;


for count=1:R
    %Code for calculating no. of mobile users
    syms j
    sum=symsum(power(2,(j-1)), 1, count);
    N=6*rate*sum;
    
    for i=1:S
        a1=power(rate,i);
        a2=factorial(i);
        a3=power(mu,i);
        a=a1/(a2*a3);
        for j=1:i
            b1=power(rate,j);
            b2=factorial(j);
            b3=power(mu,j);
            b=b1/(b2*b3);
            sum=sum+b;
        end
        P_blk(i)=a/sum;
    end
    %End of Blocking Probability
    
    sum2=sum2+N(count)*P_blk(count)
end