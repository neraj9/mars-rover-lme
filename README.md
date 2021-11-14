Improvements needed:

1) MarsRoverScentManager usage as a singleton bean is wrong.
Another incoming feed will create issues as the other feed will have different boundaries but the previous set of lost scents are being used
If I had more time, I would have used a prototype instance of MarsRoverScentManager for each input feed.

2) I would have liked to code and test for edge scenarios like invalid inputs