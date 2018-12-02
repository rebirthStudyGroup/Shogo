from kivy.config import Config

Config.set('graphics', 'width', '900')
Config.set('graphics', 'height', '600')
import kivy

kivy.require('1.9.1')

from kivy.app import App
from kivy.uix.widget import Widget
from kivy.core.text import LabelBase, DEFAULT_FONT
from kivy.resources import resource_add_path
from kivy.lang import Builder
from kivy.uix.screenmanager import Screen, ScreenManager, FallOutTransition

import random

# デフォルトに使用するフォトを変更する
resource_add_path('fonts')
LabelBase.register(DEFAULT_FONT, 'mplus-2c-regular.ttf')


# ゲームで使用する数列を生成するクラス
class RandomNumber:
    __targetNum = {1: "0", 2: "0", 3: "0"}

    # コンストラクタで数列を生成。数字は全て異なる数字となるようにする
    def __init__(self):
        for num in self.__targetNum.keys():
            candidate = str(random.randint(0, 9))
            while candidate in self.__targetNum.values():
                candidate = str(random.randint(0, 9))
            self.__targetNum[num] = str(candidate)

    #  生成した数列を返却する
    def get_targetNum(self):
        return self.__targetNum


# ユーザーがインプットした数列を格納するクラス
class History:
    __history = {}
    __cursor = 1

    #  ユーザーが入力した数列を履歴に格納する
    def set_history(self, targetNum):
        tmpDict = {}
        tmpDict[1] = targetNum[1]
        tmpDict[2] = targetNum[2]
        tmpDict[3] = targetNum[3]
        self.__history[self.__cursor] = tmpDict
        self.__cursor += 1

    #  履歴を返却する
    def get_history(self, index):
        return self.__history[index]


# ユーザーが入力した数列と回答があってるかどうかをチェックするクラス
class CheckNum:

    def __init__(self):
        self.__eat = []
        self.__bite = []

    # bite と eat の数をチェックして、ターゲットにおけるeatとbiteの場所(dictのkey)をリストに格納する
    def check_num(self, numbers, targetNum):
        for number in targetNum.keys():
            if targetNum[number] == numbers[number] and number not in self.__eat:
                self.__eat.append(number)

        for biteNum in targetNum.keys():
            if biteNum not in self.__eat and numbers[biteNum] in targetNum.values():
                self.__bite.append(biteNum)

    # eatの場所リストを返却する
    def get_eat(self):
        return self.__eat

    # biteの場所リストを返却する
    def get_bite(self):
        return self.__bite


# ゴリラの画像を操作するクラス
class ManuplatingImg:
    __winner = "./image/winnerTheGollira.jpg"
    __winnerBack = "./image/winnerTheGolliraBack.jpg"
    __loser = "./image/beatenGollira.jpg"
    __loserBack = "./image/beatenGolliraBack.jpg"
    __thinking = "./image/drawGollira.jpg"

    def get_image(self, index):
        if index == 1:
            return self.__winner
        elif index == 2:
            return self.__loser
        elif index == 3:
            return self.__thinking

    def get_image_back(self, index):
        if index == 1:
            return self.__winnerBack
        elif index == 2:
            return self.__loserBack


#  メイン画像を操作するクラス
class WholeScreen(Widget):
    __numbers = {1: "0", 2: "0", 3: "0"}
    __cursor = 1  # ユーザーが入力する文字列の位置を指定
    __challengeTime = 1
    __cardBaseImg = "./image/card.jpg"
    __enemy_backgroundimage = "./image/glass.jpg"
    __history = History()  # ユーザーがCALLした数列を保持する
    __targetNum = RandomNumber().get_targetNum()  # 当てるべき数列
    __enemyImage = ManuplatingImg().get_image(3)
    __eatbite = {}

    def __init__(self, **kwargs):
        super(WholeScreen, self).__init__(**kwargs)
        self.__cursor = 1

    def initialize(self):
        self.__cursor = 1
        self.__numbers = {1: "0", 2: "0", 3: "0"}
        self.ids["numberFirst"].text = "0"
        self.ids["numberSecond"].text = "0"
        self.ids["numberThird"].text = "0"
        self.__challengeTime = 1
        self.__cardBaseImg = "./image/card.jpg"
        self.__enemy_backgroundimage = "./image/glass.jpg"
        self.__history = History()  # ユーザーがCALLした数列を保持する
        self.__targetNum = RandomNumber().get_targetNum()  # 当てるべき数列
        self.__enemyImage = ManuplatingImg().get_image(3)
        self.__eatbite = {}
        for num in range(1, 11):
            self.ids["item" + str(num)].text = "-"
            self.ids["eat" + str(num)].text = "-"
            self.ids["bite" + str(num)].text = "-"

    def get_numbers(self, index):
        return self.__numbers[index]

    def set_numbers(self, value):
        self.__numbers[self.__cursor] = value
        if self.__cursor == 1:
            self.ids["numberFirst"].text = value
        elif self.__cursor == 2:
            self.ids["numberSecond"].text = value
        elif self.__cursor == 3:
            self.ids["numberThird"].text = value

    def next(self):
        self.__cursor += 1
        if self.__cursor == 4:
            self.set_history()
            self.checkTheResult()
            for number in self.__numbers.keys():
                self.__numbers[number] = "0"
                self.__cursor = number
                self.set_numbers("0")
            self.__cursor = 1
            self.display_history()

    def delete(self):
        self.set_numbers("0")
        if self.__cursor > 1:
            self.__cursor -= 1

    def get_cardBaseImg(self):
        return self.__cardBaseImg

    def get_enemyImg(self, index):
        return ManuplatingImg().get_image(index)

    def get_enemyImgBack(self, index):
        return ManuplatingImg().get_image_back(index)

    def get_enemy_backgroundimage(self):
        return self.__enemy_backgroundimage

    def set_history(self):
        self.__history.set_history(self.__numbers)
        checknum = CheckNum()
        checknum.check_num(self.__numbers, self.__targetNum)
        self.__eatbite[self.__challengeTime] = checknum
        self.__challengeTime += 1

    def get_eat(self, index):
        if self.__eatbite.__len__() > 0:
            return str(len(self.__eatbite[index].get_eat()))
        else:
            return "0"

    def get_bite(self, index):
        if self.__eatbite.__len__() > 0:
            return str(len(self.__eatbite[index].get_bite()))
        else:
            return "0"

    def get_history(self, index):
        return self.__history.get_history(index)

    def display_history(self):
        itemString = "item" + str(self.__challengeTime - 1)
        eatString = "eat" + str(self.__challengeTime - 1)
        biteString = "bite" + str(self.__challengeTime - 1)
        itemValue = ""
        for itemkey in self.get_history(self.__challengeTime - 1).keys():
            itemValue = itemValue + self.get_history(self.__challengeTime - 1)[itemkey]
            if itemkey != 3:
                itemValue = itemValue + "-"
        self.ids[itemString].text = itemValue
        self.ids[eatString].text = self.get_eat(self.__challengeTime - 1)
        self.ids[biteString].text = self.get_bite(self.__challengeTime - 1)

    def get_result(self, result_text):
        sm.get_screen("result").children[0].set_result(result_text)
        sm.current = "result"

    def checkTheResult(self):
        if self.get_eat(self.__challengeTime - 1) == '3':
            self.get_result('win')
        elif self.__challengeTime == 11:
            self.get_result('lose')

    def get_resultFl(self):
        return self.__winOrLoseFl


class Result(Widget):
    __resultText = ""
    __resultIndex = 1
    __victoryImg = "./image/logo_win.jpg"
    __loseImg = "./image/logo_lose.jpg"

    def get_enemyImg(self):
        return ManuplatingImg().get_image(self.__resultIndex)

    def get_enemyImgBack(self):
        return ManuplatingImg().get_image_back(self.__resultIndex)

    def get_resultImage(self):
        return self.__resultText

    def set_result(self, resultfl):
        if resultfl == 'win':
            self.__resultText = self.__victoryImg
            self.__resultIndex = 2
            self.do_after_method()
        elif resultfl == 'lose':
            self.__resultText = self.__loseImg
            self.__resultIndex = 1
            self.do_after_method()

    def do_after_method(self):
        self.ids["result_image"].source = self.get_enemyImg()
        self.ids["result_image"].canvas.before.children[1].source = self.get_enemyImgBack()
        self.ids["result"].source = self.get_resultImage()

    def reset(self):
        sm.current = "main"


class main_screen(Screen):

    def __init__(self, **kwargs):
        super(main_screen, self).__init__(**kwargs)
        self.whole_screen = WholeScreen()
        self.add_widget(self.whole_screen)

    def on_enter(self, *args):
        self.children[0].initialize()


class result_screen(Screen):

    def __init__(self, **kwargs):
        super(result_screen, self).__init__(**kwargs)
        self.result_screen = Result()
        self.add_widget(self.result_screen)

    def on_enter(self, *args):
        pass


Builder.load_file("numeron.kv")
sm = ScreenManager(transition=FallOutTransition())
sm.add_widget(main_screen(name="main"))
sm.add_widget(result_screen(name="result"))


class NumeronApp(App):
    def __init_(self, **kwargs):
        super(NumeronApp, self).__init__(**kwargs)
        self.title = 'numeron'

    def build(self):
        return sm


if __name__ == "__main__":
    NumeronApp().run()
