import pymysql

db=None
cursor=None

def get_cursor():
    global db,cursor
    if(db==None):
        db=pymysql.connect(
            user="jumo",
            passwd='jumo2021@!@#$',
            host="127.0.0.1",
            port=3306,
            db="jumo",
            charset="utf8"
        )
        cursor=db.cursor(pymysql.cursors.DictCursor)
    return cursor

def throwQuery(sql):
    my_cursor=get_cursor()
    my_cursor.execute(sql)
    result=my_cursor.fetchall()
    return result

