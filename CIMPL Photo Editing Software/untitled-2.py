"""
Cade Theriault
101 192 252
"""


from Cimpl import *
from unit_testing import check_equal

def bhFilter(original: Image) -> Image:
    """
    Cade Theriault
    101 192 252
    ---------------
    Filter functions that alters the rgb values of each pixel.
    Returns an image with: red values that are 1.5 times the original values,
    but does not pass 255, green values that is less than 55 of the original 
    values, but does not go below 0, blue values are half of the original red
    values.
    Assumptions: Cimpl.py, unit_testing.py are in the same folder
    
    >>> image1 = load_image(choose_file())
    >>> filtered_image = bhFilter(image1)
    >>> show(filtered_image)
    
    
    """
    
    
    new_image = copy(original)
    
    for pixel in original:
        x, y, (r, g, b) = pixel
        
        if (r * 1.5) > 255: # Check if new red is greater than 255
            red = 255
        else:
            red = (r * 1.5)
        
        if (55 - g) < 0: # Check if new green is negative
            green = 0
        else:
            green = (55 - g)
        
        blue = (r * 0.5)
        
        new_color = create_color(red,green,blue)
        set_color(new_image, x, y, new_color)
        
    return new_image


def test_bhFilter():
    """
    Cade Theriault
    101 192 252
    ---------------
    Test function for bhFilter().
    Creates an image that is identical to what is expected from the filter
    and compares it to an image that is manipulated by the filter.
    Checks each pixel to see if they are identical.
    
    
    >>> test_bhFilter()
    
    """
    
    # Random Colors to test - With the extremes to test the limits
    created_image = create_image(7,1)
    set_color(created_image, 0, 0, create_color(0, 0, 0))
    set_color(created_image, 1, 0, create_color(255, 31, 182)) # G < 55
    set_color(created_image, 2, 0, create_color(13, 204, 73)) # G > 55
    set_color(created_image, 3, 0, create_color(127, 50, 17)) 
    set_color(created_image, 4, 0, create_color(200, 102, 97)) 
    set_color(created_image, 5, 0, create_color(24, 203, 93))
    set_color(created_image, 6, 0, create_color(254, 13, 104)) # Max Red
    
    # Image identical to what is expected from the filter. 
    expected_image = create_image(7,1)
    set_color(expected_image, 0, 0, create_color(0, 55, 0))
    set_color(expected_image, 1, 0, create_color(255, 24, 127))
    set_color(expected_image, 2, 0, create_color(19, 0, 6))
    set_color(expected_image, 3, 0, create_color(190, 5, 63))
    set_color(expected_image, 4, 0, create_color(255, 0, 100))
    set_color(expected_image, 5, 0, create_color(36, 0, 12))
    set_color(expected_image, 6, 0, create_color(255, 42, 127))    
    
    bh_image = bhFilter(created_image)
    
    print("")
    print('Testing bhFilter() . . .')
    
    # Using imported function, check_equal, to check if the rgb values 
    # of the pixels are the same.     
    for x, y, col in bh_image:  
        check_equal('Checking pixel @(' + str(x) + ', ' + str(y) + ')',
                     col, get_color(expected_image, x, y))    
    

image1 = load_image(choose_file())
filtered_image = bhFilter(image1)
show(filtered_image)

test_bhFilter()
