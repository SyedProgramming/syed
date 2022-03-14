import random as rd
from variables import *



score = 0
selected_questions = rd.choices(questions, k=10)

def Question1():
    print("Enter your answer: ")
    print("90 - (-6) = ");
    given_answers['Question1'] = int(input(""))
    if(int(given_answers['Question1']) == int(hard_questions_answers["Question1"])):
        return 10
    else: return 0
def Question2():
    print("Enter your answer: ")
    print("89 + 16 - 2 = ");
    given_answers['Question2'] = int(input(""))
    if(int(given_answers['Question2']) == int(hard_questions_answers["Question2"])):
        return 10
    else:
        return 0
def Question3():
    print("Enter your answer: ")
    print("18 / 2 + 16 = ");
    given_answers['Question3'] = int(input(""))
    if(int(given_answers['Question3']) == int(hard_questions_answers["Question3"])):
        return 10
    else:
        return 0
def Question4():
    print("Enter your answer: ")
    print("(17+2) * 55 = ");
    given_answers['Question4'] = int(input(""))
    if(int(given_answers['Question4']) == int(hard_questions_answers["Question4"])):
        return 10
    else:
        return 0
def Question5():
    print("Enter your answer: ")
    print("99/11+2-4 = ");
    given_answers['Question5'] = int(input(""))
    if(int(given_answers['Question5']) == int(hard_questions_answers["Question5"])):
        return 10
    else:
        return 0
def Question6():
    print("Enter your answer: ")
    print("(30*2)+(89*(5+7)) = ");
    given_answers['Question6'] = int(input(""))
    if(int(given_answers['Question6']) == int(hard_questions_answers["Question6"])):
        return 10
    else:
        return 0
def Question7():
    print("Enter your answer: ")
    print("44*3+3*(8*0) = ");
    given_answers['Question7'] = int(input(""))
    if(int(given_answers['Question7']) == int(hard_questions_answers["Question7"])):
        return 10
    else:
        return 0
def Question8():
    print("Enter your answer: ")
    print("40+30/2  = ");
    given_answers['Question8'] = int(input(""))
    if(int(given_answers['Question8']) == int(hard_questions_answers["Question8"])):
        return 10
    else:
        return 0
def Question9():
    print("Enter your answer: ")
    print("44*2 + 143/1 = ");
    given_answers['Question9'] = int(input(""))
    if(int(given_answers['Question9']) == int(hard_questions_answers["Question9"])):
        return 10
    else:
        return 0
def Question10():
    print("Enter your answer: ")
    print("16/2+(5+6)*(2*4)+3 = ");
    given_answers['Question10'] = int(input(""))
    if(int(given_answers['Question10']) == int(hard_questions_answers["Question10"])):
        return 10
    else:
        return 0
def Question11():
    print("Enter your answer: ")
    print("17+10+29+30*3*3*3/2 = ");
    given_answers['Question11'] = int(input(""))
    if(int(given_answers['Question11']) == int(hard_questions_answers["Question11"])):
        return 10
    else:
        return 0
def Question12():
    print("Enter your answer: ")
    print("66+8+4/2 = ");
    given_answers['Question12'] = int(input(""))
    if(int(given_answers['Question12']) == int(hard_questions_answers["Question12"])):
        return 10
    else:
        return 0
def Question13():
    print("Enter your answer: ")
    print("33/23/9/2*2 = ");
    given_answers['Question13'] = int(input(""))
    if(int(given_answers['Question13']) == int(hard_questions_answers["Question13"])):
        return 10
    else:
        return 0
def Question14():
    print("Enter your answer: ")
    print("66/11+2 = ");
    given_answers['Question14'] = int(input(""))
    if(int(given_answers['Question14']) == int(hard_questions_answers["Question14"])):
        return 10
    else:
        return 0
def Question15():
    print("Enter your answer: ")
    print("34*42+4+8/2 = ");
    given_answers['Question15'] = int(input(""))
    if(int(given_answers['Question15']) == int(hard_questions_answers["Question15"])):
        return 10
    else:
        return 0
def Question16():
    print("Enter your answer: ")
    print("49/2+8**2 = ");
    given_answers['Question16'] = int(input(""))
    if(int(given_answers['Question16']) == int(hard_questions_answers["Question16"])):
        return 10
    else:
        return 0
def Question17():
    print("Enter your answer: ")
    print("89/2*2/2 = ");
    given_answers['Question17'] = int(input(""))
    if(int(given_answers['Question17']) == int(hard_questions_answers["Question17"])):
        return 10
    else:
        return 0
def Question18():
    print("Enter your answer: ")
    print("66+-+66 = ");
    given_answers['Question18'] = int(input(""))
    if(int(given_answers['Question18']) == int(hard_questions_answers["Question18"])):
        return 10
    else:
        return 0
def Question19():
    print("Enter your answer: ")
    print("72+90+67/2*(36) = ");
    given_answers['Question19'] = int(input(""))
    if(int(given_answers['Question19']) == int(hard_questions_answers["Question19"])):
        return 10
    else:
        return 0
def Question20():
    print("Enter your answer: ")
    print("89**2 = ");
    given_answers['Question20'] = int(input(""))
    if(int(given_answers['Question20']) == int(hard_questions_answers["Question20"])):
        return 10
    else:
        return 0
def Question21():
    print("Enter your answer: ")
    print("33*2+7+2*(0) = ");
    given_answers['Question21'] = int(input(""))
    if(int(given_answers['Question21']) == int(hard_questions_answers["Question21"])):
        return 10
    else:
        return 0
def Question22():
    print("Enter your answer: ")
    print("32*45-54-43+65+98-43 = ");
    given_answers['Question22'] = int(input(""))
    if(int(given_answers['Question22']) == int(hard_questions_answers["Question22"])):
        return 10
    else:
        return 0
def Question23():
    print("Enter your answer: ")
    print("83*3-89*34/2/2 = ");
    given_answers['Question23'] = int(input(""))
    if(int(given_answers['Question23']) == int(hard_questions_answers["Question23"])):
        return 10
    else:
        return 0
def Question24():
    print("Enter your answer: ")
    print("21/2 = ");
    given_answers['Question24'] = int(input(""))
    if(int(given_answers['Question24']) == int(hard_questions_answers["Question24"])):
        return 10
    else:
        return 0
def Question25():
    print("Enter your answer: ")
    print("89/32*6 = ");
    given_answers['Question25'] = int(input(""))
    if(int(given_answers['Question25']) == int(hard_questions_answers["Question25"])):
        return 10
    else:
        return 0
def Question26():
    print("Enter your answer: ")
    print("96*2/7 = ");
    given_answers['Question26'] = int(input(""))
    if(int(given_answers['Question26']) == int(hard_questions_answers["Question26"])):
        return 10
    else:
        return 0
def Question27():
    print("Enter your answer: ")
    print("88+34+92/(6) = ");
    given_answers['Question27'] = int(input(""))
    if(int(given_answers['Question27']) == int(hard_questions_answers["Question27"])):
        return 10
    else:
        return 0
def Question28():
    print("Enter your answer: ")
    print("77/1*2 = ");
    given_answers['Question28'] = int(input(""))
    if(int(given_answers['Question28']) == int(hard_questions_answers["Question28"])):
        return 10
    else:
        return 0
def Question29():
    print("Enter your answer: ")
    print("99*100/32+3 = ");
    given_answers['Question29'] = int(input(""))
    if(int(given_answers['Question29']) == int(hard_questions_answers["Question29"])):
        return 10
    else:
        return 0
def Question30():
    print("Enter your answer: ")
    print("77/2+7*2 = ");
    given_answers['Question30'] = int(input(""))
    if(int(given_answers['Question30']) == int(hard_questions_answers["Question30"])):
        return 10
    else:
        return 0


def printResult():
    if(score >= 80):
        print("""
        ***************************                                                                          
        | Awesome...! You have scored {}% |                                                                                      
        ***************************""".format(score))
    else:
        print("""
        ***************************                                                                          
        | Better luck next time you have scored {}% |                                                                                      
        ***************************""".format(score))


def runHardQuiz():
    question_number=1
    global score
    print("""
    **********************************                                                                                      
    | A Simple Math Quiz - Hard Mode |                                                                                      
    **********************************""")
    for i in selected_questions:
        if (i == 'Question1'):
            print("***** Question -",question_number,"*****");question_number +=1;
            score += int(Question1());
        if (i == 'Question2'):
            print("***** Question -",question_number,"*****");question_number +=1;
            score += int(Question2());
        if (i == 'Question3'):
            print("***** Question -",question_number,"*****");question_number +=1;
            score += int(Question3());
        if (i == 'Question4'):
            print("***** Question -",question_number,"*****");question_number +=1;
            score += int(Question4());
        if (i == 'Question5'):
            print("***** Question -",question_number,"*****");question_number +=1;
            score += int(Question5());
        if (i == 'Question6'):
            print("***** Question -", question_number, "*****");question_number += 1;
            score += int(Question6());
        if (i == 'Question7'):
            print("***** Question -", question_number, "*****");question_number += 1;
            score += int(Question7());
        if (i == 'Question8'):
            print("***** Question -", question_number, "*****");question_number += 1;
            score += int(Question8());
        if (i == 'Question9'):
            print("***** Question -", question_number, "*****");question_number += 1;
            score += int(Question9());
        if (i == 'Question10'):
            print("***** Question -", question_number, "*****");question_number += 1;
            score += int(Question10());
        if (i == 'Question11'):
            print("***** Question -", question_number, "*****");question_number += 1;
            score += int(Question11());
        if (i == 'Question12'):
            print("***** Question -", question_number, "*****");question_number += 1;
            score += int(Question12());
        if (i == 'Question13'):
            print("***** Question -", question_number, "*****");question_number += 1;
            score += int(Question13());
        if (i == 'Question14'):
            print("***** Question -", question_number, "*****");question_number += 1;
            score += int(Question14());
        if (i == 'Question15'):
            print("***** Question -", question_number, "*****");question_number += 1;
            score += int(Question15());
        if (i == 'Question16'):
            print("***** Question -", question_number, "*****");question_number += 1;
            score += int(Question16());
        if (i == 'Question17'):
            print("***** Question -", question_number, "*****");question_number += 1;
            score += int(Question17());
        if (i == 'Question18'):
            print("***** Question -", question_number, "*****");question_number += 1;
            score += int(Question18());
        if (i == 'Question19'):
            print("***** Question -", question_number, "*****");question_number += 1;
            score += int(Question19());
        if (i == 'Question20'):
            print("***** Question -", question_number, "*****");question_number += 1;
            score += int(Question20());
        if (i == 'Question21'):
            print("***** Question -", question_number, "*****");question_number += 1;
            score += int(Question21());
        if (i == 'Question22'):
            print("***** Question -", question_number, "*****");question_number += 1;
            score += int(Question22());
        if (i == 'Question23'):
            print("***** Question -", question_number, "*****");question_number += 1;
            score += int(Question23());
        if (i == 'Question24'):
            score += int(Question24());
            print("***** Question -", question_number, "*****");question_number += 1;
        if (i == 'Question25'):
            print("***** Question -", question_number, "*****");question_number += 1;
            score += int(Question25());
        if (i == 'Question26'):
            print("***** Question -", question_number, "*****");question_number += 1;
            score += int(Question26());
        if (i == 'Question27'):
            print("***** Question -", question_number, "*****");question_number += 1;
            score += int(Question27());
        if (i == 'Question28'):
            print("***** Question -", question_number, "*****");question_number += 1;
            score += int(Question28());
        if (i == 'Question29'):
            print("***** Question -", question_number, "*****");question_number += 1;
            score += int(Question29());
        if (i == 'Question30'):
            print("***** Question -", question_number, "*****");question_number += 1;
            score += int(Question30());

    printResult()


















