function hw2_b
    %parameters
    T = .01;            %given
    t = -.04:.0001:.04;   %given
    
    %signals
    m = hw2_m(t, T);    %message
    
    f = 300;
    c = cos(2*pi*f*t);  %carrier
    
    x = myDSBSC(m, c);  %modulated signal
    X = fft(x);
    
    y = x.*c;           %carrier-reinsertion
    Y = fft(y);         %fft lowpasses
    
    
    figure;
    subplot(3,4,[1,2,3,4]);
    plot(t, x, 'blue');
    hold on;
    plot(t, m, 'red');
    hold on;
    plot(t, c, 'green');
    xlabel('t');
    ylabel('');
    title('Superimposed signals');
    legend('Message', 'Carrier', 'Modulated');
    
    subplot(3,4,5);
    stem(t, real(X));
    xlabel('\omega');
    ylabel('X(\omega)');
    title('Modulated signal FFT: Real');
    
    subplot(3,4,6);
    stem(t, imag(X));
    xlabel('j\omega');
    ylabel('X(\omega)');
    title('Modulated signal FFT: Imaginary');
    
    subplot(3,4,[9,10]);
    plot(t, x);
    xlabel('t');
    ylabel('u(t)');
    title('Modulated signal in Time');
    
    subplot(3,4,7);
    stem(t, real(Y));
    xlabel('\omega');
    ylabel('Y(\omega)');
    title('Coherent Demodulation FFT: Real');
    
    subplot(3,4,8);
    stem(t, imag(y));
    xlabel('j\omega');
    ylabel('Y(\omega)');
    title('Coherent Demodulation FFT: Imaginary');
    
    subplot(3,4,[11, 12]);
    plot(t, y);
    xlabel('t');
    ylabel('m(t)');
    title('Demodulated signal in Time');
end