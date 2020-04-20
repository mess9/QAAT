package basePack.ru.sberbankast;

public class PurchasesItem {
    public String purchaseNumber;
    public String purchaseLink;
    public Double purchaseSum;

    PurchasesItem(String purchaseNumber, String purchaseLink, String purchaseSum){
        this.purchaseNumber = purchaseNumber;
        this.purchaseLink = purchaseLink;
        this.purchaseSum = Double.parseDouble(purchaseSum.replaceAll("\\s+",""));
    }

    @Override
    public String toString(){
        return "№: "+ purchaseNumber + " Link: " + purchaseLink + " Sum " + purchaseSum + ";";
    }
}
