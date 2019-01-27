class CardDto:

    def __init__(self, card_id: int, category1: str, category2: str, title: str, validate: int):
        self.__card_id = card_id
        self.__category1 = category1
        self.__category2 = category2
        self.__title = title
        self.__validate = validate

    def get_id(self):
        return self.__card_id

    def set_id(self, card_id):
        self.__card_id = card_id

    def get_category1(self):
        return self.__category1

    def set_category1(self, category1):
        self.__category1 = category1

    def get_category2(self):
        return self.__category2

    def set_category2(self, category2):
        self.__category2 = category2

    def get_title(self):
        return self.__title

    def set_title(self, title):
        self.__title = title

    def get_validate(self):
        return self.__validate

    def set_validate(self, validate):
        self.__validate = validate

    # TODO have to delete
    def test(self):
        print("+ " + str(self.get_id()) + ":" + self.get_category1() + ":" + self.get_category2() + ":" + self.get_title() + ":" + str(self.get_validate()))
