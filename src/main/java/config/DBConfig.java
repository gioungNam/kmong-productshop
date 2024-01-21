package config;

/**
 * db config 설정
 */
public class DBConfig {
        public static final String DATABASE_NAME = "jv4_db";
        public static final String PROPERTIES = "?characterEncoding=utf-8";
        // 필요시 변경하세요!
        public static final String DB_URL = "jdbc:mysql://localhost:3306/" + DATABASE_NAME + PROPERTIES;
        // 필요시 변경하세요!
        public static final String DB_USER = "root";
        // 필요시 변경하세요!
        public static final String DB_PASSWORD = "";
}
