import requests
import datetime
from loggerset import logger
from bs4 import BeautifulSoup

def crawlStockData(stockCode):

    url = 'https://finance.naver.com/item/main.nhn?code=' + stockCode

    response = requests.get(url)
    price = None
    now = None

    if response.status_code == 200:
        html = response.text
        soup = BeautifulSoup(html, 'html.parser')
        currentPrice = soup.find('p', {'class' : 'no_today'})

        price = int(currentPrice.find('span', {'class':'blind'}).text.replace(',',''))
        now = datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S')
    else:
        now = datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S')

    logger.info('crawled current stock price %s %d', stockCode, price)
    return now, price

if __name__ == '__main__':
    crawlStockData('035720')