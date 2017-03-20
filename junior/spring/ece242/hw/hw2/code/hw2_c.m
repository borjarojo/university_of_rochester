function hw2_c
    %parameters
    T = .01;            %given
    t = -.04:.0001:.04; %given
    
    %signals
    m = hw2_m(t, T);    %message
    
    f = 300;
    c = cos(2*pi*f*t);  %carrier
    s = sin(2*pi*f*t);
    
    lsbP = m.*c + hilbert(m).*s; %lower-sideband
    usbP = m.*c - hilbert(m).*s; %upper-sideband
    lsbPFFT = fft(lsbP);
    usbPFFT = fft(usbP);
    
    lsbF = fft(m.*c);
    usbF = fft(m.*c);
    l = length(lsbF)
    
    %filter
    for n = 1:l
        if (n-l < -f) || (n-l > f)
            lsbF(n) = 0;
        end
        if (n-l > -f) && (n-l < f)
            usbF(n) = 0;
        end
    end
    
    figure;
    subplot(2,2,1);
    stem(t, lsbPFFT);
    title('Lower-Sideband');
    subplot(2,2,3);
    stem(t, usbPFFT);
    title('Upper-Sideband');
    
    subplot(2,2,2);
    stem(t, lsbF);
    title('Lower-Sideband');
    subplot(2,2,4);
    stem(t, usbF);
    title('Upper-Sideband');
end