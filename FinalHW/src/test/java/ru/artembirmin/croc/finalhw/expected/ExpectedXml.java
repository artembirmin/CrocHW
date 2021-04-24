package ru.artembirmin.croc.finalhw.expected;

public class ExpectedXml {

    private final String expectedXmlBeforeActions = "<flights>\n" +
            "  <flight>\n" +
            "    <flightNumber>SC 123</flightNumber>\n" +
            "    <departureCity name=\"Krasnodar\"/>\n" +
            "    <arrivalCity name=\"Moscow\"/>\n" +
            "    <departureDate>2021-04-22</departureDate>\n" +
            "    <departureTime>12:00:00</departureTime>\n" +
            "    <arrivalDate>2021-04-22</arrivalDate>\n" +
            "    <arrivalTime>15:00:00</arrivalTime>\n" +
            "    <remark>Canceled</remark>\n" +
            "  </flight>\n" +
            "  <flight>\n" +
            "    <flightNumber>AX 228</flightNumber>\n" +
            "    <departureCity name=\"Krasnodar\"/>\n" +
            "    <arrivalCity name=\"Moscow\"/>\n" +
            "    <departureDate>2021-04-22</departureDate>\n" +
            "    <departureTime>19:45:00</departureTime>\n" +
            "    <arrivalDate>2021-04-22</arrivalDate>\n" +
            "    <arrivalTime>23:40:00</arrivalTime>\n" +
            "    <remark>Departed</remark>\n" +
            "  </flight>\n" +
            "  <flight>\n" +
            "    <flightNumber>BX 137</flightNumber>\n" +
            "    <departureCity name=\"Krasnodar\"/>\n" +
            "    <arrivalCity name=\"Moscow\"/>\n" +
            "    <departureDate>2021-04-22</departureDate>\n" +
            "    <departureTime>20:10:00</departureTime>\n" +
            "    <arrivalDate>2021-04-23</arrivalDate>\n" +
            "    <arrivalTime>00:00:00</arrivalTime>\n" +
            "    <remark>No status</remark>\n" +
            "  </flight>\n" +
            "</flights>";

    //TODO
    private final String expectedXmlAfterActions = "<flights>\n" +
            "  <flight>\n" +
            "    <flightNumber>SC 123</flightNumber>\n" +
            "    <departureCity name=\"Krasnodar\"/>\n" +
            "    <arrivalCity name=\"Moscow\"/>\n" +
            "    <departureDate>2021-04-22</departureDate>\n" +
            "    <departureTime>12:00:00</departureTime>\n" +
            "    <arrivalDate>2021-04-22</arrivalDate>\n" +
            "    <arrivalTime>15:00:00</arrivalTime>\n" +
            "    <remark>Canceled</remark>\n" +
            "  </flight>\n" +
            "  <flight>\n" +
            "    <flightNumber>VF 456</flightNumber>\n" +
            "    <departureCity name=\"Krasnodar\"/>\n" +
            "    <arrivalCity name=\"Moscow\"/>\n" +
            "    <departureDate>2021-04-22</departureDate>\n" +
            "    <departureTime>12:30:00</departureTime>\n" +
            "    <arrivalDate>2021-04-22</arrivalDate>\n" +
            "    <arrivalTime>17:00:00</arrivalTime>\n" +
            "    <remark>Departed</remark>\n" +
            "  </flight>\n" +
            "  <flight>\n" +
            "    <flightNumber>AX 228</flightNumber>\n" +
            "    <departureCity name=\"Krasnodar\"/>\n" +
            "    <arrivalCity name=\"Moscow\"/>\n" +
            "    <departureDate>2021-04-22</departureDate>\n" +
            "    <departureTime>19:45:00</departureTime>\n" +
            "    <arrivalDate>2021-04-22</arrivalDate>\n" +
            "    <arrivalTime>23:40:00</arrivalTime>\n" +
            "    <remark>Canceled</remark>\n" +
            "  </flight>\n" +
            "  <flight>\n" +
            "    <flightNumber>BX 137</flightNumber>\n" +
            "    <departureCity name=\"Krasnodar\"/>\n" +
            "    <arrivalCity name=\"Moscow\"/>\n" +
            "    <departureDate>2021-04-22</departureDate>\n" +
            "    <departureTime>20:10:00</departureTime>\n" +
            "    <arrivalDate>2021-04-23</arrivalDate>\n" +
            "    <arrivalTime>00:00:00</arrivalTime>\n" +
            "    <remark>No status</remark>\n" +
            "  </flight>\n" +
            "  <flight>\n" +
            "    <flightNumber>EBX 1337</flightNumber>\n" +
            "    <departureCity name=\"Krasnodar\"/>\n" +
            "    <arrivalCity name=\"Moscow\"/>\n" +
            "    <departureDate>2021-04-22</departureDate>\n" +
            "    <departureTime>19:45:00</departureTime>\n" +
            "    <arrivalDate>2021-04-22</arrivalDate>\n" +
            "    <arrivalTime>23:40:00</arrivalTime>\n" +
            "    <remark>Departed</remark>\n" +
            "  </flight>\n" +
            "</flights>";

    public String getExpectedXmlBeforeActions() {
        return expectedXmlBeforeActions;
    }

    public String getExpectedXmlAfterActions() {
        return expectedXmlAfterActions;
    }
}