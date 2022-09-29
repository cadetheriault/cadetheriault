"""
Course: ECOR 1042-D
Term: Winter 2021
---
Group: T105
Student 1: Cade Theriault 
Student Number 1: 101 192 252 
Student 2: Nahreen Tahmid
Student Number 2: 101 200 137
Student 3: Elizabeth John
Student Number 3: 101 190 317
Student 4: Coralie Roy
Student Number 4: 101 155 360
---
"""

from Cimpl import *
from unit_testing import check_equal
from simple_Cimpl_filters import grayscale
from point_manipulation import *
import numpy as np

# Auxiliary Functions

def _adjust_component(color: int) -> int: # Posterize
    """
    Nahreen Tahmid 101200137
    Depending on the rgb value, the function
    assigns a midpoint value to the color. 
    
    We assume that the color value is within 
    range of 0-255
    
    >>> _adjust_component(44)
    31
    """
    if 0<=color<=63:
        return 31
    elif 64<=color<=127:
        return 95
    elif 128<=color<=191:
        return 159
    elif 192<=color<=255:
        return 223

def _exhaustive_search(max_x:int, polycoeff: list, val:int)-> int:
    """
    ---
    Group: T105
    Student: Cade Theriault 
    Student Number: 101 192 252 
    ---
    
    Solves for x between 0 and the width.
    Returns None if there is no solution between the bounds.
   
    >>> _exhaustive_search(639,[6.33e-03,-3.80e+00,5.57e+02],0)
    253
    >>> _exhaustive_search(639,[7.24e-04,-1.19e+00,4.51e+02],0)
    590
    >>> _exhaustive_search(639,[7.24e-04,-1.19e+00,4.51e+02],479)
    None
    """
    
    EPSILON = 1 
    guess = 0
    step = 1
    
    y_value = np.polyval(polycoeff, guess) # Getting y value from guess of x.
    
    while (y_value - val) > EPSILON: # Comparing y value to val that is passed.
        guess += step
        y_value = np.polyval(polycoeff, guess)
    
    # Returning guess if it is within bounds.
    if guess > max_x or guess <= 0: 
        return None
    else:
        return guess
    

def _image_border_finding(pixel_x: int, pixel_y: int, polycoeff: list)-> list:
    """
    ---
    Group: T105
    Student: Cade Theriault 
    Student Number: 101 192 252 
    ---
    
    Returns the pixels where the curve intersects the border of the image. 
    
    >>>_image_border_finding()
    """
    x = pixel_x - 1
    y = pixel_y - 1
    
    # y
    func_of_0 = int(np.polyval(polycoeff, 0))
    func_of_x = int(np.polyval(polycoeff, x))
   
    # x
    x_of_func_0 = _exhaustive_search(x, polycoeff, 0)
    x_of_func_max = _exhaustive_search(x, polycoeff, y)
    
    # Top border
    if x_of_func_0 == None:
        top_point = None
    elif x_of_func_0 > 0 or x_of_func_0 < x:
        top_point = (x_of_func_0, 0)
    else: 
        top_point = None
   
    # Bottom border
    if x_of_func_max == None:
        bottom_point = None
    elif x_of_func_max > 0 or x_of_func_max < x:
        bottom_point = (x_of_func_max, y)
    else:
        bottom_point = None
   
    # Left border
    if func_of_0 >= 0 or func_of_0 <= y:
        left_point = (0, func_of_0)
    else:
        left_point = None
   
    # Right border
    if func_of_x >= 0 or func_of_x <= y:
        right_point = (x, func_of_x)
    else:
        right_point = None

    border_points = []
    
    if top_point != None:
        border_points.append(top_point)
    if bottom_point != None:
        border_points.append(bottom_point)
    if left_point != None:
        border_points.append(left_point)
    if right_point != None:
        border_points.append(right_point)

    return border_points


def _interpolation(point_set: list)-> list:
    """
    ---
    Group: T105
    Student: Cade Theriault 
    Student Number: 101 192 252 
    ---
    
    [(4,5),(6,9),(8,2)]
    
    """
    
    x_values = []
    y_values = []
    
    for x, y in point_set: # X and Y values in lists
        x_values.append(int(x))
        y_values.append(int(y))    
    
    n = len(x_values)
    
    if n > 3:
        pl = np.polyfit(x_values,y_values, 2) # Regression 
    else:
        pl = np.polyfit(x_values,y_values, (n - 1)) # Interpolation
    
    return pl


# Milestone 1 Filter Functions

def red_channel(image: Image) -> Image: # Red Filter Function
    """ 
    ---
    Group: T105
    Student: Coralie Roy
    Student Number: 101155360
    ---
    
    Return a new image that contains only the red values of the image that 
    is passed. 
    
    >>> image = load_image(choose_file())
    >>> red_image = red_channel(image)
    >>> show(red_image)
    
    """ 
    red_image = copy(image) # Copy of orignal image.
    for pixel in image:
        x, y, (r, g, b) = pixel # Extracting color and location from pixel.
   
    for pixel in image:
        x, y, (r, g, b) = pixel
        red_colour = create_color(r,0,0) # Creating a new color from the 
                                         # red component.
        set_color (red_image, x, y, red_colour) # Applying only the red 
                                                # component.
     
    return red_image

def green_channel(original: Image) -> Image: # Green Filter Function
    """
    Author: Elizabeth John
    Student Number: 101190317
    
    The function returns a copy of the original image with only 
    the green filter.  Assume that the original image file is already stored in
    the same folder as this script.
    
    >>>green_channel(image)
    Image
    """
    new = copy(original) # Makes a copy of the original image.
    
    for pixel in original:
        x, y, (r, g, b) = pixel  
        green = create_color( 0, g, 0) # Creates the green filter.
        set_color(new, x, y, green) # Sets the color to the green filter for 
                                    # each pixel in the original image.
    
    return new    # Returns the new filtered image.

def blue_channel(image: Image) -> Image: # Blue Filter Function
    
    """
    #Nahreen Tahmid 101200137
   
    Returns a blue copy of the image.
    
    >>> image = load_image(choose_file())
    >>> bluee = blue_channel(image)
    >>> show(bluee)
    """
    blue_image = copy(image)
    
    for pixel in image:
        x, y, (r, g, b) = pixel
      
    for pixel in image:
        x, y, (r, g, b) = pixel
        blue_colour = create_color(0, 0, b) 
        set_color(blue_image, x, y, blue_colour)
    
    return blue_image

def combine(red_image: Image, green_image: Image, blue_image: Image)-> Image:
    """
    ---
    Group: T105
    Student: Cade Theriault 
    Student Number: 101 192 252 
    ---
    Returns an image that has the combined rgb color values of the three images
    that are passed. The three images should have one and only shades of red, 
    blue or green.
    
    An assumption is made that the images are passed in the order of:
    Red, Blue, Green.
    
    >>> red_image = load_image(choose_file())
    >>> green_image = load_image(choose_file())
    >>> blue_image = load_image(choose_file())
    >>> combined_image = combine(red_image, green_image, blue_image)
    >>> show(combined_image)
    
    """
    
    # Setting up empty lists for the values of red, green and blue.
    red = []
    green = []
    blue = []
    
    for pixel in red_image: 
        x, y, (r1, g1, b1) = pixel # Extracting color and location from pixel.
        red.append(r1) # Adds the red value to the list for each pixel.
    
    new_image = copy(red_image) # Copies the red image to work with as a base.
    
    for pixel in green_image: 
        x, y, (r2, g2, b2) = pixel # Extracting color and location from pixel.
        green.append(g2) # Adds the green value to the list for each pixel.
     
    for pixel in blue_image: 
        x, y, (r3, g3, b3) = pixel # Extracting color and location from pixel.
        blue.append(b3) # Adds the blue value to the list for each pixel.
     
    i = 0 # Index for the lists of the red, green, blue values.
    for pixel in new_image:
        x, y, (r4, g4, b4) = pixel # Extracting color and location from pixel.
        new_color = create_color(red[i], green[i], blue[i]) # Creates new color.
        set_color(new_image, x, y, new_color ) # Applies new color.
        i += 1 # Counter to increase the lists' index.
   
    return new_image

# Milestone 2 Filter Functions

def detect_edges(original: Image, threshold: int) -> Image:
    """
    Author/Student Number: Elizabeth John 101190317
    
    Return a new copy of the original image, appearing as a pencil sketch, 
    based on the threshold value.
    >>>detect_edges(image, 3)
    Image
    >>>detect_edges(image, 20)
    Image
    """
    new = copy(original) # makes a copy of the original image
    
    b1 = 0
    b2 = 0
    c1 = 0
    c2 = 0
    contrast = 0
    width = get_width(original) # gets the width of the original image
    height = get_height(original) # gets the height of the original image
    
    
    for x in range(width): # loops over the x values within the width
            
        for y in range(height): # loops over the y values within the height
                
            if y == height - 1:
                # sets the color of the bottom row of pixels of the original 
                # image to white                                          
                set_color(new, x, y, create_color(255,255,255)) 
                continue # skips to the following statements
            c1 = get_color(original, x, y) # gets the color of the bottom pixel
            b1 = (c1[0] + c1[1] + c1[2]) //3 # calculates the brightness of the 
                                             # color at the bottom pixel
            c2 = get_color(original, x , y+1) # gets the color of the top
                                              # pixel
            b2 = (c2[0] + c2[1] + c2[2]) //3 # calculates the brightness of the
                                            # color at the top pixel 
            contrast = abs(b2 - b1) # calculates the contrast of the two pixels
            
            
            if contrast < threshold: # sets the color of the  
                        
                set_color(new, x, y, create_color(255, 255, 255))
                                  
            if contrast > threshold:
                set_color(new, x, y, create_color(0, 0, 0))
    
    return new

def draw_curve(image: Image, str_color: str, pointlist: list)-> tuple:
    
    """
    ---
    Group: T105
    Student 1: Cade Theriault
    Student 2: Nahreen Tahmid 
    Student Number 1: 101 192 252 
    Student Number 2: 101 200 137
    ---
    
    This filter takes a set of points, a colour, and an image and draws a line 
    or curve on the image with the colour inputted. 
    Returns a tuple containg the
    image that was drawn on and a list of points that the curve intersects the
    border of the image.
    
    """
    
    # Displaying size of image
    pixel_x = get_width(image)
    pixel_y = get_height(image)
    
    new_image = copy(image)
    
    str_color.lower()
    
    # Determing the color
    if str_color == "black":
        color = Color(0,0,0)
    elif str_color == "white":
        color = Color(255,255,255)
    elif str_color == "blood":
        color = Color(255,0,0)
    elif str_color == "green":
        color = Color(0,255,0)
    elif str_color == "blue":
        color = Color(0,0,255)
    elif str_color == "lemon":
        color = Color(255,255,0)
    elif str_color == "cyan":
        color = Color(0,255,255)
    elif str_color == "magenta":
        color = Color(255,0,255)
    elif str_color == "gray":
        color = Color(128,128,128)
    else:
        color = Color(0,255,255) # Default color, if input was invalid.
                                 # Color is cyan for simplicity for milestone 3.
        
        
    # Ensure at least 2 points are inputted.
    bool_points = False
    while bool_points == False: 
        num_points = int(input\
                ("Enter how many points would you like to plot: (At least 2) "))
        if num_points >= 2:
            bool_points = True
        else:
            bool_points = False
        
    # Getting X and Y values for the points from user.
    coordinates = list()
    for i in range(num_points):
        x = int(input("Enter the x value for point " + str(i + 1) + ": "))
        y = int(input("Enter the y value for point " + str(i + 1) + ": "))
        point = (x,y)
        coordinates.append(point)
        
    
    sorted_coordinates = sort_points(coordinates) # Coordinates sorted by x.
    polycoeff = _interpolation(sorted_coordinates) # Finding the coefficients.
    border_points = _image_border_finding(pixel_x,pixel_y, polycoeff)

    # Find the corresponding y-coordinate for every x-coordinate of 
    # the image and change the colour of each pixel.

    for x in range (0, pixel_x):
        y_p = round(np.polyval(polycoeff, x))
        if y_p >= 0 and y_p < pixel_y:
            if y_p >= 2 and y_p <= (pixel_y - 3):
                set_color(new_image, x, y_p - 2, color)
                set_color(new_image, x, y_p - 1, color)
                set_color(new_image, x, y_p, color)
                set_color(new_image, x, y_p + 1, color)
                set_color(new_image, x, y_p + 2, color)
            elif y_p == 1:
                set_color(new_image, x, y_p - 1, color)
                set_color(new_image, x, y_p, color)
                set_color(new_image, x, y_p + 1, color)
                set_color(new_image, x, y_p + 2, color)
            elif y_p == 0:
                set_color(new_image, x, y_p, color)
                set_color(new_image, x, y_p + 1, color)
                set_color(new_image, x, y_p + 2, color)
            elif y_p >= 2 and y_p == (pixel_y - 1):
                set_color(new_image, x, y_p - 2, color)
                set_color(new_image, x, y_p - 1, color)
                set_color(new_image, x, y_p, color)
            elif y_p >=2 and y_p == (pixel_y - 2):
                set_color(new_image, x, y_p - 2, color)
                set_color(new_image, x, y_p - 1, color)
                set_color(new_image, x, y_p, color)
                set_color(new_image, x, y_p + 1, color) 

    final_tup = (new_image, border_points)
    
    return final_tup

def flip_horizontal(image: Image) -> Image:
    """
    T105 - Coralie Roy 101155360
    
    Returns a flipped copy of an image, flipped along a vertical line.
    
    >>> image = load_image(choose_file())
    >>> flipped_image = flip_horizontal(image)
    >>> show(flipped_image) 
    """
    flipped_image = copy(image) 
    width = image.get_width()
    height = image.get_height()
    for y in range(0, height):
        for x in range(0, width//2): 
            left_pixels = image.get_color(x, y) # Colour of a pixel located at 
                                                # some distance from the left 
                                                # side of the image.
            right_pixels = image.get_color(width - 1 - x, y) # Colour of a pixel 
                                                             # located at some 
                                                             # distance from the 
                                                             # right side of the
                                                             # image.
            image.set_color(width - 1 - x, y, left_pixels) # Swapping right 
                                                           # pixels with left 
                                                           # pixels.
            image.set_color(x, y, right_pixels) # Swapping left pixels with 
                                                # right pixels.
                                                
    return image

def extreme_contrast(image: Image) -> Image:
    """
    Author: Coralie Roy 
    Student Number: 101155360
    
    Returns a copy of an image in which the contrast between the pixels has been 
    maximized.
    
    >>> image = load_image(choose_file())
    >>> contrast_image = extreme_contrast(image)
    >>> show(contrast_image
    
    """        
    contrast_image = copy(image)
    for pixel in image:
        x, y, (r, g, b) = pixel    
        if r <= 127:
            r = 0            
        if g <= 127:
            g = 0 
        if b <= 127:
            b = 0
        contrast_colour = create_color(r,g,b)
        set_color(contrast_image, x, y, contrast_colour) 
        if r >= 127:
            r = 255            
        if g >= 127:
            g = 255
        if b >= 127:
            b = 255
        contrast_colour = create_color(r,g,b)
        set_color(contrast_image, x, y, contrast_colour)         
  
    return contrast_image

def flip_vertical(image: Image) -> Image: 
    
    """
    #Nahreen Tahmid 101200137
    
    Assumption: There is an image stored in the same folder as 
    this script with the given name
    
    Returns a vertically flipped copy of the image.
    
    >>> image = load_image(choose_file())
    >>> y_flip = flip_vertical(image)
    >>> show(y_flip)
    
    """
    vertical_image = copy(image)
    
    for pixel in image:
        x, y, (r, g, b) = pixel
      
    for pixel in image:
        x, y, (r, g, b) = pixel
        HEIGHT = get_height(image) #gets height of image 
        new_color = get_color(image,x,y) #gets x,y values of pixel 
        set_color(vertical_image, x, HEIGHT-y-1, new_color) # Sets color of 
                                                            # vertical opposite 
                                                            # to itself.
    
    return vertical_image

def posterize(image: Image) -> Image:
    
    """
    Nahreen Tahmid 101200137
    Assumption: There is an image stored in the same folder as 
    this script with the given name
    
    Returns a posterized copy of the image.
    
    >>> image = load_image(choose_file())
    >>> posterized_image = posterize(image)
    >>> show(posterized_image)
    """
    #copies image to a separate variable 
    poster_image = copy(image)
    
    #poisterizes image by changing values of rgb components in pixel  
    for pixel in image:
        x, y, (r, g, b) = pixel
        poster_colour = create_color(_adjust_component(r), \
                                     _adjust_component(g), \
                                     _adjust_component(b)) 
        set_color(poster_image, x, y, poster_colour)
    
    return poster_image

def sepia(og_image: Image)-> Image: 
    """
    ---
    Group: T105
    Student: Cade Theriault 
    Student Number: 101 192 252 
    ---
    Returns an image that is converted to grayscale, and then adds a yellow
    tint to resemble an old photograhic print. 
    
    Imports grayscale from simple_Cimpl_filters to remove the color from
    the image.
    
    >>> image = load_image(choose_file())
    >>> sepia_image = sepia(image)
    >>> show(sepia_image)
    
    """ 
    
    gray_image = grayscale(og_image) # Uses grayscale function and returns
                                     # a colourless image.
    
    final_image = copy(gray_image)   # Copys the gray image to manipulate.
    
    # Setting up empty lists for the values of red, green and blue.
    red = []
    green = []
    blue = []
    
    for pixel in gray_image:
        x, y, (r1, g1, b1) = pixel
        
        if r1 < 63:                   # Check for dark gray.
            r2 = r1 * 1.1             # Adjusting the red component.
            b2 = b1 * 0.9             # Adjusting the blue component.
            
            red.append(r2)
            green.append(g1)
            blue.append(b2)
            
        elif r1 <= 191:               # Check for medium gray.
            r2 = r1 * 1.15            # Adjusting the red component.
            b2 = b1 * 0.85            # Adjusting the blue component.
            
            red.append(r2)
            green.append(g1)
            blue.append(b2)           
            
        elif r1 > 191:                # Check for light gray.
            r2 = r1 * 1.08            # Adjusting the red component.
            b2 = b1 * 0.93            # Adjusting the blue component.
            
            red.append(r2)
            green.append(g1)
            blue.append(b2)            
    
    i = 0 # Index for the lists of the red, green, blue values.
    for pixel in final_image:
        x, y, (r3, g3, b3) = pixel # Extracting color and location from pixel.
        new_color = create_color(red[i], green[i], blue[i]) # Creates new color.
        set_color(final_image, x, y, new_color ) # Applies new color.
        i += 1 # Counter to increase the lists' index.    
    
    return final_image

def three_tone(original: Image, col1: str, col2: str, col3: str) -> Image:
    """
    Author/Student Number : Elizabeth John 101190317
    
    Returns a copy of an image where it only has the three tones col1, col2,
    col3.  Assume that the image file is saved in the same folder as this script
    file.
    
    >>>image = load_image(choose_file()) 
    >>>three_tone(image, "black", "gray", "white")
    Image
    
    >>>image = load_image(choose_file()) 
    >>>three_tone(image,"blue", "lemon", "magenta")
    Image
    
    """
    new = copy(original) # makes a copy of the original image
    col_list = ["black", "white", "blood", "green", "blue", "lemon", 
                "cyan", "magenta", "gray"] #  a list of strings representing 
                                           # colours 
    rgb_list = [(0,0,0),(255, 255, 255), (255, 0, 0), (0, 255, 0), (0, 0, 255),
                (255, 255, 0), (0, 255, 255), (255, 0, 255), (128, 128, 128)]
    # a list of tuples, each representing the rgb components for the 
    # corresponding colours in col_list
    
    c1 = 0
    c2 = 0
    c3 = 0       
            
    brightness = 0
  
    
    for pixel in original:        
        for i in range(len(col_list)):
            x, y, (r, g, b) = pixel  
            brightness = sum((r,g,b)) / 3 # takes the average of each set of 
                                          # rgb components for a pixel to get
                                          # the brightness            
                
            if 0 <= brightness <= 84: # changes the colour to the first colour
                                      # col1 if the brightness at a pixel is 
                                      # between 0 and 84                           
                if col1 == col_list[i]:
                    c1 = create_color(rgb_list[i][0], rgb_list[i][1], 
                                      rgb_list[i][2])                
                    set_color(new, x, y, c1 )
        
            if 85 <= brightness <= 170: # changes the colour to the second 
                                        # colour col2 if the brightness at a 
                                        # pixel is between 85 and 170
                if col2 == col_list[i]:
                    c2 = create_color(rgb_list[i][0], rgb_list[i][1], 
                                      rgb_list[i][2])
                    set_color(new, x, y, c2)
       
            if 171 <= brightness <= 255: # changes the colour to the third 
                                         # colour col3 if the brightness at a 
                                         # pixel if between 171 and 255
                if col3 == col_list[i]:
                    c3 = create_color(rgb_list[i][0], rgb_list[i][1], 
                                      rgb_list[i][2])
                    set_color(new, x, y, c3)
       
    return new # returns the new filtered image




# Main Script

if __name__ == "__main__": # I know it is not required, 
                           # but I chose to use it anyway.

    # Posterize
    image1 = load_image(choose_file())
    posterized_image = posterize(image1)
    show(posterized_image)
    
    # Sepia Tinting
    image2 = load_image(choose_file())
    sepia_image = sepia(image2)
    show(sepia_image)
    
    # Three Tone
    image3 = load_image(choose_file()) 
    three_tone_image = three_tone(image3, "black", "gray", "white")
    show(three_tone_image)
    
    # Extreme Contrast
    image4 = load_image(choose_file())
    contrast_image = extreme_contrast(image4)
    show(contrast_image) 
    
    # Draw Curve
    image5 = load_image(choose_file())
    color_str = (input("Enter a color: "))
    image_and_border_pixels = draw_curve(image5, color_str, None)
    final_image, border_list = image_and_border_pixels
    print(border_list)
    show(final_image)
    
    # Edge Detection
    image6 = load_image(choose_file())
    edged_image = detect_edges(image6, 20)
    show(edged_image)    
    
    # Flip Vertical
    image7 = load_image(choose_file())
    y_flip = flip_vertical(image7)
    show(y_flip)
    
    # Flip Horizontal
    image8 = load_image(choose_file())
    flipped_image = flip_horizontal(image8)
    show(flipped_image) 
    