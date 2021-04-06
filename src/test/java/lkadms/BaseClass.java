package lkadms;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

public class BaseClass {
    private String arg = System.getProperty("arg", "stage");
    String environment = arg;

    private static String testLanUrl = "http://esb-test01.vsk.ru:8181/rest/front/lk/dms/";
    private static String stageLanUrl = "http://esb-stage.vsk.ru:8501/rest/front/lk/dms/";
    private static String stageDmzUrl = "http://esbext-stage.vsk.ru:8501/rest/front/lk/dms/";
    private static String stageDMZbalansUrl = "http://ws-test.vsk.ru:8501/rest/front/lk/dms/";
    static Logger log = LoggerFactory.getLogger(UnitTests.class);

    private static List<String> getValuesForGivenKey(String jsonArrayStr, String key) {
        JSONArray jsonArray = new JSONArray(jsonArrayStr);
        return IntStream.range(0, jsonArray.length())
                .mapToObj(index -> ((JSONObject) jsonArray.get(index)).optString(key))
                .collect(Collectors.toList());
    }

    static String getURLkibana(String environment){
        String urlKibanaSearch ="";
        switch (environment){
            case "stage":
                urlKibanaSearch = "esb-stage";
                break;
            case "test":
                urlKibanaSearch = "esb-test";
                break;
        }
        return urlKibanaSearch;
    }

    static String getURL(String environment, String fileName) {
        String url = "";
        switch (environment) {
            case "stage":
                switch (fileName) {
                    case "infoNoCaf":
                    case "programFileWithCaf":
                    case "programFileNoCaf":
                    case "variantInfoWithCaf":
                    case "infoWithCaf":
                    case "variantInfoNoCaf":
                        url = stageLanUrl;
                        break;
                    case "infoNoCafDMZ":
                    case "programFileWithCafDMZ":
                    case "programFileNoCafDMZ":
                    case "variantInfoWithCafDMZ":
                    case "infoWithCafDMZ":
                    case "variantInfoNoCafDMZ":
                        url = stageDmzUrl;
                        break;
                    case "infoNoCafBalans":
                    case "programFileWithCafBalans":
                    case "programFileNoCafBalans":
                    case "variantInfoWithCafBalans":
                    case "infoWithCafBalans":
                    case "variantInfoNoCafBalans":
                        url = stageDMZbalansUrl;
                }
                break;
            case "test":
                url = testLanUrl;
                break;
        }
        return url;
    }

    static String getOperation(String fileName) {
        String operation;
        switch (fileName) {
            case "infoNoCaf":
            case "infoNoCafDMZ":
            case "infoNoCafBalans":
                operation = "policy/info";
                break;
            case "infoWithCaf":
            case "infoWithCafDMZ":
            case "infoWithCafBalans":
                operation = "v2/policy/info";
                break;
            case "variantInfoNoCaf":
            case "variantInfoNoCafDMZ":
            case "variantInfoNoCafBalans":
                operation = "policy/variantinfo";
                break;
            case "variantInfoWithCaf":
            case "variantInfoWithCafDMZ":
            case "variantInfoWithCafBalans":
                operation = "v2/policy/variantinfo";
                break;
            case "programFileNoCaf":
            case "programFileNoCafDMZ":
            case "programFileNoCafBalans":
                operation = "program/file";
                break;
            case "programFileWithCaf":
            case "programFileWithCafDMZ":
            case "programFileWithCafBalans":
                operation = "v2/program/file";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + fileName);
        }
        return operation;
    }

    static HashMap<String, String> getHeaders(String fileName, String cid) {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("X-VSK-CorrelationId", cid);
        switch (fileName) {
            case "infoNoCaf":
            case "infoNoCafDMZ":
            case "infoNoCafBalans":
            case "infoWithCaf":
            case "infoWithCafDMZ":
            case "infoWithCafBalans":
                headers.put("NUMBER_DMS", "20001TST00001-1");
                headers.put("UNID", "87C5600458AE4D98905BE487665599FC");
                break;
            case "variantInfoNoCaf":
            case "variantInfoNoCafDMZ":
            case "variantInfoNoCafBalans":
            case "programFileWithCaf":
            case "programFileWithCafDMZ":
            case "programFileWithCafBalans":
            case "variantInfoWithCaf":
            case "variantInfoWithCafDMZ":
            case "variantInfoWithCafBalans":
            case "programFileNoCaf":
            case "programFileNoCafDMZ":
            case "programFileNoCafBalans":
                headers.put("NUMBER_DMS", "20001TST00001-1");
                headers.put("UNID", "87C5600458AE4D98905BE487665599FC");
                headers.put("GROUP_VARIANT_UNID", "00000000000000000000000000000000");
                headers.put("VARIANT_PROGRAMS_UNID", "469A590A66D2F9F64325853C00400274");
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + fileName);
        }
        return headers;
    }

    static void assertREST(String fileName, String serviceResponse) {
        List <String> tags;
        List <String> tags1;
        List <String> tags2;
        String varPrTag;
        switch (fileName) {
            case "infoNoCaf":
                assertThat(serviceResponse, not(containsString("STOP_DATE")));
                assertThat(serviceResponse, containsString("VARIANT_PROGRAMS"));
                tags = getValuesForGivenKey("[" + serviceResponse + "]", "GROUP_VARIANTS");
                String groupVarSTag= String.valueOf(tags);
                tags1 = getValuesForGivenKey( groupVarSTag , "GROUP_VARIANT");
                String groupVarTag = "[" + (String.valueOf(tags1)).replace("[", "").replace("]", "")+ "]";
                tags2 = getValuesForGivenKey(groupVarTag , "VARIANT_PROGRAMS");
                varPrTag = String.valueOf(tags2);
                assertThat(varPrTag, containsString("VARIANT_PROGRAM"));
                break;
            case "infoWithCaf":
                assertThat(serviceResponse, containsString("STOP_DATE"));
                assertThat(serviceResponse, containsString("VARIANT_PROGRAMS"));
                tags = getValuesForGivenKey("[" + serviceResponse + "]", "GROUP_VARIANTS");
                groupVarSTag= String.valueOf(tags);
                tags1 = getValuesForGivenKey( groupVarSTag , "GROUP_VARIANT");
                groupVarTag = "[" + (String.valueOf(tags1)).replace("[", "").replace("]", "")+ ":";
                assertThat(groupVarTag, containsString("VARIANT_PROGRAMS"));
                assertThat(groupVarTag, containsString("VARIANT_PROGRAM"));
                break;
            case "variantInfoNoCaf":
                assertThat(serviceResponse, not(containsString("VARIANT_PROGRAMS_UNID")));
//                tags = getValuesForGivenKey("[" + serviceResponse + "]", "GROUP_VARIANT");
//                groupVarSTag= String.valueOf(tags);
//                tags1 = getValuesForGivenKey( groupVarSTag , "VARIANT_PROGRAM");
//                groupVarTag = "[" + (String.valueOf(tags1)).replace("[", "").replace("]", "")+ "]";
//                tags2 = getValuesForGivenKey(groupVarTag , "GROUP_CLINICS");
//                String groupClinics = String.valueOf(tags2);
//                List <String> tags3 = getValuesForGivenKey(groupClinics , "CLINICS");
//                String clinics = String.valueOf(tags3);
                assertThat(serviceResponse, containsString("VISIT_TYPE"));
                break;
            case "variantInfoWithCaf":
                assertThat(serviceResponse, containsString("VARIANT_PROGRAMS_UNID"));
                assertThat(serviceResponse, containsString("VISIT_TYPE"));
                break;
            case "programFileNoCaf":
            case "programFileWithCaf":
                assertThat(serviceResponse, containsString("STATUS"));
                assertThat(serviceResponse, containsString(": 1"));
                assertThat(serviceResponse, containsString("ERRORS"));
                assertThat(serviceResponse, containsString(": null"));
                assertThat(serviceResponse, containsString("BODY"));
                break;
        }
    }

    static String getSearchingLog(String fileName) {
        String searchingLog;
        switch (fileName) {
            case "infoNoCaf":
            case "infoNoCafDMZ":
            case "infoNoCafBalans":
                searchingLog = "Front-LkDMS REST: after 'Lotus' calling: ru.vsk.integration.servicemix.esb.front.lk.dms.rest.service.GETDMSINFORETURNCLASS";
                break;
            case "infoWithCaf":
            case "infoWithCafDMZ":
            case "infoWithCafBalans":
                searchingLog = "Front-LkDMS REST: after 'Lotus' V2 calling: ru.vsk.integration.servicemix.esb.front.lk.dms.v2.rest.service.GETDMSINFORETURNCLASS";
                break;
            case "variantInfoNoCaf":
            case "variantInfoNoCafDMZ":
            case "variantInfoNoCafBalans":
                searchingLog = "Front-LkDMS REST: after 'Lotus' calling: ru.vsk.integration.servicemix.esb.front.lk.dms.rest.service.GETVARIANTPROGRAMINFORETURNCLASS";
                break;
            case "variantInfoWithCaf":
            case "variantInfoWithCafDMZ":
            case "variantInfoWithCafBalans":
                searchingLog = "LotusAdapter: call 'Lotus' service V2 operation 'GETVARIANTPROGRAMINFO' is completed";
                break;
            case "programFileNoCaf":
            case "programFileNoCafDMZ":
            case "programFileNoCafBalans":
                searchingLog = "Front-LkDMS REST: after 'Lotus' calling: ru.vsk.integration.servicemix.esb.front.lk.dms.rest.service.GETPROGRAMFILEBYVARIANTRETURNCLASS";
                break;
            case "programFileWithCaf":
            case "programFileWithCafDMZ":
            case "programFileWithCafBalans":
                searchingLog = "LotusAdapter: call 'Lotus' service V2 operation 'GETPROGRAMFILEBYVARIANTANDINSURED' is completed";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + fileName);
        }
        return searchingLog;
    }

}
