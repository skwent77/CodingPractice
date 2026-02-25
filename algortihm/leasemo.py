import sqlite3 # 가장 시작하기 쉬운 가벼운 DB

# DB 연결 (DataGrip으로 이 파일을 열어서 볼 수 있습니다)
conn = sqlite3.connect('leasemo_cars.db')
cur = conn.cursor()

# 테이블 생성 (최초 1회)
cur.execute('''CREATE TABLE IF NOT EXISTS cars 
               (name TEXT, price TEXT, mileage TEXT, options TEXT)''')

# 데이터 삽입 (아까 추출한 .cls, .prc 정보를 활용)
cur.execute("INSERT INTO cars VALUES (?, ?, ?, ?)", 
            (full_name, price, mileage, "핸들O, 시트O"))

conn.commit()
conn.close()
