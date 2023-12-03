package com.kapsta.handbagshop.framework.exceptions

class BaseException(message: String) : Exception(message)

val InvalidUserInput = BaseException("Invalid input")
val UserNotFoundException = BaseException("User with given credentials not found")
