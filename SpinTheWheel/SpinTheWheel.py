import random

# display functions
def display_wheel():

    horizontal_line()

    for i in range(15):
        if i == 3:
            top_description(i)
        elif i == 13:
            top_middle_description(i)
        else:
            upper_vertical_lines(i)

    horizontal_line()

    for i in range(15):
        if i == 3:
            bottom_middle_description(i)
        elif i == 13:
            bottom_description(i)
        else:
            lower_vertical_lines(i)

    horizontal_line()


def horizontal_line():
    print("-" * 35)


def top_description(num):
    print("|", end="")
    print(" " * num, end="")
    print("\\", end="")
    print("   $1000    |   -1 turn  ", end="")
    print("/", end="")
    print(" " * num, end="")
    print("|", end="\n")


def top_middle_description(num):
    print("|", end="")
    print("   $1000     ", end="")
    print("\\ ", end="")
    print(" | ", end="")
    print(" /", end="")
    print("     $1000   ", end="")
    print("|", end="\n")


def upper_vertical_lines(num):
    print("|", end="")
    print(" " * num, end="")
    print("\\", end="")
    print(" " * (15-num), end="")
    print("|", end="")
    print(" " * (15-num), end="")
    print("/", end="")
    print(" " * num, end="")
    print("|", end="\n")


def bottom_middle_description(num):
    print("|", end="")
    print("   $1000    ", end="")
    print("/  ", end="")
    print(" | ", end="")
    print("  \\", end="")
    print("   -1 turn  ", end="")
    print("|", end="\n")


def bottom_description(num):
    print("|", end="")
    print(" " * (15-num), end="")
    print("/", end="")
    print("   -1 turn   |    $1000    ", end="")
    print("\\", end="")
    print(" " * (15-num), end="")
    print("|", end="\n")


def lower_vertical_lines(num):
    print("|", end="")
    print(" " * (15-num), end="")
    print("/", end="")
    print(" " * num, end="")
    print("|", end="")
    print(" " * num, end="")
    print("\\", end="")
    print(" " * (15-num), end="")
    print("|", end="\n")

display_wheel()

player_spins = 5 # Number of times to spin

STR_SPIN = "spin" # To start wheel spin

money = 0

while (player_spins > 0): # Game loop
    
    spin_str = input("\nType 'spin' to spin!\n")
    
    if(spin_str.lower() == STR_SPIN):
        
        player_spins -= 1
        
        sow = random.randint(1,8) # Random spin of wheel
        
        if (sow == 1 or sow == 2 or sow == 4 or sow == 6 or sow == 8 ):
            
            print("\nNow choose one of the following questions to answer: (type number)")
            print("\n1. What is 1 + 1 ?")
            print("\n2. What file type does python use for its code?")
            print("\n3. What is the temperature of the sun? (in K)")
            
            choice = input("\n")
            
            if(choice == "1"):
                print("\nAnswer the following: What is 1 + 1 ??")
                answer = input("\n")
                
                if (answer == "2" or answer.lower() == "two"):
                    print("That's Correct!")
                    money += 1000
                    print("\nYou got $1000!")                    
                else:
                    print("I am sorry, but that is incorrect.")
                    
            if(choice == "2"):
                print("\nAnswer the following: What file type does python use for its code?")
                answer = input("\n")
                
                if (answer == ".py" or answer == "py"):
                    print("That's Correct!")
                    money += 1000
                    print("\nYou got $1000!")                    
                else:
                    print("I am sorry, but that is incorrect.")   
            if(choice == "3"):
                print("\nAnswer the following: What is the temperature of the sun? (in K)")
                answer = input("\n")
                
                if (answer == "5778"):
                    print("That's Correct!")
                    money += 1000
                    print("\nYou got $1000!")                    
                else:
                    print("I am sorry, but that is incorrect.")            
                
        elif (sow == 3 or sow == 5 or sow == 7):
            if (money > 0):
                money = money * 0.95
            
            player_spins -= 1
            print("\nAwh man! You lose an extra turn!")
            print("\nAnd you will be deducted 5% of your money earned")
        
        print("Number of spins left: " + str(player_spins))
        print("Money earned: " + str(money))
        
        
    