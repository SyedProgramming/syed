# Quiz generator project
import random
import EasyQuestions as eq
import HardQuestions as hq

print("""
***************************                                                                          
| Welcome to the Math Quiz |                                                                                      
***************************""")

# Selection of mode (Easy/Difficult)
difficulty_mode = input("Please select the difficulty mode : \n\t Press E for Easy \n \t Press H for Hard \n")
print("Mode selected : " + difficulty_mode)

if(difficulty_mode.lower() == 'e'):
    eq.runEasyQuiz()
elif(difficulty_mode.lower() == 'h'):
    hq.runHardQuiz()


else :
 print("\n**************************************")
 print("Please select a valid mode either E/H")
 print("**************************************")



