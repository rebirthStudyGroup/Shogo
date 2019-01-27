# -*- coding: utf-8 -*-

from kivy.app import App
from kivy.uix.boxlayout import BoxLayout
from kivy.properties import ObjectProperty, ListProperty, StringProperty
from kivy.core.text import LabelBase, DEFAULT_FONT
from kivy.resources import resource_add_path
from kivy.uix.screenmanager import Screen
from kivy.uix.screenmanager import ScreenManager
from kivy.clock import Clock
from kivy.uix.listview import ListItemButton
from kivy.uix.textinput import TextInput
from kivy.base import EventLoop

from datetime import date, datetime


from timemanagement.const.ScreenInfoConst import ScreenConst
from timemanagement.dataAccess.DataAccess import CardAccessLogicImpl, TitleAccessLogicImpl, HistoryAccessLogicImpl
from timemanagement.dto.CardDTO import CardDto
from timemanagement.dto.TitleDTO import TitleDto
from timemanagement.dto.HistoryDTO import HistoryDto

import logging

resource_add_path('./fonts')
LabelBase.register(DEFAULT_FONT, 'mplus-2c-regular.ttf')

stream_handler = logging.StreamHandler()
stream_handler.setLevel(logging.INFO)
stream_handler.setFormatter(logging.Formatter(
    '%(pathname)s:%(lineno)s %(message)s'
))
logging.basicConfig(
    level=logging.INFO,
    handlers=[stream_handler]
)


SEPARATOR = "#"


class SampleDto:

    def __init__(self):
        self.text = ''

    def set_test_s(self, text):
        self.text = text

    def get_test_s(self):
        return self.text

class MyScreenManager(ScreenManager):
    """アクションバーを制御するクラス。

    以下の機能を実装。
     * 画面を切り替える(kvファイルのどのクラスからも呼べる)
     * 画面間共有データの操作
    """
    def __init__(self, **kwargs):
        super(MyScreenManager, self).__init__(**kwargs)
        self.__s = SampleDto()
        self.__sample_list = []
        self.__card_list = []

    def get_card_list(self) -> list:
        """マスタ画面にあるカードリストを取得する"""
        return self.__card_list

    def set_card_list(self, card_list: list):
        """マスタ画面にあるカードリストをsmクラスのインスタンスに設定する"""
        self.__card_list = card_list

    def update_target_card(self, text: str):
        """スクリーンマネージャからストップウォッチの画面上に処理対象となるカードを表示する"""
        self.get_screen("stop_watch").update_target_card(text)

    def set_delete_card(self, text):
        """マスタ画面上に削除対象のカードを表示する"""
        self.get_screen("master").set_delete_card(text)

    """画面の切替"""

    def load_slide_for_make_new(self):
        """新規作成画面への切替"""
        self.current = ScreenConst.make_new()

    def load_slide_for_stop_watch(self):
        """計測画面への切替"""
        self.current = ScreenConst.stop_watch()

    def load_slide_for_master(self):
        """マスタ管理画面への切替"""
        self.current = ScreenConst.master()

sm = MyScreenManager()

class ScreenBase(Screen):
    """全画面共通のメソッドを実装する。

    現時点では未実装。
    """
    pass

"""各画面毎のクラス
"""

class MakeNew(BoxLayout, ScreenBase):
    """タイトル、カードを新規作成する画面"""
    title_input = ObjectProperty()
    purpose_input = ObjectProperty()
    overview_input = ObjectProperty()
    card_name_input = ObjectProperty()
    category1_input = ObjectProperty()
    category2_input = ObjectProperty()
    __card_logic = CardAccessLogicImpl()
    __title_logic = TitleAccessLogicImpl()

    def __init__(self, **kwargs):
        super(MakeNew, self).__init__(**kwargs)

    def register_new_title(self):
        """新規タイトルを登録する"""
        title = self.ids.title_input.text.strip()
        dto = TitleDto(title, self.ids.overview_input.text.strip(), self.ids.purpose_input.text.strip())
        # 正常に登録された場合、インプット内容をカードリストに追加する
        if self.__title_logic.set_data(dto):
            self.ids.card_name_input.values.extend(['{0:<20}'.format(title)])
            self.ids.title_input.text = ""
        # 既に登録されていた場合は、登録できなかった旨を表示する
        else:
            self.ids.title_input.text = "既に登録されてます"
        self.ids.overview_input.text = ""
        self.ids.purpose_input.text = ""

    def register_new_card(self):
        """新規カードを登録する"""
        dto = CardDto(1, self.ids.category1_input.text.strip(), self.ids.category2_input.text.strip(), self.ids.card_name_input.text.strip(), 1)
        # 正常に登録された場合、インプット内容をカードリストに追加する
        # 現時点ではカードの登録にcheck機能を持たせていない
        if self.__card_logic.set_data(dto):
            # self.ids.card_name_input.values.extend(['{0:<20}'.format(title)])
            self.ids.title_input.text = ""
        # 既に登録されていた場合は、登録できなかった旨を表示する
        else:
            self.ids.title_input.text = "既に登録されてます"
        self.ids.category1_input.text = ""
        self.ids.category2_input.text = ""


    def test(self):
        sm.set_test_s(self.title_input.text, list())

    def title_list(self):
        """カードのタイトルに使用するリストを表示する"""
        title_list = TitleAccessLogicImpl.get_data_list(self.__title_logic).values()
        if len(title_list) == 0:
            return ["-"]
        return [name.get_name() for name in title_list]


class StopWatch(BoxLayout, ScreenBase):
    """ストップウォッチで時間を計測する画面"""

    stop_watch_start = False   # ストップウォッチが作動しているかどうか
    stop_watch_second = 0       # ストップウォッチの秒
    first_start_button_fl = False  # ストップウォッチの初回押下を判断


    """time_unit_start and end
    0-3  : 1, 3-6  : 2, 6-9  : 3, 9-12 : 4
    12-15: 5, 15-18: 6, 18-21: 7, 21-24: 8
    """
    time_unit_start = 1
    time_unit_end = 1

    __history_logic = HistoryAccessLogicImpl()  #  履歴データのデータアクセスロジック

    __T_FORM = '{0:02d}:{1:02d}:{2:02d}'

    def __init__(self, **kwargs):
        super(StopWatch, self).__init__(**kwargs)
        self.title = 'Stop Watch'
        self.update_event = Clock.schedule_interval(self.update_time, 0.1)

    def start_stop(self):
        """ストップウォッチのスタートボタンの操作"""
        if self.stop_watch_start:
            self.stop_watch_start = False
            self.ids.start_stop.text = 'Start'

            # 初回スタート時点にtime_unitを設定し、フラグをオンにする
            if not self.first_start_button_fl:
                self.time_unit_end = 1  # time_unit の値を初期化
                self.time_unit_start = StopWatch.make_time_unit()
                self.first_start_button_fl = True
        else:
            self.stop_watch_start = True
            self.ids.start_stop.text = 'Stop'

    def reset(self):
        """ストップウォッチのリセットボタンの操作"""

        if self.stop_watch_start:
            self.ids.start_stop.text = 'Start'
            self.stop_watch_start = False

        # 結果をDBに登録する
        self.time_unit_end = StopWatch.make_time_unit()
        self.record_time_data()

        self.stop_watch_second = 0
        self.first_start_button_fl = False
        # self.ids.lap.text = self.__T_FORM.format(0, 0, 0)

    # def record_lap(self):
    #     """ストップウォッチのリセットボタンの操作"""
    #
    #     if self.ids.stop_watch_repr.text == '00:00:00':
    #         self.ids.lap.text = self.__T_FORM.format(0, 0, 0)
    #     else:
    #         self.ids.lap.text = self.ids.stop_watch_repr.text

    def update_time(self, dt):
        """ストップウォッチの秒数を更新"""
        if self.stop_watch_start:
            self.stop_watch_second += dt

        m, s = divmod(self.stop_watch_second, 60) # 分、秒を取得
        ms = s * 100 % 100  # ミリ秒を取得

        self.ids.stop_watch_repr.text = self.__T_FORM.format(int(m), int(s), int(ms))

    def update_card_list(self):
        """マスタ画面のカード一覧を計測画面で表示
        on_enter により発火
        """
        self.ids.card_list_view.adapter.data = sm.get_card_list()

    def update_target_card(self, this_card: str):
        """計測対象のカードを表示する"""
        self.ids.this_card.text = this_card

    def record_time_data(self):
        """計測結果を実際のデータに反映させる"""
        this_card = self.ids.this_card.text.split("：")
        if this_card[0] != "-":
            none, id, title, result, category1, category2 = self.ids.this_card.text.split("：")
            input_data = round(self.stop_watch_second/1, 0) # 計測時間を分数換算にする
            dto = HistoryDto(int(id), int(input_data), self.time_unit_start, self.time_unit_end, date.today())
            self.__history_logic.set_data(dto)

            # ここから作成する必要あり
            # self.__history_logic.

    @staticmethod
    def make_time_unit() -> int:
        """タスクの開始時間と終了時間を作成する(現時点で未使用)"""
        dth = datetime.today().hour
        time_unit_dict = {21: 8, 18: 7, 15: 6, 12: 5, 9: 4, 6: 3, 3: 2}
        for time_unit, result in time_unit_dict.items():
            if dth > time_unit:
                return result


class StopWatchCardButton(ListItemButton):
    """計測画面でのカードリストをボタンにする"""
    card = ListProperty()

    def get_card(self):
        return self.card[0]

class MasterButton(ListItemButton):
    """カード管理画面の一覧をボタンにする"""
    card = ListProperty()

    def get_card(self):
        return self.card[0]


__all__ = ('TextInputIME', )


class TextInputIME(TextInput):
    """文字入寮中の日本語表示を行うためのパーツ"""
    text_repr = StringProperty()

    def __init__(self, **kwargs):
        super(TextInputIME, self).__init__(**kwargs)
        EventLoop.window.bind(on_textedit = self._on_textedit)

    def _on_textedit(self, window, text):
        self.text_repr = text

class Master(BoxLayout, ScreenBase):
    """マスタ画面を表示するクラス"""

    card_list_select = ObjectProperty()
    __card_logic = CardAccessLogicImpl()
    __history_logic = HistoryAccessLogicImpl()

    __card_list_repr = '【title】：{id:>3}：{name}    【time(m)】：{time:5}\n【cate1】：{category1}【cate2】：{category2}'

    def __init__(self, **kwargs):
        super(Master, self).__init__(**kwargs)
        # extend で挿入するリストはstr型にしないとエラーになる

    def set_title_output(self):
        """カードの一覧を画面下部に表示するための部品"""
        card_list = list()
        card_dict = self.__card_logic.get_data_list()  # cardDBに登録されている{id: dto}の辞書
        abs_list = [",".join(map(str, card_dict.keys()))]  # history 検索キーのcard_id
        abs_list.append(str(date(2019, 1, 1)))  # history 検索キーの日付

        # 履歴データをDBから取得
        history_list = self.__history_logic.get_data_list_with_list(abs_list)

        hist_dict = {}
        for tmp_id, history_data in history_list.items():
            hist_dict[tmp_id] = sum([data.get_result() for data in history_data.values()])

        # DBからカードのリストを取得して、一覧用の文字列を作成する
        for card in card_dict.values():
            card_id = str(card.get_id())
            time = str(hist_dict.get(card.get_id()/60, 0))
            card_str = self.__card_list_repr.format(
                    id=card_id, name=card.get_title(), category1=card.get_category1(), category2=card.get_category2(), time=time
            )
            card_list.append([card_str])

        sm.set_card_list(card_list)
        self.card_list_select.adapter.data = card_list

    def args_converter(index, card_list: list):
        return {'card': card_list}

    def set_delete_card(self, text):
        """マスタ画面の上部に削除対象のカードを表示する"""
        self.ids.delete_target_card.text = text

    def delete_card(self):
        """マスタ画面上で削除対象となっているカードを削除する"""
        delete_card = self.ids.delete_target_card.text
        if delete_card != "":
            none, id, title, result, category1, category2 = delete_card.split("：")
            self.__history_logic.clear_data([id])
            self.__card_logic.clear_data([id])

"""実行クラス
"""

class TimeManagerApp(App):
    """ルートのクラス"""

    def __init__(self, **kwargs):
        super(TimeManagerApp, self).__init__(**kwargs)
        self.title = ScreenConst.screen_title()
    pass

    def build(self):
        sm.add_widget(Master(name=ScreenConst.master()))
        sm.add_widget(StopWatch(name=ScreenConst.stop_watch()))
        sm.add_widget(MakeNew(name=ScreenConst.make_new()))
        return sm


if __name__ == '__main__':
    TimeManagerApp().run()


