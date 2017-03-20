"""CSC 161: Lab 2

This program implements a basic interactive calculator

Borja Rojo
Lab Section TR 2:00-3:15
Fall 2015
"""

'''
This is the main method to my program
It is an interactive calculator
The program prompts you for an expresion, and then it prints the evaluation
You can exit by writing a bogus expression or closing the window
'''


def main():
    # Friendly Message
    print("-------------Interactive Calculator-------------")
    print("Hello! This program is an interactive calculator.")
    count = eval(input("How many expressions would you like to calculate?: "))
    print("Write an arithmetic expression to be evaluated.\n")

    # Evaluation loop for 100 expresions
    print("-Expressions-\n")
    for i in range(count):
        x = input("Expression " + str(i+1) + ": ")
        print(x, "=", eval(x), "\n")

main()
