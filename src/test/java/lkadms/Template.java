package lkadms;


import java.util.List;

import static lkadms.Kibana.searchByCID;

public class Template extends Requests {
    public static String testCase(String environment, String fileName) {
        List<String> response = request(environment, fileName);
        String CID= response.get(1);
        return searchByCID(environment, CID, fileName);
    }
}
