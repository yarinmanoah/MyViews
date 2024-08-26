# MyViews

MyViews is an Android Library that allows users to use special LoadingButton,CircleProgress and InitialsIndicator.

## Features

- LoadingButton
- CircleProgress
- InitialsIndicator

## Installation

To use this Library:
1. Go to Module :app gradle to dependencies.
2. 
  ```sh
    implementation(project(":mylibrary"))
  ```
This will install all the necessary dependencies for the project.


## Usage

1. Open the project in AndroidStudio.
2. Build and run the project on a simulator or a physical device.

## Code Overview

### LoadingButton
![image](https://github.com/user-attachments/assets/a9666b79-00a8-4d4c-80e3-276b22d51165)
![image](https://github.com/user-attachments/assets/4b73840a-f276-4343-9789-1749f705ebfa)


The `LoadingButton` class is a custom Android UI component that combines a `MaterialButton` with a `CircularProgressIndicator` to create a button that displays a loading indicator when clicked. This component supports customization of text, background color, stroke color, text color, progress indicator color, stroke size, text size, and corner radius through XML attributes or setter methods. It inflates a custom layout, updates the view based on the provided attributes, and handles the transition between the button text and the loading indicator when the button is clicked.

### CircleProgress
![image](https://github.com/user-attachments/assets/d28bf8a6-a2f1-4087-9100-af648a25c4ac)
![image](https://github.com/user-attachments/assets/2f065c78-187d-41d2-a039-32ab94c434f4)


The `CircleProgress` class is a custom Android UI component extending `AppCompatTextView`, designed to display a circular progress indicator using the `Canvas` class. It allows customization of attributes like maximum steps, current step, stroke size, background color, and stroke color. The component uses `ValueAnimator` to animate the progress with an `AccelerateDecelerateInterpolator`. It draws a circle and an arc representing the progress, centered within the view. Methods are available to update the progress, stroke color, and background color dynamically while maintaining a consistent circular shape.

### InitialsIndicator
![image](https://github.com/user-attachments/assets/e6f07a6e-dfbe-4702-80da-1333493aef2d)
![image](https://github.com/user-attachments/assets/0f4df2ee-464d-4d80-86c2-61c2d4fca8a0)


The `InitialsIndicator` class is a custom Android UI component designed to display user initials or a profile image within a customizable shape (circle or rectangle). It uses a `TextView` for initials and an `ImageView` for profile images, with customization options for text color, text size, background color, and text style. The background shape can be set to either circular or rectangular, and the component dynamically adjusts its size based on the measured dimensions. It also integrates with Glide to load profile images from URLs.

## Video Demonstration

https://github.com/user-attachments/assets/0e253aa7-84c7-4601-ba2e-87d4067e0c00

## License

Copyright (c) 2024 Yarin Manoah 
