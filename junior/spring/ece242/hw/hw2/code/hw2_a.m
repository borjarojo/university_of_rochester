%{
This function produces the DSB-SC for HW2a.
%}
function y = hw2_a()
    %parameters
    T = .01;            %given
    t = -.04:.0001:.04;   %given
    
    %signals
    m = hw2_m(t, T);    %message
    
    f = 300;
    c = cos(2*pi*f*t);  %carrier
    
    %modulated signal
    y = myDSBSC(m, c);
    Yr = real(fft(y));
    Yi = imag(fft(y));
    
    figure;
    
    subplot(2,2,1)
    stem(t, Yr, 'blue');
    xlabel('\omega');
    ylabel('Y(\omega)');
    title('Fourier Series of the Modulated Signal: Real Parts');
    
    subplot(2,2,2);
    stem(t, Yi, 'red');
    xlabel('j\omega');
    ylabel('Y(\omega)');
    title('Fourier Series of the Modulated Signal: Imaginary Parts');
    
    subplot(2,2,[3,4]);
    plot(t, y, 'blue');
    hold on;
    plot(t, m, 'red');
    hold on;
    plot(t, c, 'green');
    xlabel('t');
    ylabel('y(t)');
    title('Plot of the Modulated signal');
end