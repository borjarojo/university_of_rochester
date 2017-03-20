import math


def main():
    # Intro
    print("This program approximates a square-root.")
    # Query
    number = eval(input("What number would you like to find the square-root of? "))
    # Guesses
    gloop = eval(input("How many times would you like to improve the guess? "))
    # Evalutation
    guess = number / 2
    difference = math.sqrt(number) - guess
    for i in range(gloop):
        # Newton's
        guess = (guess + (number / guess)) / 2 
        difference = math.sqrt(number) - guess
        print((i + 1), ". ", int(guess), " ", abs(difference), sep="")

    # 4.Print result
    print("My guess for the square-root of ", number, " is ", guess, sep="")
    print("The difference between my guess and the real result is ", difference)

main()
