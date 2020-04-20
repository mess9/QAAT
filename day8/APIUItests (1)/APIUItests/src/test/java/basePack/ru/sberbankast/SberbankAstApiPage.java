package basePack.ru.sberbankast;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.restassured.RestAssured.given;

public class SberbankAstApiPage {

    public List<PurchasesItem> getPurchasesListFromAPI(String searchString, int itemsAmount, int itemsPerRequest){
        //itemsPerRequest 20, 50, 100
        List<PurchasesItem> purchases = new ArrayList<>();

        Pattern sum = Pattern.compile("(?<=purchAmountRUB\\\\{3}\":)\\d+\\.\\d*(?=,\\\\{3})"); //сумма
        Pattern number = Pattern.compile("(?<=purchCode.{9})\\d+(?=\\\\{3})"); //номер
        Pattern link = Pattern.compile("(?<=objectHrefTerm.{9})http:\\/\\/www\\.sberbank-ast\\.ru(\\/..)?\\/purchaseview\\.aspx\\?id=\\d+(?=\\\\{3})"); //ссылка
        int from = 0; //результат начиная с
        while(purchases.size() < itemsAmount){

            List<String> itemsList = new ArrayList<>();
            String page = getResponse(
                    "xmlData=<elasticrequest><filters><mainSearchBar><value>"+ searchString +"</value><type>best_fields</type><minimum_should_match>100%</minimum_should_match></mainSearchBar><purchAmount><minvalue></minvalue><maxvalue></maxvalue>" +
                            "</purchAmount><PublicDate><minvalue></minvalue><maxvalue></maxvalue></PublicDate><PurchaseStageTerm><value></value><visiblepart></visiblepart></PurchaseStageTerm><SourceTerm><value></value><visiblepart></visiblepart>" +
                            "</SourceTerm><RegionNameTerm><value></value><visiblepart></visiblepart></RegionNameTerm><RequestStartDate><minvalue></minvalue><maxvalue></maxvalue></RequestStartDate><RequestDate><minvalue></minvalue><maxvalue></maxvalue>" +
                            "</RequestDate><AuctionBeginDate><minvalue></minvalue><maxvalue></maxvalue></AuctionBeginDate><okdp2MultiMatch><value></value></okdp2MultiMatch><okdp2tree><value></value><productField></productField><branchField></branchField></okdp2tree>" +
                            "<classifier><visiblepart></visiblepart></classifier><orgCondition><value></value></orgCondition><orgDictionary><value></value></orgDictionary><organizator><visiblepart></visiblepart></organizator><CustomerCondition><value></value></CustomerCondition>" +
                            "<CustomerDictionary><value></value></CustomerDictionary><customer><visiblepart></visiblepart></customer><PurchaseTypeNameTerm><value></value><visiblepart></visiblepart></PurchaseTypeNameTerm><BranchNameTerm><value></value><visiblepart></visiblepart>" +
                            "</BranchNameTerm><IsSMPTerm><value></value><visiblepart></visiblepart></IsSMPTerm><statistic><totalProc>0</totalProc><TotalSum>0</TotalSum><DistinctOrgs>4+064</DistinctOrgs></statistic></filters><_source><field>TradeSectionId</field><field>purchAmount</field>" +
                            "<field>purchCurrency</field><field>purchCodeTerm</field><field>PurchaseTypeName</field><field>purchStateName</field><field>OrgName</field><field>SourceTerm</field><field>PublicDate</field><field>RequestDate</field><field>RequestStartDate</field><field>RequestAcceptDate</field>" +
                            "<field>EndDate</field><field>AuctionBeginDate</field><field>CreateRequestHrefTerm</field><field>CreateRequestAlowed</field><field>purchName</field><field>BidName</field><field>SourceHrefTerm</field><field>objectHrefTerm</field><field>needPayment</field><field>IsSMP</field>" +
                            "<field>isIncrease</field><field>purchType</field></_source><sort><value>default</value><direction></direction></sort><aggregations><empty><filterType>filter_aggregation</filterType><field></field></empty></aggregations>" +
                            "<size>"+ itemsPerRequest +"</size><from>" + from + "</from></elasticrequest>&orgId=0&targetPageCode=ESPurchaseList"
            );
            from += itemsPerRequest;
            page = page.split("pagerTotal")[1]; //отделяем ненужную часть
            if(from > itemsAmount)
                itemsList = Arrays.asList(page.split("\\},\\{",itemsAmount % itemsPerRequest)); //оставшаяся часть
            else
                itemsList = Arrays.asList(page.split("\\},\\{",itemsPerRequest)); //делим на блоки

            itemsList.forEach(                                               //парсим
                    x->purchases.add(new PurchasesItem(
                            getMatch(x, number),
                            getMatch(x, link),
                            getMatch(x, sum)
                    ))
            );
        }

        return purchases;
    }

    String getMatch(String string, Pattern pattern){
        Matcher matcher;
        matcher = pattern.matcher(string);
        matcher.find();
        return matcher.group(0);
    }

    String getResponse(String body){
        Response response = given()
                .header("Host", "www.sberbank-ast.ru")
                .header("Connection", "keep-alive")
                .header("Accept", "*/*")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.48 Safari/537.36")
                .header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .header("Accept-Encoding", "gzip, deflate, br")
                .body(body)
                .when()
                .post("https://www.sberbank-ast.ru/SearchQuery.aspx?name=Main")
                .then()
                //.log().all()
                .statusCode(200)
                .extract()
                .response();
        return response.getBody().asString();
    }

}
