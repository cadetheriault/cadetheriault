"""
Course: ECOR 1042-D
Term: Winter 2021
---
Group: T105
Student 1: Coralie Roy
Student Number 1: 101 155 360
Student 2: Elizabeth John
Student Number 2: 101 190 317
---
"""
from T105_image_filters import *
from Cimpl import *

filename = choose_file()
batch_file = open(filename)

for line in batch_file:
    items = line.split()
    print(items)
    image = load_image(items[0]) 
    for item in line:
        if item == '3':
            image = three_tone(image, "blood", "lemon", "grey")
            save_as(image, items[1])       
        if item == 'X':
            image = extreme_contrast(image)
            save_as(image, items[1])
        if item == 'T':
            image = sepia(image)
            save_as(image,items[1])
        if item == 'P':
            image = posterize(image)
            save_as(image, items[1])
        if item == 'V':
            image = flip_vertical(image)
            save_as(image, items[1])
        if item == 'H':
            image = flip_horizontal(image)
            save_as(image, items[1])
        if item == 'E':
            image= detect_edges(image, 15)
            save_as(image, items[1])   
            
batch_file.close()