from timemanagement.dto import CardDTO
from datetime import date

class HistoryDto:
    """
    履歴DTO
    """

    def __init__(self, card_id: int, result: int, time_unit_start: int, time_unit_end: int, date: date):
        self.__card_id = card_id
        self.__result = result
        self.__time_unit_start = time_unit_start
        self.__time_unit_end = time_unit_end
        self.__date = date

    def get_card_id(self):
        return self.__card_id

    def set_card_id(self, card_id):
        self.__card_id = card_id

    def get_result(self):
        return self.__result

    def set_result(self, result):
        self.__result = result

    def get_time_unit_start(self):
        return self.__time_unit_start

    def set_time_unit_start(self, time_unit_start):
        self.__time_unit_start = time_unit_start

    def get_time_unit_end(self):
        return self.__time_unit_end

    def set_time_unit_end(self, time_unit_end):
        self.__time_unit_end = time_unit_end

    def get_date(self):
        return self.__date

    def set_date(self, date):
        self.__date = date


