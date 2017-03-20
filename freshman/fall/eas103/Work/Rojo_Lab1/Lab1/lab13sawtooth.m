%Make a Sawtooth Wave using the Fourier series equation
f = 440;        %Fundamental Frequency
nmax = 20000/f;      %set number on components to reach only the audible hearing spectrum
phi = 0;        %starting phase
R = 44100;      %Sample Rate
t = 0:1/R:3;    %Time&points

x = 0;          %Start function

for n = 1:nmax
    x = x + (1/n)*sin(2*pi*n*f*t + phi); % create sawtooth fourier series
end

plot(t(1:R),x(1:R));
xlim([0,2/f]);
soundsc(x,R);