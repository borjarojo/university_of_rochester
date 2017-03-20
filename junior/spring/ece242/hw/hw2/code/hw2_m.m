%{
This fuction will produce a vector with values corresponding to the
function givin in HW2.

It takes a vector, t, that holds the values of the domain, and a
scalar, T, which is the incrementation index of the vector, t.
%}
function y = hw2_m(t,T)
    y = 2*sinc(2*t/T) + sinc((2*t/T) + 1) + sinc(2*t/(T-1));
end