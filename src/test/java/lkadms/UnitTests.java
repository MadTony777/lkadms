package lkadms;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;

public class UnitTests extends Template {

    @BeforeEach
    public void executedBeforeEach(TestInfo testInfo) {
        log.info("\n...Starting test: " + testInfo.getDisplayName());
    }

    @AfterEach
    public void executedAfterEach() {
        log.info("...End test\n\n");
    }


    @Test
    public void policyInfoWithCaf() {
        String logs = testCase(environment, "infoWithCaf");
        assertThat(logs, containsString("Front-LkDMS REST: start processing /policy/info"));
        assertThat(logs, containsString("Front-LkDMS REST: Generating body"));
        assertThat(logs, containsString("Front-LkDMS REST: Generated body from xslt: <?xml"));
        assertThat(logs, containsString("LotusAdapter: call 'Lotus' service V2 operation 'GETDMSINFO"));
        assertThat(logs, containsString("LotusAdapter: call 'GETDMSINFO' with bodyclass ru.vsk.integration.servicemix.esb.front.lk.dms.v2.rest.service.GETDMSINFOREQUESTCLASS"));
        assertThat(logs, containsString("LotusAdapter: call 'Lotus' service V2 operation 'GETDMSINFO' is completed"));
        assertThat(logs, containsString("Front-LkDMS REST: after 'Lotus' V2 calling: ru.vsk.integration.servicemix.esb.front.lk.dms.v2.rest.service.GETDMSINFORETURNCLASS"));
    }

    @Test
    public void policyInfoNoCaf() {
        String logs = testCase(environment, "infoNoCaf");
        assertThat(logs, containsString("Front-LkDMS REST: start processing /policy/info"));
        assertThat(logs, containsString("Front-LkDMS REST: Generated body from xslt: <?xml"));
        assertThat(logs, containsString("LotusAdapter: call 'Lotus' service operation 'GETDMSINFO"));
        assertThat(logs, containsString("Front-LkDMS REST: after 'Lotus' calling: ru.vsk.integration.servicemix.esb.front.lk.dms.rest.service.GETDMSINFORETURNCLASS"));
    }

    @Test
    public void policyVariantInfoWithCaf() {
        String logs = testCase(environment, "variantInfoWithCaf");
        assertThat(logs, containsString("Front-LkDMS REST: start processing /policy/variantinfo"));
        assertThat(logs, containsString("Front-LkDMS REST: Generating body"));
        assertThat(logs, containsString("LotusAdapter: call 'Lotus' service V2 operation 'GETVARIANTPROGRAMINFO"));
        assertThat(logs, containsString("Front-LkDMS REST: Generated body from xslt: <?xml"));
        assertThat(logs, containsString("LotusAdapter: call 'GETVARIANTPROGRAMINFO' with bodyclass ru.vsk.integration.servicemix.esb.front.lk.dms.v2.rest.service.GETVARIANTPROGRAMINFOREQUESTCLASS"));
        assertThat(logs, containsString("LotusAdapter: call 'Lotus' service V2 operation 'GETVARIANTPROGRAMINFO' is completed"));
    }

    @Test
    public void policyVariantInfoNoCaf() {
        String logs = testCase(environment, "variantInfoNoCaf");
        assertThat(logs, containsString("Front-LkDMS REST: start processing /policy/variantinfo"));
        assertThat(logs, containsString("Front-LkDMS REST: Generated body from xslt: <?xml"));
        assertThat(logs, containsString("LotusAdapter: call 'Lotus' service operation 'GETVARIANTPROGRAMINFO"));
        assertThat(logs, containsString("Front-LkDMS REST: after 'Lotus' calling: ru.vsk.integration.servicemix.esb.front.lk.dms.rest.service.GETVARIANTPROGRAMINFORETURNCLASS"));
    }


    @Test
    public void programFileWithCaf() {
        String logs = testCase(environment, "programFileWithCaf");
        assertThat(logs, containsString("Front-LkDMS REST: Generating body"));
        assertThat(logs, containsString("Front-LkDMS REST: start processing /v2/program/file"));
        assertThat(logs, containsString("LotusAdapter: call 'Lotus' service V2 operation 'GETPROGRAMFILEBYVARIANTANDINSURED"));
        assertThat(logs, containsString("Front-LkDMS REST: Generated body from xslt: <?xml"));
        assertThat(logs, containsString("LotusAdapter: call 'GETPROGRAMFILEBYVARIANTANDINSURED' with bodyclass ru.vsk.integration.servicemix.esb.front.lk.dms.v2.rest.service.GETVARIANTPROGRAMINFOREQUESTCLASS"));
        assertThat(logs, containsString("LotusAdapter: call 'Lotus' service V2 operation 'GETPROGRAMFILEBYVARIANTANDINSURED' is completed"));
    }

    @Test
    public void programFileNoCaf() {
        String logs = testCase(environment, "programFileNoCaf");
        assertThat(logs, containsString("Front-LkDMS REST: start processing /program/file"));
        assertThat(logs, containsString("LotusAdapter: call 'Lotus' service operation 'GETPROGRAMFILEBYVARIANTANDINSURED"));
        assertThat(logs, containsString("Front-LkDMS REST: Generated body from xslt: <?xml"));
        assertThat(logs, containsString("Front-LkDMS REST: after 'Lotus' calling: ru.vsk.integration.servicemix.esb.front.lk.dms.rest.service.GETPROGRAMFILEBYVARIANTRETURNCLASS"));
    }


    @Test
    public void policyInfoWithCafDMZ() {
        if (environment.equals("stage")) {
            String logs = testCase(environment, "infoWithCafDMZ");
            assertThat(logs, containsString("Front-LkDMS REST: start processing /policy/info"));
            assertThat(logs, containsString("Front-LkDMS REST: Generating body"));
            assertThat(logs, containsString("Front-LkDMS REST: Generated body from xslt: <?xml"));
            assertThat(logs, containsString("LotusAdapter: call 'Lotus' service V2 operation 'GETDMSINFO"));
            assertThat(logs, containsString("LotusAdapter: call 'GETDMSINFO' with bodyclass ru.vsk.integration.servicemix.esb.front.lk.dms.v2.rest.service.GETDMSINFOREQUESTCLASS"));
            assertThat(logs, containsString("LotusAdapter: call 'Lotus' service V2 operation 'GETDMSINFO' is completed"));
            assertThat(logs, containsString("Front-LkDMS REST: after 'Lotus' V2 calling: ru.vsk.integration.servicemix.esb.front.lk.dms.v2.rest.service.GETDMSINFORETURNCLASS"));
        } else {
            log.info("DOES NOT EXIST ON TEST");
        }
    }

    @Test
    public void policyInfoNoCafDMZ() {
        if (environment.equals("stage")) {
            String logs = testCase(environment, "infoNoCafDMZ");
            assertThat(logs, containsString("Front-LkDMS REST: start processing /policy/info"));
            assertThat(logs, containsString("Front-LkDMS REST: Generated body from xslt: <?xml"));
            assertThat(logs, containsString("LotusAdapter: call 'Lotus' service operation 'GETDMSINFO"));
            assertThat(logs, containsString("Front-LkDMS REST: after 'Lotus' calling: ru.vsk.integration.servicemix.esb.front.lk.dms.rest.service.GETDMSINFORETURNCLASS"));
        } else {
            log.info("DOES NOT EXIST ON TEST");
        }
    }

    @Test
    public void policyVariantInfoWithCafDMZ() {
        if (environment.equals("stage")) {
            String logs = testCase(environment, "variantInfoWithCafDMZ");
            assertThat(logs, containsString("Front-LkDMS REST: start processing /policy/variantinfo"));
            assertThat(logs, containsString("Front-LkDMS REST: Generating body"));
            assertThat(logs, containsString("LotusAdapter: call 'Lotus' service V2 operation 'GETVARIANTPROGRAMINFO"));
            assertThat(logs, containsString("Front-LkDMS REST: Generated body from xslt: <?xml"));
            assertThat(logs, containsString("LotusAdapter: call 'GETVARIANTPROGRAMINFO' with bodyclass ru.vsk.integration.servicemix.esb.front.lk.dms.v2.rest.service.GETVARIANTPROGRAMINFOREQUESTCLASS"));
            assertThat(logs, containsString("LotusAdapter: call 'Lotus' service V2 operation 'GETVARIANTPROGRAMINFO' is completed"));
        } else {
            log.info("DOES NOT EXIST ON TEST");
        }
    }

    @Test
    public void policyVariantInfoNoCafDMZ() {
        if (environment.equals("stage")) {
            String logs = testCase(environment, "variantInfoNoCafDMZ");
            assertThat(logs, containsString("Front-LkDMS REST: start processing /policy/variantinfo"));
            assertThat(logs, containsString("Front-LkDMS REST: Generated body from xslt: <?xml"));
            assertThat(logs, containsString("LotusAdapter: call 'Lotus' service operation 'GETVARIANTPROGRAMINFO"));
            assertThat(logs, containsString("Front-LkDMS REST: after 'Lotus' calling: ru.vsk.integration.servicemix.esb.front.lk.dms.rest.service.GETVARIANTPROGRAMINFORETURNCLASS"));
        } else {
            log.info("DOES NOT EXIST ON TEST");
        }
    }


    @Test
    public void programFileWithCafDMZ() {
        if (environment.equals("stage")) {
            String logs = testCase(environment, "programFileWithCafDMZ");
            assertThat(logs, containsString("Front-LkDMS REST: Generating body"));
            assertThat(logs, containsString("Front-LkDMS REST: start processing /v2/program/file"));
            assertThat(logs, containsString("LotusAdapter: call 'Lotus' service V2 operation 'GETPROGRAMFILEBYVARIANTANDINSURED"));
            assertThat(logs, containsString("Front-LkDMS REST: Generated body from xslt: <?xml"));
            assertThat(logs, containsString("LotusAdapter: call 'GETPROGRAMFILEBYVARIANTANDINSURED' with bodyclass ru.vsk.integration.servicemix.esb.front.lk.dms.v2.rest.service.GETVARIANTPROGRAMINFOREQUESTCLASS"));
            assertThat(logs, containsString("LotusAdapter: call 'Lotus' service V2 operation 'GETPROGRAMFILEBYVARIANTANDINSURED' is completed"));
        } else {
            log.info("DOES NOT EXIST ON TEST");
        }
    }

    @Test
    public void programFileNoCafDMZ() {
        if (environment.equals("stage")) {
            String logs = testCase(environment, "programFileNoCafDMZ");
            assertThat(logs, containsString("Front-LkDMS REST: start processing /program/file"));
            assertThat(logs, containsString("LotusAdapter: call 'Lotus' service operation 'GETPROGRAMFILEBYVARIANTANDINSURED"));
            assertThat(logs, containsString("Front-LkDMS REST: Generated body from xslt: <?xml"));
            assertThat(logs, containsString("Front-LkDMS REST: after 'Lotus' calling: ru.vsk.integration.servicemix.esb.front.lk.dms.rest.service.GETPROGRAMFILEBYVARIANTRETURNCLASS"));
        } else {
            log.info("DOES NOT EXIST ON TEST");
        }
    }


    @Test
    public void policyInfoWithCafBalans() {
        if (environment.equals("stage")) {
            String logs = testCase(environment, "infoWithCafBalans");
            assertThat(logs, containsString("Front-LkDMS REST: start processing /policy/info"));
            assertThat(logs, containsString("Front-LkDMS REST: Generating body"));
            assertThat(logs, containsString("Front-LkDMS REST: Generated body from xslt: <?xml"));
            assertThat(logs, containsString("LotusAdapter: call 'Lotus' service V2 operation 'GETDMSINFO"));
            assertThat(logs, containsString("LotusAdapter: call 'GETDMSINFO' with bodyclass ru.vsk.integration.servicemix.esb.front.lk.dms.v2.rest.service.GETDMSINFOREQUESTCLASS"));
            assertThat(logs, containsString("LotusAdapter: call 'Lotus' service V2 operation 'GETDMSINFO' is completed"));
            assertThat(logs, containsString("Front-LkDMS REST: after 'Lotus' V2 calling: ru.vsk.integration.servicemix.esb.front.lk.dms.v2.rest.service.GETDMSINFORETURNCLASS"));
        } else {
            log.info("DOES NOT EXIST ON TEST");
        }
    }

    @Test
    public void policyInfoNoCafBalans() {
        if (environment.equals("stage")) {
            String logs = testCase(environment, "infoNoCafBalans");
            assertThat(logs, containsString("Front-LkDMS REST: start processing /policy/info"));
            assertThat(logs, containsString("Front-LkDMS REST: Generated body from xslt: <?xml"));
            assertThat(logs, containsString("LotusAdapter: call 'Lotus' service operation 'GETDMSINFO"));
            assertThat(logs, containsString("Front-LkDMS REST: after 'Lotus' calling: ru.vsk.integration.servicemix.esb.front.lk.dms.rest.service.GETDMSINFORETURNCLASS"));
        } else {
            log.info("DOES NOT EXIST ON TEST");
        }
    }

    @Test
    public void policyVariantInfoWithCafBalans() {
        if (environment.equals("stage")) {
            String logs = testCase(environment, "variantInfoWithCafBalans");
            assertThat(logs, containsString("Front-LkDMS REST: start processing /policy/variantinfo"));
            assertThat(logs, containsString("Front-LkDMS REST: Generating body"));
            assertThat(logs, containsString("LotusAdapter: call 'Lotus' service V2 operation 'GETVARIANTPROGRAMINFO"));
            assertThat(logs, containsString("Front-LkDMS REST: Generated body from xslt: <?xml"));
            assertThat(logs, containsString("LotusAdapter: call 'GETVARIANTPROGRAMINFO' with bodyclass ru.vsk.integration.servicemix.esb.front.lk.dms.v2.rest.service.GETVARIANTPROGRAMINFOREQUESTCLASS"));
            assertThat(logs, containsString("LotusAdapter: call 'Lotus' service V2 operation 'GETVARIANTPROGRAMINFO' is completed"));
        } else {
            log.info("DOES NOT EXIST ON TEST");
        }
    }

    @Test
    public void policyVariantInfoNoCafBalans() {
        if (environment.equals("stage")) {
            String logs = testCase(environment, "variantInfoNoCafBalans");
            assertThat(logs, containsString("Front-LkDMS REST: start processing /policy/variantinfo"));
            assertThat(logs, containsString("Front-LkDMS REST: Generated body from xslt: <?xml"));
            assertThat(logs, containsString("LotusAdapter: call 'Lotus' service operation 'GETVARIANTPROGRAMINFO"));
            assertThat(logs, containsString("Front-LkDMS REST: after 'Lotus' calling: ru.vsk.integration.servicemix.esb.front.lk.dms.rest.service.GETVARIANTPROGRAMINFORETURNCLASS"));
        } else {
            log.info("DOES NOT EXIST ON TEST");
        }
    }


    @Test
    public void programFileWithCafBalans() {
        if (environment.equals("stage")) {
            String logs = testCase(environment, "programFileWithCafBalans");
            assertThat(logs, containsString("Front-LkDMS REST: Generating body"));
            assertThat(logs, containsString("Front-LkDMS REST: start processing /v2/program/file"));
            assertThat(logs, containsString("LotusAdapter: call 'Lotus' service V2 operation 'GETPROGRAMFILEBYVARIANTANDINSURED"));
            assertThat(logs, containsString("Front-LkDMS REST: Generated body from xslt: <?xml"));
            assertThat(logs, containsString("LotusAdapter: call 'GETPROGRAMFILEBYVARIANTANDINSURED' with bodyclass ru.vsk.integration.servicemix.esb.front.lk.dms.v2.rest.service.GETVARIANTPROGRAMINFOREQUESTCLASS"));
            assertThat(logs, containsString("LotusAdapter: call 'Lotus' service V2 operation 'GETPROGRAMFILEBYVARIANTANDINSURED' is completed"));
        } else {
            log.info("DOES NOT EXIST ON TEST");
        }
    }

    @Test
    public void programFileNoCafBalans() {
        if (environment.equals("stage")) {
            String logs = testCase(environment, "programFileNoCafBalans");
            assertThat(logs, containsString("Front-LkDMS REST: start processing /program/file"));
            assertThat(logs, containsString("LotusAdapter: call 'Lotus' service operation 'GETPROGRAMFILEBYVARIANTANDINSURED"));
            assertThat(logs, containsString("Front-LkDMS REST: Generated body from xslt: <?xml"));
            assertThat(logs, containsString("Front-LkDMS REST: after 'Lotus' calling: ru.vsk.integration.servicemix.esb.front.lk.dms.rest.service.GETPROGRAMFILEBYVARIANTRETURNCLASS"));
        } else {
            log.info("DOES NOT EXIST ON TEST");
        }
    }

}

