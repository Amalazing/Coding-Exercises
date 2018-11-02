userInput = 0   # A variable to hold in user choice
userString = "" # A variable to hold the user's string that we will reverse
stringLenth = 0 # A variable to hold the length of the user's string
result = "" # A variable where we store the reversed string
needToRunProgram = True # A boolean to keep the program running

while needToRunProgram:   # This loop continues the program over and over until the user decides to quit

    print("1 : Enter String to reverse it?")
    print("2 : Quit?")

    userInput = int(input())    # Taking in User input for choice

    if (userInput == 1):    # User selcted to reverse string

        print("Please enter the string")
        print()

        userString = input()  # Taking in the users input
        stringLenth = len(userString) - 1   # This sets stringLength equal to the number of characters in the user's String : The function "Len" means length

        while (stringLenth >= 0):   # In this loop we add the last letter of the user's input to an empty string then the next and next until done

            result = result + userString[stringLenth]   # Adding the letter at a specific index of the user's string to the empty string
            
            stringLenth = stringLenth - 1   # This changes which index we access by minus 1 so that on the next loop it will get the letter before the one on the last loop
    
    print(result)
    result = "" # Clear the result variable for when the program is run multiple times in one session

    if (userInput == 2):    # The user has elected to end the program
        needToRunProgram = False  # Change the while loop condition to false thus ending the program