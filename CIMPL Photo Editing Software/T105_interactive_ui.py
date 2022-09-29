"""
Course: ECOR 1042-D
Term: Winter 2021
---
Group: T105
Student 1: Cade Theriault
Student Number 1: 101 192 252
Student 2: Nahreen Tahmid
Student Number 2: 101 200 137
"""

from T105_image_filters import *

# Functions  
def menu():
    
    print()
    print("L)oad image S)ave-as",end="\n")
    print("3)-tone X)treme contrast T)int sepia P)osterize",end="\n")
    print("E)dge detect D)raw curve V)ertical flip H)orizontal flip",end="\n")
    print("Q)uit",end="\n\n")
    
    userinput = input(": ").upper()
    return userinput


# Main Script

userinput = menu()
options = ["L","S","3","X","T","P","E","D","V","H","Q"]

while userinput != "Q": # Main Loop, iterates until quit.
    
    # If statement to ensure an image is loaded prior to the use of any filters.
    if userinput == "L":
        
        # Loop to check for quit after image is loaded.
        while userinput != "Q":
            
            # If statement containing the filters and other commands.
            if userinput == "L":
                image = load_image(choose_file())
        
            elif userinput == "S":
                print("Save as")
                save_as(image)
                
            elif userinput == "3":
                print("Three tone")
                image = three_tone(image, "blood", "lemon", "gray")
                
            elif userinput == "X":
                print("Extreme Contrast")
                image = extreme_contrast(image)
                
            elif userinput == "T":
                print("Sepia tint")
                image = sepia(image)
                
            elif userinput == "P":
                print("Posterize")
                image = posterize(image)
                
            elif userinput == "E":
                threshold = int(input("Enter a threshold: "))
                image = detect_edges(image, threshold)
                
            elif userinput == "D":
                print("Draw a curve")
                image, borders = draw_curve(image, "cyan", None)
                print(borders)
                
            elif userinput == "V":
                print("Vertical flip")
                image = flip_vertical(image)
                
            elif userinput == "H":
                print("Horizontal flip")
                image = flip_horizontal(image)
                
            elif userinput == "Q":
                print("Quiting. . .")
            
            else:
                print("No such command")
                
            show(image)
            userinput = menu() 
            
    elif (userinput != "L") and (userinput in options):
        print("No image loaded")
        userinput = menu()
        
    else:
        print("No such command")
        userinput = menu()
     


