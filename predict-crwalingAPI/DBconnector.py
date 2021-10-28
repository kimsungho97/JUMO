import pymysql

db=None
cursor=None

def get_cursor():
    global db,cursor
    if(db==None):
        db=pymysql.connect(
            user="jumo",
            passwd='jumo2021@!@#$',
            host="223.194.70.104",
            port=16562,
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

