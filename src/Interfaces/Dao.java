package interfaces;

import java.util.ArrayList;

public interface Dao {
    
    Object get(Object id);

    ArrayList search(Object id);
    
    ArrayList getAll();
    
    void save(Object t, String[] params);
    
    void update(Object t, String[] params);
    
    void delete(Object t);
}
