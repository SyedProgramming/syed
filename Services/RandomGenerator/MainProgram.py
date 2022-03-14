# Quiz generator project
import random
import EasyQuestions as eq
import HardQuestions as hq
import pyfiglet as pyf

ascii_banner_1 = pyf.figlet_format("Hello Everyone...!")
ascii_banner_2 = pyf.figlet_format("Welcome to the Math Quiz", font='digital')
print(ascii_banner_1)
print(ascii_banner_2)
# Selection of mode (Easy/Difficult)
difficulty_mode = input("Please select the difficulty mode : \n\t Press E for Easy \n \t Press H for Hard \n")

if(difficulty_mode.lower() == 'e'):
    print("Mode selected : Easy")
elif (difficulty_mode.lower() == 'h'):
    print("Mode selected : Hard")
else:
    print("")


if(difficulty_mode.lower() == 'e'):
    eq.runEasyQuiz()
elif(difficulty_mode.lower() == 'h'):
    hq.runHardQuiz()


else :
 print("\n**************************************")
 print("Please select a valid mode either E/H")
 print("**************************************")



