function hw1_5
    n10 = hw1f(10);
    n30 = hw1f(30);
    n100 = hw1f(100);
    
    n10r = real(myDTFS(n10));
    n10i = imag(myDTFS(n10));
    n30r = real(myDTFS(n30));
    n30i = imag(myDTFS(n30));
    n100r = real(myDTFS(n100));
    n100i = imag(myDTFS(n100));
    
    
    axis = 0:(length(n10r)-1);
    figure,
    subplot(2,3,1);
        stem(axis, n10r);
        xlabel('n');
        ylabel('Real Values of n10_{n}');
        title('Real part of myDTFS(n10)');
        legend('n10r');
    subplot(2,3,4);
        stem(axis, n10i);
        xlabel('n');
        ylabel('Imaginary Values of n10_{n}');
        title('Imag part of myDTFS(n10)');
        legend('n10i');
    
    axis = 0:(length(n30r)-1);
    subplot(2,3,2);
        stem(axis, n30r);
        xlabel('n');
        ylabel('Real Values of n30_{n}');
        title('Real part of myDTFS(n30)');
        legend('n30r');
    subplot(2,3,5);
        stem(axis, n30i);
        xlabel('n');
        ylabel('Imaginary Values of n30_{n}');
        title('Imag part of myDTFS(n30)');
        legend('n30i');
    
    axis = 0:(length(n100r)-1);
    subplot(2,3,3);
        stem(axis, n100r);
        xlabel('n');
        ylabel('Real Values of n100_{n}');
        title('Real part of myDTFS(n100)');
        legend('n100r');
    subplot(2,3,6);
        stem(axis, n100i);
        xlabel('n');
        ylabel('Imaginary Values of n100_{n}');
        title('Imag part of myDTFS(n100)');
        legend('n100i');
end
    
    