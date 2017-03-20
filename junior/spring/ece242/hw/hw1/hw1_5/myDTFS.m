function a = myDTFS(x)
    X = transpose(x);   % Transpose for the columns
    N = length(X);      % Get the length and save
    F = zeros(N,N);     % Create a square matrix with the dimesions 
                        % of the passed vector
    % For n and k, 1 thorugh N
    for n = 1:N         
        for k = 1:N
            F(n,k) = exp(1i*(2*pi*(k-1)*(n-1))/N); % Equation
        end
    end
    a = F\X;    % Solve for a
end