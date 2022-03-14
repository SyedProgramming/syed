import random as rd
from variables import *



score = 0
selected_questions = rd.choices(questions, k=10)

def Question1():
    print("Enter your answer: ")
    print("16 + 28 = ");
    given_answers['Question1'] = int(input(""))
    if(int(given_answers['Question1']) == int(easy_questions_answers["Question1"])):
        return 10
    else: return 0
def Question2():
    print("Enter your answer: ")
    print("32 - 19 = ");
    given_answers['Question2'] = int(input(""))
    if(int(given_answers['Question2']) == int(easy_questions_answers["Question2"])):
        return 10
    else:
        return 0
def Question3():
    print("Enter your answer: ")
    print("20 / 5 = ");
    given_answers['Question3'] = int(input(""))
    if(int(given_answers['Question3']) == int(easy_questions_answers["Question3"])):
        return 10
    else:
        return 0
def Question4():
    print("Enter your answer: ")
    print("36 * 2 = ");
    given_answers['Question4'] = int(input(""))
    if(int(given_answers['Question4']) == int(easy_questions_answers["Question4"])):
        return 10
    else:
        return 0
def Question5():
    print("Enter your answer: ")
    print("98 + 160 = ");
    given_answers['Question5'] = int(input(""))
    if(int(given_answers['Question5']) == int(easy_questions_answers["Question5"])):
        return 10
    else:
        return 0
def Question6():
    print("Enter your answer: ")
    print("125 / 5 = ");
    given_answers['Question6'] = int(input(""))
    if(int(given_answers['Question6']) == int(easy_questions_answers["Question6"])):
        return 10
    else:
        return 0
def Question7():
    print("Enter your answer: ")
    print("5 / 5 = ");
    given_answers['Question7'] = int(input(""))
    if(int(given_answers['Question7']) == int(easy_questions_answers["Question7"])):
        return 10
    else:
        return 0
def Question8():
    print("Enter your answer: ")
    print("39 - 12 = ");
    given_answers['Question8'] = int(input(""))
    if(int(given_answers['Question8']) == int(easy_questions_answers["Question8"])):
        return 10
    else:
        return 0
def Question9():
    print("Enter your answer: ")
    print("40 + 2 = ");
    given_answers['Question9'] = int(input(""))
    if(int(given_answers['Question9']) == int(easy_questions_answers["Question9"])):
        return 10
    else:
        return 0
def Question10():
    print("Enter your answer: ")
    print("38 - 26 = ");
    given_answers['Question10'] = int(input(""))
    if(int(given_answers['Question10']) == int(easy_questions_answers["Question10"])):
        return 10
    else:
        return 0
def Question11():
    print("Enter your answer: ")
    print("17 + 16 = ");
    given_answers['Question11'] = int(input(""))
    if(int(given_answers['Question11']) == int(easy_questions_answers["Question11"])):
        return 10
    else:
        return 0
def Question12():
    print("Enter your answer: ")
    print("20 * 10 = ");
    given_answers['Question12'] = int(input(""))
    if(int(given_answers['Question12']) == int(easy_questions_answers["Question12"])):
        return 10
    else:
        return 0
def Question13():
    print("Enter your answer: ")
    print("46 - 22 = ");
    given_answers['Question13'] = int(input(""))
    if(int(given_answers['Question13']) == int(easy_questions_answers["Question13"])):
        return 10
    else:
        return 0
def Question14():
    print("Enter your answer: ")
    print("9 + 7 = ");
    given_answers['Question14'] = int(input(""))
    if(int(given_answers['Question14']) == int(easy_questions_answers["Question14"])):
        return 10
    else:
        return 0
def Question15():
    print("Enter your answer: ")
    print("88 + 96 = ");
    given_answers['Question15'] = int(input(""))
    if(int(given_answers['Question15']) == int(easy_questions_answers["Question15"])):
        return 10
    else:
        return 0
def Question16():
    print("Enter your answer: ")
    print("93 * 3 = ");
    given_answers['Question16'] = int(input(""))
    if(int(given_answers['Question16']) == int(easy_questions_answers["Question16"])):
        return 10
    else:
        return 0
def Question17():
    print("Enter your answer: ")
    print("32 + 16 = ");
    given_answers['Question17'] = int(input(""))
    if(int(given_answers['Question17']) == int(easy_questions_answers["Question17"])):
        return 10
    else:
        return 0
def Question18():
    print("Enter your answer: ")
    print("100 * 3 = ");
    given_answers['Question18'] = int(input(""))
    if(int(given_answers['Question18']) == int(easy_questions_answers["Question18"])):
        return 10
    else:
        return 0
def Question19():
    print("Enter your answer: ")
    print("50 / 25 = ");
    given_answers['Question19'] = int(input(""))
    if(int(given_answers['Question19']) == int(easy_questions_answers["Question19"])):
        return 10
    else:
        return 0
def Question20():
    print("Enter your answer: ")
    print("37 + 98 = ");
    given_answers['Question20'] = int(input(""))
    if(int(given_answers['Question20']) == int(easy_questions_answers["Question20"])):
        return 10
    else:
        return 0
def Question21():
    print("Enter your answer: ")
    print("55 / 11 = ");
    given_answers['Question21'] = int(input(""))
    if(int(given_answers['Question21']) == int(easy_questions_answers["Question21"])):
        return 10
    else:
        return 0
def Question22():
    print("Enter your answer: ")
    print("30 - 30 = ");
    given_answers['Question22'] = int(input(""))
    if(int(given_answers['Question22']) == int(easy_questions_answers["Question22"])):
        return 10
    else:
        return 0
def Question23():
    print("Enter your answer: ")
    print("92 + 19 = ");
    given_answers['Question23'] = int(input(""))
    if(int(given_answers['Question23']) == int(easy_questions_answers["Question23"])):
        return 10
    else:
        return 0
def Question24():
    print("Enter your answer: ")
    print("11 + 21 = ");
    given_answers['Question24'] = int(input(""))
    if(int(given_answers['Question24']) == int(easy_questions_answers["Question24"])):
        return 10
    else:
        return 0
def Question25():
    print("Enter your answer: ")
    print("42 - 39 = ");
    given_answers['Question25'] = int(input(""))
    if(int(given_answers['Question25']) == int(easy_questions_answers["Question25"])):
        return 10
    else:
        return 0
def Question26():
    print("Enter your answer: ")
    print("23 + 48 = ");
    given_answers['Question26'] = int(input(""))
    if(int(given_answers['Question26']) == int(easy_questions_answers["Question26"])):
        return 10
    else:
        return 0
def Question27():
    print("Enter your answer: ")
    print("10 * 100 = ");
    given_answers['Question27'] = int(input(""))
    if(int(given_answers['Question27']) == int(easy_questions_answers["Question27"])):
        return 10
    else:
        return 0
def Question28():
    print("Enter your answer: ")
    print("23 * 18 = ");
    given_answers['Question28'] = int(input(""))
    if(int(given_answers['Question28']) == int(easy_questions_answers["Question28"])):
        return 10
    else:
        return 0
def Question29():
    print("Enter your answer: ")
    print("53 + 34 = ");
    given_answers['Question29'] = int(input(""))
    if(int(given_answers['Question29']) == int(easy_questions_answers["Question29"])):
        return 10
    else:
        return 0
def Question30():
    print("Enter your answer: ")
    print("98 + 10 = ");
    given_answers['Question30'] = int(input(""))
    if(int(given_answers['Question30']) == int(easy_questions_answers["Question30"])):
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



def runEasyQuiz():
    global score
    print("""
    **********************************                                                                                      
    | A Simple Math Quiz - Easy Mode |                                                                                      
    **********************************""")
    for i in selected_questions:
        if (i == 'Question1'):
            score += int(Question1());
        if (i == 'Question2'):
            score += int(Question2());
        if (i == 'Question3'):
            score += int(Question3());
        if (i == 'Question4'):
            score += int(Question4());
        if (i == 'Question5'):
            score += int(Question5());
        if (i == 'Question6'):
            score += int(Question6());
        if (i == 'Question7'):
            score += int(Question7());
        if (i == 'Question8'):
            score += int(Question8());
        if (i == 'Question9'):
            score += int(Question9());
        if (i == 'Question10'):
            score += int(Question10());
        if (i == 'Question11'):
            score += int(Question11());
        if (i == 'Question12'):
            score += int(Question12());
        if (i == 'Question13'):
            score += int(Question13());
        if (i == 'Question14'):
            score += int(Question14());
        if (i == 'Question15'):
            score += int(Question15());
        if (i == 'Question16'):
            score += int(Question16());
        if (i == 'Question17'):
            score += int(Question17());
        if (i == 'Question18'):
            score += int(Question18());
        if (i == 'Question19'):
            score += int(Question19());
        if (i == 'Question20'):
            score += int(Question20());
        if (i == 'Question21'):
            score += int(Question21());
        if (i == 'Question22'):
            score += int(Question22());
        if (i == 'Question23'):
            score += int(Question23());
        if (i == 'Question24'):
            Question24();
        if (i == 'Question25'):
            score += int(Question25());
        if (i == 'Question26'):
            score += int(Question26());
        if (i == 'Question27'):
            score += int(Question27());
        if (i == 'Question28'):
            score += int(Question28());
        if (i == 'Question29'):
            score += int(Question29());
        if (i == 'Question30'):
            score += int(Question30());

    printResult()



















