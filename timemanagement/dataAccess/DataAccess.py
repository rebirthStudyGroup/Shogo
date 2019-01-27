import mysql.connector
from timemanagement.const.DBAConst import DBAConst
from timemanagement.const.CommonConst import CommonConst
from timemanagement.dto.CardDTO import CardDto
from timemanagement.dto.HistoryDTO import HistoryDto
from timemanagement.dto.TitleDTO import TitleDto

from abc import ABCMeta, abstractmethod

TRUE = 'true'
FALSE = 'false'

class DriverManager(object):
    """mysql-connector-pythonを使用してデータベースへの接続を行う。
    """

    def __init__(self):
        pass

    def __enter__(self):
        """mysqlにアクセスする。使用後は速やかにconnectをcloseすること。
        :return: MySQL connectionオブジェクト
        """
        self.connect = mysql.connector.connect(user=DBAConst.user(),
                                               password=DBAConst.password(),
                                               host=DBAConst.host(),
                                               database=DBAConst.database(),
                                               charset=DBAConst.charset()
                                               )
        return self.connect

    def __exit__(self, exc_type, exc_val, exc_tb):
        self.connect.close()


class DataAccessLogic(metaclass=ABCMeta):
    """データベースにアクセスする機能を提供する抽象クラス。
    """

    def __init__(self):
        pass

    def get_data_list(self) -> dict:
        """データベースから対象クラスの辞書型にして返却する

        辞書型 -> {先頭の列: DTOオブジェクト}

        :return: 対象クラスのリスト
        """
        try:
            cursor = object()
            with DriverManager() as connect:
                cursor = connect.cursor()
                # SQL文を実行する
                cursor.execute(self.get_select_sentence())
                result = cursor.fetchall()
                result_dict = {}
                for entry in result:
                    self.set_dto(entry, result_dict)
            return result_dict
        except Exception as e:
            raise e
        finally:
            if type(cursor) is not object:
                cursor.close()
            else:
                print("before cursor is instantiated, error has been occurred")

    def get_data_list_with_list(self, abst_list: list) -> dict:
        """(オーバーロード↑)データベースから対象クラスの辞書型にして返却する

        辞書型 -> {先頭の列: DTOオブジェクト}

        :param 条件分
        :return: 対象クラスのリスト
        """
        try:
            cursor = object()
            with DriverManager() as connect:
                cursor = connect.cursor()
                # SQL文を実行する
                cursor.execute(self.get_select_sentence_with_list(abst_list))
                result = cursor.fetchall()
                result_dict = {}
                for entry in result:
                    self.set_dto(entry, result_dict)
            return result_dict
        except Exception as e:
            raise e
        finally:
            if type(cursor) is not object:
                cursor.close()
            else:
                print("before cursor is instantiated, error has been occurred")

    @abstractmethod
    def get_select_sentence(self) -> str:
        """データベースからデータを取得する際に使用するselect文
        :return: select文
        """
        pass

    def get_select_sentence_with_list(self, abst_list: list) -> str:
        """(オーバーロード↑)データベースからデータを取得する際に使用するselect文
        :param 条件文
        :return: select文
        """
        pass

    @abstractmethod
    def set_dto(self, value_list: list, result_dict: dict):
        """データベースから受け取った値をDTOに格納して、dictに設定する
        :param value_list: データベースから受け取った値のリスト
        :param result_dict: 格納するDict
        """
        pass

    def set_data(self, dto) -> bool:
        """引数に指定されている対象クラスの値をデータベースに設定する
        :param dto: 対象クラス
        @:return 正常に登録出来たら True、それ以外の場合は False
        """
        if self._check_data(dto):
            self.__do_update(self.get_insert_sentence(dto))
            return True
        return False

    def _check_data(self, dto) -> bool:
        """設定対象のDTOのデータの中身をチェックする
        @:param: dto 対象クラス
        @:return 正常に登録できるのであれば True、それ以外の場合は False
        """
        return True

    @abstractmethod
    def get_insert_sentence(object) -> str:
        """データベースへデータを挿入する際に使用するinsert文
        :return: insert文
        """
        pass

    def clear_data(self, ids: list):
        """引数のIDに紐づくカラムを削除する
        :param ids: 削除対象のID
        """
        self.__do_update(self.get_delete_sentence(ids))

    @abstractmethod
    def get_delete_sentence(self, ids: list) -> str:
        """データベースからデータを削除する際に使用するdelete文
        :param ids: 削除対象のid
        :return: delete文
        """
        pass

    def __do_update(self, sentence: str):
        """update文を実行する
        :param sentence: update用のSQL文
        """
        try:
            cursor = object()
            with DriverManager() as connect:
                cursor = connect.cursor()
                cursor.execute(sentence)
                connect.commit()
            pass
        except Exception as e:
            raise e
        finally:
            if type(cursor) != object:
                cursor.close()
            else:
                print("before cursor is instantiated, error has been occurred")



class CardAccessLogicImpl(DataAccessLogic):
    """カードデータベースを操作するクラス

    desc card:
    Field -> id,  category1, category2, title,   validation
    Type  -> int, varchar,   varchar,   varchar, tinyint

    """
    __SELECT_ALL_FROM = DBAConst.select_from_all() % DBAConst.card_data_base_name()
    __TABLE_DATA = '{table}({category1}, {category2}, {title}, {validation})'.format(table=DBAConst.card_data_base_name(), category1=DBAConst.category1(), category2=DBAConst.category2(), title=DBAConst.title(), validation=DBAConst.validate())  # DBAConstクラスに本番用の定数を作成する
    __INSERT_DATA = '"{category1}", "{category2}", "{title}", {validation}'  # DBAConstクラスに本番用の定数を作成する
    __INSERT_INTO_TABLE_VALUE = DBAConst.insert_into() # insert into %s values(%s)
    __DELETE_FROM_TABLE = 'delete from %s where id in ({delete_card})' % DBAConst.card_data_base_name() # DBAConstクラスに本番用の定数を作成する

    def __init__(self):
        pass

    def set_dto(self, entry: list, card_dict: dict) -> str:
        """引数1で受け取ったDTOの値リストをDTOに格納して、dictクラスに設定する。
        :param entry:  DTOの値を持ったリスト
        :param card_dict: 返却するDTO dict
        :return: DTOを格納したdict
        """
        boolean = CommonConst.true() if entry[4] != 0 else CommonConst.false()
        dto = CardDto(entry[0], entry[1], entry[2], entry[3], boolean)
        card_dict[entry[0]] = dto

    def get_select_sentence(self) -> str:
        """select文を返却する
        :return: select文
        """
        return self.__SELECT_ALL_FROM

    def get_insert_sentence(self, card_dto: CardDto) -> str:
        """insert文を返却する
        :param card_dto: カードDTO
        :return: insert文
        """
        value_data = self.__INSERT_DATA.format(category1=card_dto.get_category1(),
                                          category2=card_dto.get_category2(), title=card_dto.get_title(), validation=card_dto.get_validate())
        return self.__INSERT_INTO_TABLE_VALUE % (self.__TABLE_DATA, value_data)

    def get_delete_sentence(self, card_ids: list) -> str:
        """delete文を返却する(idで対象を指定して削除する)
        :param card_ids: 削除対象のid
        :return: delete文
        """
        return self.__DELETE_FROM_TABLE.format(delete_card=', '.join([str(card_id) for card_id in card_ids]))


class HistoryAccessLogicImpl(DataAccessLogic):
    """
    履歴データベースを操作するクラス

    desc history:
    Field -> card_id,  result,   time_unit_start, time_unit_end, date
    Type  -> int,      smallint, tinyint,         tinyint,       date

    """
    __SELECT_ALL_FROM = DBAConst.select_from_all() % DBAConst.history_data_base_name()
    __SELECT_ALL_FROM_WITH = DBAConst.select_from_part() % DBAConst.history_data_base_name()
    __TABLE_DATA = '{table}({col1}, {col2}, {col3}, {col4}, {col5})'.format(table=DBAConst.history_data_base_name(), col1=DBAConst.card_id(), col2=DBAConst.result(), col3=DBAConst.time_unit_start(), col4=DBAConst.time_unit_end(), col5=DBAConst.date())  # DBAConstクラスに本番用の定数を作成する
    __INSERT_DATA = '{card_id}, {result}, {time_unit_start}, {time_unit_end}, \'{date}\''  # DBAConstクラスに本番用の定数を作成する
    __INSERT_INTO_TABLE_VALUE = DBAConst.insert_into()  # insert into %s values(%s)
    __DELETE_FROM_TABLE = 'delete from %s where card_id in ({delete_card})' % DBAConst.history_data_base_name() # DBAConstクラスに本番用の定数を作成する

    def __init__(self):
        pass

    def set_dto(self, entry: list, history_dict: dict) -> str:
        """引数1で受け取ったDTOの値リストをDTOに格納して、dictクラスに設定する。

        例){1: {
                2019-1-1: dto1,
                2019-1-2: dto2
                },
            2: {
                2019-1-1: dto1,
                2019-1-2: dto2
                }
            }

        :param entry:  DTOの値を持ったリスト
        :param history_dict: 返却するDTO dict
        :return: DTOを格納したdict
        """
        dto = HistoryDto(entry[0], entry[1], entry[2], entry[3], entry[4])
        if history_dict.get(entry[0]) is None:
            history_dict[entry[0]] = {entry[4]: dto}
        else:
            #  card_id と date が同じ値が存在する際に、resultを合算したdtoを新規に作成して辞書に登録する
            target_dto = history_dict.get(dto.get_card_id(), {}).get(dto.get_date())
            if target_dto is not None:
                target_dto.set_result(target_dto.get_result() + dto.get_result())

            #  card_id は存在するが date が異なる(別日に実施した)場合、card_id に紐づく新しい辞書を追加する
            else:
                history_dict[entry[0]].update({entry[4]: dto})

    def get_select_sentence(self) -> str:
        """select文を返却する
        :return: select文
        """
        return self.__SELECT_ALL_FROM

    def get_select_sentence_with_list(self, abs_list: list) -> str:
        """select文を返却する
        @:param 条件分(id と 日付)
        :return: select文
        """
        # 挿入文
        insert_str = "card_id in ({id}) and date > '{date}'".format(id=abs_list[0], date=abs_list[1])
        return self.__SELECT_ALL_FROM_WITH.format(terms=insert_str)

    def get_insert_sentence(self, history_dto: HistoryDto) -> str:
        """insert文を返却する
        :param history_dto: タイトルDTO
        :return: insert文
        """
        value_data = self.__INSERT_DATA.format(card_id=history_dto.get_card_id(), result=history_dto.get_result(), time_unit_start=history_dto.get_time_unit_start(), time_unit_end=history_dto.get_time_unit_end(), date=history_dto.get_date())
        table_data = DBAConst.history_data_base_name()
        return self.__INSERT_INTO_TABLE_VALUE % (table_data, value_data)

    def get_delete_sentence(self, title_ids: list) -> str:
        """delete文を返却する(idで対象を指定して削除する)
        :param title_ids: 削除対象のid
        :return: delete文
        """
        return self.__DELETE_FROM_TABLE.format(delete_card=', '.join([str(title_id) for title_id in title_ids]))

    def _check_data(self, dto: TitleDto) -> bool:
        """設定対象のDTOのデータの中身をチェックする
        @:param: dto 対象クラス
        @:return 正常に登録できるのであれば True、それ以外の場合は False
        """
        return True

class TitleAccessLogicImpl(DataAccessLogic):
    """
    タイトルデータベースを操作するクラス

    desc title:
    Field -> id,  name,    overview, purpose
    Type  -> int, varchar, varchar,  varchar

    """
    __SELECT_ALL_FROM = DBAConst.select_from_all() % DBAConst.title_data_base_name()
    __TABLE_DATA = '{table}({col1}, {col2}, {col3})'.format(table=DBAConst.title_data_base_name(), col1=DBAConst.name(), col2=DBAConst.overview(), col3=DBAConst.purpose())  # DBAConstクラスに本番用の定数を作成する
    __INSERT_DATA = '"{name}", "{category1}", "{category2}"'  # DBAConstクラスに本番用の定数を作成する
    __INSERT_INTO_TABLE_VALUE = DBAConst.insert_into()  # insert into %s values(%s)
    __DELETE_FROM_TABLE = 'delete from {table} where ID in ({ids})'  # DBAConstクラスに本番用の定数を作成する

    def __init__(self):
        pass

    def set_dto(self, entry: list, title_dict: dict) -> str:
        """引数1で受け取ったDTOの値リストをDTOに格納して、dictクラスに設定する。
        :param entry:  DTOの値を持ったリスト
        :param title_dict: 返却するDTO dict
        :return: DTOを格納したdict
        """
        dto = TitleDto(entry[1], entry[2], entry[3])
        title_dict[entry[0]] = dto

    def get_select_sentence(self) -> str:
        """select文を返却する
        :return: select文
        """
        return self.__SELECT_ALL_FROM

    def get_insert_sentence(self, title_dto: TitleDto) -> str:
        """insert文を返却する
        :param title_dto: タイトルDTO
        :return: insert文
        """
        value_data = self.__INSERT_DATA.format(name=title_dto.get_name(), category1=title_dto.get_overview(), category2=title_dto.get_purpose())
        table_data = self.__TABLE_DATA.format(table=DBAConst.title_data_base_name(), col1=DBAConst.name(), col2=DBAConst.overview(), col3=DBAConst.purpose())
        return self.__INSERT_INTO_TABLE_VALUE % (table_data, value_data)

    def get_delete_sentence(self, title_ids: list) -> str:
        """delete文を返却する(idで対象を指定して削除する)
        :param title_ids: 削除対象のid
        :return: delete文
        """
        return self.__DELETE_FROM_TABLE % ', '.join([str(title_id) for title_id in title_ids])

    def _check_data(self, dto: TitleDto) -> bool:
        """設定対象のDTOのデータの中身をチェックする
        @:param: dto 対象クラス
        @:return 正常に登録できるのであれば True、それ以外の場合は False
        """
        title = dto.get_name()
        for registered_dto in self.get_data_list().values():
            if title == registered_dto.get_name():
                return False
        return True









