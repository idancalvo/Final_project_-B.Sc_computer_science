1. הורד והתקן את PostgreSQL
https://www.postgresql.org/download/
2. הורד והתקן את Apache Tomcat
 https://tomcat.apache.org/download-90.cgi
3.לאחר התקנת Tomcat יש לערוך את הקובץ tomcat-users.xml בכדי להוסיף הרשאות מנהל.
4. לאחר הגדרת Tomcat יש להוסיף את קובץ ה WAR אל הנתיב CATALINA_HOME/webapps. בתיקייה
לחלופין ניתן לפרוס את קבצי המקור באותו נתיב
4. בסיום, יש לבצע אתחול לשרת ולהיכנס בנתיב שלו בדפדפן (בדרך כלל localhost:8080)
5. התקנת DB - לאחר התקנת PostgreSQL יש להיכנס אל הקובץ DBMangaer.java ולהגדיראת הנתיב והיוזר הנדרש להתחברות
	private static final String DATABASE_URL = "jdbc:postgresql://localhost:5433/LiveInMovieDB";
	private static final String USERNAME = "postgres";
	private static final String PASSWORD = "admin";
6. בסיום - יש להריץ את Main.java הנמצא בMainPackege, יש להקפיד לבטל את סימון ההערות על הקוד 
לאחר הרצת הקובץ ייבנו כל הטבלאות בDB ויוכנסו נתונים לדוגמא 
יוזר ברירת המחדל במסך הניהול הינו שם משתמש Admin וסיסמא Admin/


