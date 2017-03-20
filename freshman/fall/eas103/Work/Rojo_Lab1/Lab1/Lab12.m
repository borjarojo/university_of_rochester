%Make a sound that has a out of tune sound
clear
close all
A = 1.5;
f1 = 220;
phi1 = 0;
B = 1;
w = 30;
f2 = 440 + w;
phi2 = pi/3;
R = 44100;
t = 0:1/R:3;
x = A*sin(2*pi*f1*t + phi1);
y = B*sin(2*pi*f2*t + phi2);
z = x + y;
plot(t(1:R),z(1:R));
zlim([0,2/f2]);
soundsc(z,R);

