from os import terminal_size
from flask import signals
import pandas as pd
import datetime
import DBconnector as db


def EMA(data, period=20, column='Close'):
    return data[column].ewm(span=period, adjust=False).mean()


def MACD(data, period_long=26, period_short=12, period_signal=9, column='Close'):
    ShortEMA = EMA(data, period_short, column=column)
    LongEMA = EMA(data, period_long, column=column)
    data['macd'] = ShortEMA - LongEMA
    data['signalLine'] = EMA(data, period_signal, column='macd')
    return data


def isLong_Full_Mae(macd, signal):
    if macd > 0:
        return "BUY"
    elif macd == 0:
        return "STAY"
    else:
        return "SELL"


def isShort_Full_Mae(macd, signal):
    if macd > signal:
        return "BUY"
    elif macd == signal:
        return "STAY"
    else:
        return "SELL"


def getEMA_MACD(name):
    result = db.throwQuery(f"""SELECT code,name,time,Close FROM price WHERE name="{name}";""")
    df = pd.DataFrame(result)
    df['time'] = pd.to_datetime(df['time'], format='%Y-%m-%d')
    df['year'] = df['time'].dt.year
    df['month'] = df['time'].dt.month
    df['day'] = df['time'].dt.day
    MACD(df)
    df['isLongTermFullmaesu'] = df.apply(lambda x: isLong_Full_Mae(x['macd'], x['signalLine']), axis=1)
    df['isShortTermFullmaesu'] = df.apply(lambda x: isShort_Full_Mae(x['macd'], x['signalLine']), axis=1)
    return df
