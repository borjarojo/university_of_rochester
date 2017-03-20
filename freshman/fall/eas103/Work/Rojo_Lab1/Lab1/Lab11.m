%Make a code set to play a sound with
%the parameters listed below for the function
% Acos(2 pi f t + phi)
clear
close all
a = 1;          %Set amplitude to 1
f = 220;        %Set Frequency to 220  
R = 44100;      %Set sample rate to 44100
t = 0:1/R:1;    %Make time array that is one second long with intervals at the sample rate
phi = pi/3;     %Set phase change to pi/3
x = a*cos(2*pi*f*t + phi);
plot(t(1:R),x(1:R));
soundsc(x,R);