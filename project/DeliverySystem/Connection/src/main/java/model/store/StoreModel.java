package model.store;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Sissel on 2015/10/24.
 */
public class StoreModel implements Serializable{
    class StoreShelf {
        List<StoreLocation> locations;
        int	shelfID;
    }
    class StoreRow {
        List<StoreShelf> shelves;
        int	rowID;
    }
    class StoreArea {
        List<StoreRow> rows;
        StoreAreaCode	areaID;
    }
}
