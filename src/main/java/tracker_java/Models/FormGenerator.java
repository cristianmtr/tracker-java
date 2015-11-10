package tracker_java.Models;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.regex.Pattern;

/**
 * Created by CristianMitroi on 10-11-2015.
 */
public enum FormGenerator {
    INSTANCE;

    public HashMap generateForm(Class theModel) {
        HashMap formHashMap = new HashMap();

        for(Field field : theModel.getDeclaredFields()){
            Class type = field.getType();
            String[] typeSplitByDot = type.toString().split(Pattern.quote("."));
            String prettyType = typeSplitByDot[typeSplitByDot.length-1];
            String name = field.getName();
            Annotation[] annotations = field.getDeclaredAnnotations();
            for (Annotation ann : annotations) {
                if ( ann instanceof FormField ) {
                    formHashMap.put(name, prettyType);
                }
            }
        }

        return formHashMap;
    }
}
