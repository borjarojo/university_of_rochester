"""CSC 161: Lab 3

This program calculates square root using Newton's Method.

Borja Rojo
Lab Section TR 2:00-3:15pm
Fall 2015
"""

import math


def main():
    # 1.Intro print
    print("--Calculate the square-root of a number using Newton's method!--\n")

    # 2.Query Number
    x = eval(input("What number would you like to find the square-root of? "))

    # 3.Query Guesses
    gloop = eval(input("How many times would you like to improve the guess? "))

    # Evalutation
    guess = x / 2   # Initial guess
    diff = math.sqrt(x) - guess	# Calculate difference
    for i in range(gloop):
        guess = (guess + (x / guess)) / 2 	# Newton's Method
        diff = math.sqrt(x) - guess
        # Form for printing the table
        # 'loop number'. 'int form of the guess' 'absolute value of difference'
        print((i + 1), ". ", int(guess), " ", abs(diff), sep="")

    # 4.Print result
    print("My guess for the square-root of ", x, " is ", guess, sep="")
    print("The difference between my guess and the real result is ", diff)

main()
