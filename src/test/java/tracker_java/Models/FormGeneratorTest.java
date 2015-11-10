package tracker_java.Models;

import junit.framework.TestCase;

import java.util.HashMap;

/**
 * Created by CristianMitroi on 10-11-2015.
 */
public class FormGeneratorTest extends TestCase {

    public class ExampleModel {
        private Integer memberid;

        @FormField
        private String email;
    }

    public void testGenerateForm() throws Exception {
        HashMap exampleFormHashMap;
        exampleFormHashMap = FormGenerator.INSTANCE.generateForm(ExampleModel.class);
        HashMap correctHashMap = new HashMap();
        correctHashMap.put("email", "String");

        assertEquals(exampleFormHashMap, correctHashMap);
    }
}