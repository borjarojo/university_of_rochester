%{
This function takes in two vectors of the same length and creates
a new vector that represents the DSB-SC modulated signal.
%}

function y = myDSBSC(m, c)
    y = m.*c;
end