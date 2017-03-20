%This is a function to create the f(t) defined
%in the HW1.5 of ECE 242 with variable length
function a = hw1f(N)
    a = zeros(N);
    for x = 0:N
        if x < (N/2)
            a(x+1) = -1;
        else
            a(x+1) = 1;
        end
    end
end