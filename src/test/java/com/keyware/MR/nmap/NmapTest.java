package com.keyware.MR.nmap;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nmap4j.core.flags.Flag;
import org.nmap4j.core.nmap.ExecutionResults;
import org.nmap4j.core.scans.BaseScan;
import org.nmap4j.data.NMapRun;
import org.nmap4j.data.host.ports.Port;
import org.nmap4j.data.nmaprun.Host;
import org.nmap4j.parser.OnePassParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class NmapTest {

    @Value("${nmap.path}")
    private String nmapPath;
//
//    @Autowired
//    private HostPortScanner portScanner;

    @Test
    public void testHostScan() {
        System.out.println("12");
//        try {
//            String[] includeHosts = {"172.16.36.182"};
//            String[] excludeHosts = {};
//            BaseScan discovery = new BaseScan();
//            discovery.setNMapPath(nmapPath);
//            discovery.includeHosts(includeHosts);
//            /*discovery.getArgumentProperties().removeFlag(Flag.HOST_SCAN);
//            discovery.getArgumentProperties().addFlag(Flag.PING_SCAN);*/
//            discovery.addFlag(Flag.PING_SCAN);
//            //discovery.addFlag(Flag.OS_DETECTION);
//            ExecutionResults results = discovery.executeScan();
//            if (results.hasErrors()) {
//                System.out.println("ERROR:" + results.getErrors());
//            } else {
//                String output = results.getOutput();
//                System.out.println("SUCCESS:" + output);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @Test
    public void portScan() {
        try {
            String[] includeHosts = {"172.16.36.184"};
            BaseScan scan = new BaseScan(nmapPath);
            scan.includeHosts(includeHosts);
            //scan.addFlag(Flag.OPEN);
            scan.addFlag(Flag.SERVICE_VERSION);
            scan.addFlag(Flag.OS_DETECTION);
            ExecutionResults results = scan.executeScan();
            if (results.hasErrors()) {
                OnePassParser passParser = new OnePassParser();
                NMapRun nMapRun = passParser.parse(results.getOutput(), OnePassParser.STRING_INPUT);
                if (nMapRun == null) {
                    log.error("nMapRun is null");
                } else {
                    for (Host h : nMapRun.getHosts()) {
                        log.info("host: {}", h);
                        for (Port port : h.getPorts().getPorts()) {
                            log.info("port: {}", port);
                        }
                    }
                }
            } else {
                log.error("扫描结果异常:\nERROR:{};\nOUTPUT:{}", results.getErrors(), results.getOutput());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testParseResult(){
        String testResult = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<!DOCTYPE nmaprun>\n" +
                "<?xml-stylesheet href=\"file:///D:/Program Files (x86)/Nmap/nmap.xsl\" type=\"text/xsl\"?>\n" +
                "<!-- Nmap 7.92 scan initiated Tue Apr 26 11:10:23 2022 as: &quot;D:\\\\Program Files (x86)\\\\Nmap\\\\nmap.exe&quot; -oX - -sV -O 172.16.36.184 -->\n" +
                "<nmaprun scanner=\"nmap\" args=\"&quot;D:\\\\Program Files (x86)\\\\Nmap\\\\nmap.exe&quot; -oX - -sV -O 172.16.36.184\" start=\"1650942623\" startstr=\"Tue Apr 26 11:10:23 2022\" version=\"7.92\" xmloutputversion=\"1.05\">\n" +
                "<scaninfo type=\"syn\" protocol=\"tcp\" numservices=\"1000\" services=\"1,3-4,6-7,9,13,17,19-26,30,32-33,37,42-43,49,53,70,79-85,88-90,99-100,106,109-111,113,119,125,135,139,143-144,146,161,163,179,199,211-212,222,254-256,259,264,280,301,306,311,340,366,389,406-407,416-417,425,427,443-445,458,464-465,481,497,500,512-515,524,541,543-545,548,554-555,563,587,593,616-617,625,631,636,646,648,666-668,683,687,691,700,705,711,714,720,722,726,749,765,777,783,787,800-801,808,843,873,880,888,898,900-903,911-912,981,987,990,992-993,995,999-1002,1007,1009-1011,1021-1100,1102,1104-1108,1110-1114,1117,1119,1121-1124,1126,1130-1132,1137-1138,1141,1145,1147-1149,1151-1152,1154,1163-1166,1169,1174-1175,1183,1185-1187,1192,1198-1199,1201,1213,1216-1218,1233-1234,1236,1244,1247-1248,1259,1271-1272,1277,1287,1296,1300-1301,1309-1311,1322,1328,1334,1352,1417,1433-1434,1443,1455,1461,1494,1500-1501,1503,1521,1524,1533,1556,1580,1583,1594,1600,1641,1658,1666,1687-1688,1700,1717-1721,1723,1755,1761,1782-1783,1801,1805,1812,1839-1840,1862-1864,1875,1900,1914,1935,1947,1971-1972,1974,1984,1998-2010,2013,2020-2022,2030,2033-2035,2038,2040-2043,2045-2049,2065,2068,2099-2100,2103,2105-2107,2111,2119,2121,2126,2135,2144,2160-2161,2170,2179,2190-2191,2196,2200,2222,2251,2260,2288,2301,2323,2366,2381-2383,2393-2394,2399,2401,2492,2500,2522,2525,2557,2601-2602,2604-2605,2607-2608,2638,2701-2702,2710,2717-2718,2725,2800,2809,2811,2869,2875,2909-2910,2920,2967-2968,2998,3000-3001,3003,3005-3007,3011,3013,3017,3030-3031,3052,3071,3077,3128,3168,3211,3221,3260-3261,3268-3269,3283,3300-3301,3306,3322-3325,3333,3351,3367,3369-3372,3389-3390,3404,3476,3493,3517,3527,3546,3551,3580,3659,3689-3690,3703,3737,3766,3784,3800-3801,3809,3814,3826-3828,3851,3869,3871,3878,3880,3889,3905,3914,3918,3920,3945,3971,3986,3995,3998,4000-4006,4045,4111,4125-4126,4129,4224,4242,4279,4321,4343,4443-4446,4449,4550,4567,4662,4848,4899-4900,4998,5000-5004,5009,5030,5033,5050-5051,5054,5060-5061,5080,5087,5100-5102,5120,5190,5200,5214,5221-5222,5225-5226,5269,5280,5298,5357,5405,5414,5431-5432,5440,5500,5510,5544,5550,5555,5560,5566,5631,5633,5666,5678-5679,5718,5730,5800-5802,5810-5811,5815,5822,5825,5850,5859,5862,5877,5900-5904,5906-5907,5910-5911,5915,5922,5925,5950,5952,5959-5963,5987-5989,5998-6007,6009,6025,6059,6100-6101,6106,6112,6123,6129,6156,6346,6389,6502,6510,6543,6547,6565-6567,6580,6646,6666-6669,6689,6692,6699,6779,6788-6789,6792,6839,6881,6901,6969,7000-7002,7004,7007,7019,7025,7070,7100,7103,7106,7200-7201,7402,7435,7443,7496,7512,7625,7627,7676,7741,7777-7778,7800,7911,7920-7921,7937-7938,7999-8002,8007-8011,8021-8022,8031,8042,8045,8080-8090,8093,8099-8100,8180-8181,8192-8194,8200,8222,8254,8290-8292,8300,8333,8383,8400,8402,8443,8500,8600,8649,8651-8652,8654,8701,8800,8873,8888,8899,8994,9000-9003,9009-9011,9040,9050,9071,9080-9081,9090-9091,9099-9103,9110-9111,9200,9207,9220,9290,9415,9418,9485,9500,9502-9503,9535,9575,9593-9595,9618,9666,9876-9878,9898,9900,9917,9929,9943-9944,9968,9998-10004,10009-10010,10012,10024-10025,10082,10180,10215,10243,10566,10616-10617,10621,10626,10628-10629,10778,11110-11111,11967,12000,12174,12265,12345,13456,13722,13782-13783,14000,14238,14441-14442,15000,15002-15004,15660,15742,16000-16001,16012,16016,16018,16080,16113,16992-16993,17877,17988,18040,18101,18988,19101,19283,19315,19350,19780,19801,19842,20000,20005,20031,20221-20222,20828,21571,22939,23502,24444,24800,25734-25735,26214,27000,27352-27353,27355-27356,27715,28201,30000,30718,30951,31038,31337,32768-32785,33354,33899,34571-34573,35500,38292,40193,40911,41511,42510,44176,44442-44443,44501,45100,48080,49152-49161,49163,49165,49167,49175-49176,49400,49999-50003,50006,50300,50389,50500,50636,50800,51103,51493,52673,52822,52848,52869,54045,54328,55055-55056,55555,55600,56737-56738,57294,57797,58080,60020,60443,61532,61900,62078,63331,64623,64680,65000,65129,65389\"/>\n" +
                "<verbose level=\"0\"/>\n" +
                "<debugging level=\"0\"/>\n" +
                "<hosthint><status state=\"up\" reason=\"arp-response\" reason_ttl=\"0\"/>\n" +
                "<address addr=\"172.16.36.184\" addrtype=\"ipv4\"/>\n" +
                "<address addr=\"08:00:27:F8:85:ED\" addrtype=\"mac\" vendor=\"Oracle VirtualBox virtual NIC\"/>\n" +
                "<hostnames>\n" +
                "</hostnames>\n" +
                "</hosthint>\n" +
                "<host starttime=\"1650942623\" endtime=\"1650942782\"><status state=\"up\" reason=\"arp-response\" reason_ttl=\"0\"/>\n" +
                "<address addr=\"172.16.36.184\" addrtype=\"ipv4\"/>\n" +
                "<address addr=\"08:00:27:F8:85:ED\" addrtype=\"mac\" vendor=\"Oracle VirtualBox virtual NIC\"/>\n" +
                "<hostnames>\n" +
                "<hostname name=\"uos\" type=\"PTR\"/>\n" +
                "</hostnames>\n" +
                "<ports><extraports state=\"closed\" count=\"996\">\n" +
                "<extrareasons reason=\"reset\" count=\"996\" proto=\"tcp\" ports=\"1,3-4,6-7,9,13,17,19-21,23-26,30,32-33,37,42-43,49,53,70,79-85,88-90,99-100,106,109-111,113,119,125,135,143-144,146,161,163,179,199,211-212,222,254-256,259,264,280,301,306,311,340,366,389,406-407,416-417,425,427,443-444,458,464-465,481,497,500,512-515,524,541,543-545,548,554-555,563,587,593,616-617,625,631,636,646,648,666-668,683,687,691,700,705,711,714,720,722,726,749,765,777,783,787,800-801,808,843,873,880,888,898,900-903,911-912,981,987,990,992-993,995,999-1002,1007,1009-1011,1021-1100,1102,1104-1108,1110-1114,1117,1119,1121-1124,1126,1130-1132,1137-1138,1141,1145,1147-1149,1151-1152,1154,1163-1166,1169,1174-1175,1183,1185-1187,1192,1198-1199,1201,1213,1216-1218,1233-1234,1236,1244,1247-1248,1259,1271-1272,1277,1287,1296,1300-1301,1309-1311,1322,1328,1334,1352,1417,1433-1434,1443,1455,1461,1494,1500-1501,1503,1521,1524,1533,1556,1580,1583,1594,1600,1641,1658,1666,1687-1688,1700,1717-1721,1723,1755,1761,1782-1783,1801,1805,1812,1839-1840,1862-1864,1875,1900,1914,1935,1947,1971-1972,1974,1984,1998-2010,2013,2020-2022,2030,2033-2035,2038,2040-2043,2045-2049,2065,2068,2099-2100,2103,2105-2107,2111,2119,2121,2126,2135,2144,2160-2161,2170,2179,2190-2191,2196,2200,2222,2251,2260,2288,2301,2323,2366,2381-2383,2393-2394,2399,2401,2492,2500,2522,2525,2557,2601-2602,2604-2605,2607-2608,2638,2701-2702,2710,2717-2718,2725,2800,2809,2811,2869,2875,2909-2910,2920,2967-2968,2998,3000-3001,3003,3005-3007,3011,3013,3017,3030-3031,3052,3071,3077,3128,3168,3211,3221,3260-3261,3268-3269,3283,3300-3301,3306,3322-3325,3333,3351,3367,3369-3372,3389-3390,3404,3476,3493,3517,3527,3546,3551,3580,3659,3689-3690,3703,3737,3766,3784,3800-3801,3809,3814,3826-3828,3851,3869,3871,3878,3880,3889,3905,3914,3918,3920,3945,3971,3986,3995,3998,4000-4006,4045,4111,4125-4126,4129,4224,4242,4279,4321,4343,4443-4446,4449,4550,4567,4662,4848,4899-4900,4998,5000-5004,5009,5030,5033,5050-5051,5054,5060-5061,5080,5087,5100-5102,5120,5190,5200,5214,5221-5222,5225-5226,5269,5280,5298,5357,5405,5414,5431-5432,5440,5500,5510,5544,5550,5555,5560,5566,5631,5633,5666,5678-5679,5718,5730,5800-5802,5810-5811,5815,5822,5825,5850,5859,5862,5877,5900-5904,5906-5907,5910-5911,5915,5922,5925,5950,5952,5959-5963,5987-5989,5998-6007,6009,6025,6059,6100-6101,6106,6112,6123,6129,6156,6346,6389,6502,6510,6543,6547,6565-6567,6580,6646,6666-6669,6689,6692,6699,6779,6788-6789,6792,6839,6881,6901,6969,7000-7002,7004,7007,7019,7025,7070,7100,7103,7106,7200-7201,7402,7435,7443,7496,7512,7625,7627,7676,7741,7777-7778,7800,7911,7920-7921,7937-7938,7999-8002,8007-8011,8021-8022,8031,8042,8045,8080-8090,8093,8099-8100,8180-8181,8192-8194,8200,8222,8254,8290-8292,8300,8333,8383,8400,8402,8443,8500,8600,8649,8651-8652,8654,8701,8800,8873,8888,8899,8994,9000-9003,9009-9011,9040,9050,9071,9080-9081,9090-9091,9099-9103,9110-9111,9200,9207,9220,9290,9415,9418,9485,9500,9502-9503,9535,9575,9593-9595,9618,9666,9876-9878,9898,9900,9917,9929,9943-9944,9968,9998-10004,10009-10010,10012,10024-10025,10082,10180,10215,10243,10566,10616-10617,10621,10626,10628-10629,10778,11110-11111,11967,12000,12174,12265,12345,13456,13722,13782-13783,14000,14238,14441-14442,15000,15002-15004,15660,15742,16000-16001,16012,16016,16018,16080,16113,16992-16993,17877,17988,18040,18101,18988,19101,19283,19315,19350,19780,19801,19842,20005,20031,20221-20222,20828,21571,22939,23502,24444,24800,25734-25735,26214,27000,27352-27353,27355-27356,27715,28201,30000,30718,30951,31038,31337,32768-32785,33354,33899,34571-34573,35500,38292,40193,40911,41511,42510,44176,44442-44443,44501,45100,48080,49152-49161,49163,49165,49167,49175-49176,49400,49999-50003,50006,50300,50389,50500,50636,50800,51103,51493,52673,52822,52848,52869,54045,54328,55055-55056,55555,55600,56737-56738,57294,57797,58080,60020,60443,61532,61900,62078,63331,64623,64680,65000,65129,65389\"/>\n" +
                "</extraports>\n" +
                "<port protocol=\"tcp\" portid=\"22\"><state state=\"open\" reason=\"syn-ack\" reason_ttl=\"64\"/><service name=\"ssh\" product=\"OpenSSH\" version=\"7.9p1 Debian 1+dde\" extrainfo=\"protocol 2.0\" ostype=\"Linux\" method=\"probed\" conf=\"10\"><cpe>cpe:/a:openbsd:openssh:7.9p1</cpe><cpe>cpe:/o:linux:linux_kernel</cpe></service></port>\n" +
                "<port protocol=\"tcp\" portid=\"139\"><state state=\"open\" reason=\"syn-ack\" reason_ttl=\"64\"/><service name=\"netbios-ssn\" product=\"Samba smbd\" version=\"3.X - 4.X\" extrainfo=\"workgroup: WORKGROUP\" hostname=\"UOS\" method=\"probed\" conf=\"10\"><cpe>cpe:/a:samba:samba</cpe></service></port>\n" +
                "<port protocol=\"tcp\" portid=\"445\"><state state=\"open\" reason=\"syn-ack\" reason_ttl=\"64\"/><service name=\"netbios-ssn\" product=\"Samba smbd\" version=\"3.X - 4.X\" extrainfo=\"workgroup: WORKGROUP\" hostname=\"UOS\" method=\"probed\" conf=\"10\"><cpe>cpe:/a:samba:samba</cpe></service></port>\n" +
                "<port protocol=\"tcp\" portid=\"20000\"><state state=\"open\" reason=\"syn-ack\" reason_ttl=\"64\"/><service name=\"dnp\" method=\"table\" conf=\"3\"/></port>\n" +
                "</ports>\n" +
                "<os><portused state=\"open\" proto=\"tcp\" portid=\"22\"/>\n" +
                "<portused state=\"closed\" proto=\"tcp\" portid=\"1\"/>\n" +
                "<portused state=\"closed\" proto=\"udp\" portid=\"39043\"/>\n" +
                "<osmatch name=\"Linux 4.15 - 5.6\" accuracy=\"100\" line=\"67241\">\n" +
                "<osclass type=\"general purpose\" vendor=\"Linux\" osfamily=\"Linux\" osgen=\"4.X\" accuracy=\"100\"><cpe>cpe:/o:linux:linux_kernel:4</cpe></osclass>\n" +
                "<osclass type=\"general purpose\" vendor=\"Linux\" osfamily=\"Linux\" osgen=\"5.X\" accuracy=\"100\"><cpe>cpe:/o:linux:linux_kernel:5</cpe></osclass>\n" +
                "</osmatch>\n" +
                "</os>\n" +
                "<uptime seconds=\"2398252\" lastboot=\"Tue Mar 29 17:02:10 2022\"/>\n" +
                "<distance value=\"1\"/>\n" +
                "<tcpsequence index=\"263\" difficulty=\"Good luck!\" values=\"EEF72696,401D8857,434EA544,CA589B9A,42ECC4E,F78CB44D\"/>\n" +
                "<ipidsequence class=\"All zeros\" values=\"0,0,0,0,0,0\"/>\n" +
                "<tcptssequence class=\"1000HZ\" values=\"8EF263FB,8EF2645F,8EF264C3,8EF26527,8EF2658B,8EF265EF\"/>\n" +
                "<times srtt=\"499\" rttvar=\"823\" to=\"100000\"/>\n" +
                "</host>\n" +
                "<runstats><finished time=\"1650942782\" timestr=\"Tue Apr 26 11:13:02 2022\" summary=\"Nmap done at Tue Apr 26 11:13:02 2022; 1 IP address (1 host up) scanned in 159.40 seconds\" elapsed=\"159.40\" exit=\"success\"/><hosts up=\"1\" down=\"0\" total=\"1\"/>\n" +
                "</runstats>\n" +
                "</nmaprun>";
        OnePassParser passParser = new OnePassParser();
        NMapRun nMapRun = passParser.parse(testResult, OnePassParser.STRING_INPUT);
        if (nMapRun == null) {
            log.error("nMapRun is null");
        } else {
            for (Host h : nMapRun.getHosts()) {
                log.info("host: {}", h);
                for (Port port : h.getPorts().getPorts()) {
                    log.info("port: {}", port);
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(3.0 / 4);
    }
}
