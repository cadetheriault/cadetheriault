Copyright 2021 Carleton University

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.


ECOR1042 Winter 2021 T105

Photo-editing program Project Version 1.0 25/02/2021

Contact Information:
-------------------
Cade Theriault
Email: cadetheriault@cmail.carleton.ca

Description:
------------

-The project is a photo-editing program that allows the user to load images, apply image filters to the images and 
save the final image. The user interfaces allow the user to easily apply filters to the chosen image.

-The project is made up of eight files:
	Cimpl.py			point_manipulation.py
	simple_Cimpl_filters.py		unit_testing.py
	T105_batch_ui.py		T105_image_filters.py
	T105_interactive_ui.py		batch_sample.txt

Installation:
-------------

Python 3.9.1 or later must be installed
numpy must be installed using "pip install numpy" in command prompt or terminal
pillow must be installed using "pip install Pillow" in command prompt or terminal

Usage:
------

> T105_interactive_ui.py

When prompted, load an image, and apply any filter in any order to the image. The image can be saved at any time 
and a new image can be loaded at any time as well. If the input does not correspond to a filter or listed command, 
the program will prompt for a new input. The new image will be shown after every filter is applied.

> T105_batch_ui.py

When prompted, choose a text file, i.e. batch_sample.txt file. The file will be used to apply filters onto an image and save the
resulting images. The new image can be seen by looking at the folder containing the program.
- The content of the batch_sample.txt contains this format ["image file"] ["title for new image"] [filters].
	The square brackets show what should be written on one line of the text file to execute.
	There can be any number of filters on the line to be applied, there is no error control, therefore if a command
	is inputted that does not match a filter the program will fail.


Credits:
--------

T105_batch_ui.py		
- Coralie Roy -> recorded as author 	Student 1: Coralie Roy
					Student Number 1: 101 155 360

- Elizabeth John -> recorded as author	Student 2: Elizabeth John
					Student Number 2: 101 190 317

T105_interactive_ui.py
- Cade Theriault -> recorded as author 	Student 1: Cade Theriault
					Student Number 1: 101 192 252

- Nahreen Tahmid -> recorded as author	Student 2: Nahreen Tahmid
					Student Number 2: 101 200 137

T105_image_filters.py
- Cade Theriault -> recorded as author 	Student 1: Cade Theriault
					Student Number 1: 101 192 252

- Nahreen Tahmid -> recorded as author	Student 2: Nahreen Tahmid
					Student Number 2: 101 200 137

- Elizabeth John -> recorded as author	Student 3: Elizabeth John
					Student Number 3: 101 190 317

- Coralie Roy -> recorded as author 	Student 4: Coralie Roy
					Student Number 4: 101 155 360








