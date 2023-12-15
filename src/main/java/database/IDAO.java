package database;


public interface IDAO<T> {

   void createTable();

    void removeTable();

    // DELETE ALL
    void truncate();
}
