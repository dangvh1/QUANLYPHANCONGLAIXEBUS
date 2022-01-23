package FileIOShare;

import java.util.Collection;

public class DataUtil {
    public static <T> boolean isEmptyCollection(Collection<T> collection){
        return collection == null || collection.isEmpty();
    }
    public static boolean isEmptyString(String fileName){
        return fileName == null || fileName.trim().equals("");
    }
}
