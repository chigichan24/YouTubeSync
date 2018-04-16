package com.cyder.atsushi.youtubesync.util


class NotFilledFormsException : Exception("forms are not filled!")
class NotAgreeTermsException : Exception("not agree terms!")
class NotValidEmailException : Exception("email address is not valid!")
class TooShortPasswordException : Exception("password is too short!")
class NotSamePasswordException : Exception("passwords are not same!")
class CannotJoinRoomException : Exception("can not join room because of banned or mistook key")