class TitleDto:
    """
    タイトルDTO
    """

    def __init__(self, name: str, overview: str, purpose: str):
        self.__name = name
        self.__overview = overview
        self.__purpose = purpose

    def get_name(self):
        return self.__name

    def set_name(self, name):
        self.__name = name

    def get_overview(self):
        return self.__overview

    def set_overview(self, overview):
        self.__overview = overview

    def get_purpose(self):
        return self.__purpose

    def set_purpose(self, purpose):
        self.__purpose = purpose
