package test;

import Services.DatabaseIO;

public class DatabaseIOTest {
    public static void main(String[] args) {
        DatabaseIO db = new DatabaseIO("root","root","localhost");{
            db.Connect();
            db.Update("CREATE DATABASE CDIO_DB_TEST");
            db.CloseConnection();
        }
    }
}
