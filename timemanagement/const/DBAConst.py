class DBAConst:
    '''
    データベースにアクセスする際に使用する定数クラス
    '''

    # データベースへのアクセス
    __user = "shogo"
    __password = "hayashi9999"
    __host = "localhost"
    __database = "stopwatch"
    __charset = "utf8"

    # データベース名
    __card_data_base_name = "card"
    __history_data_base_name = "history"
    __title_data_base_name = "title"

    # 各データベース共通の列名
    __name = "name"

    # CARDデータベースからデータを取得する
    __category1 = "category1"
    __category2 = "category2"
    __id = "id"
    __validate = "validation"
    __title = "title"

    # HISTORYデータベースからデータを取得する
    __card_id = "card_id"
    __result = "result"
    __time_unit_start = "time_unit_start"
    __time_unit_end = "time_unit_end"
    __date = "date"

    # TITLEデータベースからデータを取得する
    __purpose = "purpose"
    __overview = "overview"

    # EXECUTE文
    __select_from_all = "select * from %s"
    __insert_into = "insert into %s values(%s)"
    __select_from_part = "select * from %s where {terms}"

    def __init__(self):
        pass

    # データベースへのアクセス
    @staticmethod
    def user():
        return DBAConst.__user

    @staticmethod
    def password():
        return DBAConst.__password

    @staticmethod
    def host():
        return DBAConst.__host

    @staticmethod
    def database():
        return DBAConst.__database

    @staticmethod
    def charset():
        return DBAConst.__charset

    # データベース名
    @staticmethod
    def card_data_base_name():
        return DBAConst.__card_data_base_name

    @staticmethod
    def title_data_base_name():
        return DBAConst.__title_data_base_name

    @staticmethod
    def history_data_base_name():
        return DBAConst.__history_data_base_name

    # 各データベース共通の列名取得
    @staticmethod
    def name():
        return DBAConst.__name

    # cardデータベースの列名取得
    @staticmethod
    def category1():
        return DBAConst.__category1

    @staticmethod
    def category2():
        return DBAConst.__category2

    @staticmethod
    def id():
        return DBAConst.__id

    @staticmethod
    def validate():
        return DBAConst.__validate

    @staticmethod
    def card_data_base_name():
        return DBAConst.__card_data_base_name

    @staticmethod
    def title():
        return DBAConst.__title;

    # HISTORYデータベースからデータを取得する
    @staticmethod
    def card_id():
        return DBAConst.__card_id

    @staticmethod
    def result():
        return DBAConst.__result

    @staticmethod
    def time_unit_start():
        return DBAConst.__time_unit_start

    @staticmethod
    def time_unit_end():
        return DBAConst.__time_unit_end

    @staticmethod
    def date():
        return DBAConst.__date

    # TITLEデータベースからデータを取得する
    @staticmethod
    def purpose():
        return DBAConst.__purpose

    @staticmethod
    def overview():
        return DBAConst.__overview

    # EXECUTE文
    @staticmethod
    def select_from_all():
        return DBAConst.__select_from_all

    @staticmethod
    def select_from_part():
        return DBAConst.__select_from_part

    @staticmethod
    def insert_into():
        return DBAConst.__insert_into
