package net.finmath.xva.beans;

import com.google.gson.annotations.SerializedName;
import net.finmath.xva.initialmargin.SIMMParameter;
import net.finmath.xva.tradespecifications.SIMMSensitivityKey;

public class CRIFSensititivityBean {
    public static class CRIFSensiBean{

        @SerializedName("Counterparty")
        String Counterparty;
        @SerializedName("TradeID")
        String TradeID;

        @SerializedName("ProductClass")
        String ProductClass;

        @SerializedName("RiskType")
        String RiskType;

        @SerializedName("Qualifier")
        String Qualifier;

        @SerializedName("Bucket")
        String Bucket;

        @SerializedName("Label1")
        String Label1;

        @SerializedName("Label2")
        String Label2;

        @SerializedName("Amount")
        Double Amount;

        @SerializedName("AmountCCY")
        String AmountCCY;

        @SerializedName("AmountUSD")
        Double AmountUSD;

        public String getCounterparty() {
            return Counterparty;
        }

        public SIMMSensitivityKey getSensitivityKey(){
            SIMMSensitivityKey key = null;
            if (RiskType.contains("IR") && !RiskType.contains("Vol"))
                key = new SIMMSensitivityKey(Label1, Label2, Qualifier, "InterestRate", SIMMParameter.RiskType.Delta.name(), ProductClass);
            else if (RiskType.contains("IR") && RiskType.contains("Vol"))
                key = new SIMMSensitivityKey(Label1, Label2, Qualifier, "InterestRate", SIMMParameter.RiskType.Vega.name(), ProductClass);
            else if (RiskType.contains("Equity") && !RiskType.contains("Vol"))
                key = new SIMMSensitivityKey("None", Qualifier, Bucket, "Equity", SIMMParameter.RiskType.Delta.name(), ProductClass);
            else if (RiskType.contains("Equity") && RiskType.contains("Vol"))
                key = new SIMMSensitivityKey(Label1, Qualifier, Bucket, "Equity", SIMMParameter.RiskType.Vega.name(), ProductClass);
            else if (RiskType.contains("Commodity") && !RiskType.contains("Vol"))
                key = new SIMMSensitivityKey("None", Qualifier, Bucket, "Commodity", SIMMParameter.RiskType.Delta.name(), ProductClass);
            else if (RiskType.contains("Commodity") && RiskType.contains("Vol"))
                key = new SIMMSensitivityKey(Label1, Qualifier, Bucket, "Commodity", SIMMParameter.RiskType.Vega.name(), ProductClass);
            else if (RiskType.contains("FX") && !RiskType.contains("Vol"))
                key = new SIMMSensitivityKey("None", Qualifier, "0", "FX", SIMMParameter.RiskType.Delta.name(), ProductClass);
            else if (RiskType.contains("FX") && RiskType.contains("Vol")) {
                if (Qualifier.length() == 6) {
                    String ccy1 = Qualifier.substring(0, 3);
                    String ccy2 = Qualifier.substring(3, 6);
                    if (ccy2.compareTo(ccy1) > 0) {
                        Qualifier = ccy2 + ccy1;
                    }
                }
                key = new SIMMSensitivityKey(Label1, Qualifier, "0", "FX", SIMMParameter.RiskType.Vega.name(), ProductClass);
            } else if (RiskType.contains("CreditQ"))
                key = new SIMMSensitivityKey(Label1, Qualifier, Bucket, "CreditQ", SIMMParameter.RiskType.Delta.name(), ProductClass);
            else if (RiskType.contains("CreditNonQ"))
                key = new SIMMSensitivityKey(Label1, Qualifier, Bucket, "CreditNonQ", SIMMParameter.RiskType.Delta.name(), ProductClass);
            else if (RiskType.contains("Credit") && RiskType.contains("Vol"))
                key = new SIMMSensitivityKey(Label1, Qualifier, Bucket, "CreditQ", SIMMParameter.RiskType.Vega.name(), ProductClass);
            else if (RiskType.contains("Inflation") && !RiskType.contains("Vol"))
                key = new SIMMSensitivityKey("None", "inflation", Qualifier, "InterestRate", SIMMParameter.RiskType.Delta.name(), ProductClass);
            else if (RiskType.contains("Inflation") && RiskType.contains("Vol"))
                key = new SIMMSensitivityKey("None", "inflation", Qualifier, "InterestRate", SIMMParameter.RiskType.Vega.name(), ProductClass);
            else if (RiskType.contains("XCcy"))
                key = new SIMMSensitivityKey("None", "ccybasis", Qualifier, "InterestRate", SIMMParameter.RiskType.Delta.name(), ProductClass);return key;

        }

        public CRIFSensiBean(String counterparty, String tradeID, String productClass, String riskType, String qualifier, String bucket, String label1, String label2, Double amount, String amountCCY, Double amountUSD) {
            Counterparty = counterparty;
            TradeID = tradeID;
            ProductClass = productClass;
            RiskType = riskType;
            Qualifier = qualifier;
            Bucket = bucket;
            Label1 = label1;
            Label2 = label2;
            Amount = amount;
            AmountCCY = amountCCY;
            AmountUSD = amountUSD;
        }

        public String getTradeID() {
            return TradeID;
        }

        public void setTradeID(String tradeID) {
            TradeID = tradeID;
        }

        public String getProductClass() {
            return ProductClass;
        }

        public void setProductClass(String productClass) {
            ProductClass = productClass;
        }

        public String getRiskType() {
            return RiskType;
        }

        public void setRiskType(String riskType) {
            RiskType = riskType;
        }

        public String getQualifier() {
            return Qualifier;
        }

        public void setQualifier(String qualifier) {
            Qualifier = qualifier;
        }

        public String getBucket() {
            return Bucket;
        }

        public void setBucket(String bucket) {
            Bucket = bucket;
        }

        public String getLabel1() {
            return Label1;
        }

        public void setLabel1(String label1) {
            Label1 = label1;
        }

        public String getLabel2() {
            return Label2;
        }

        public void setLabel2(String label2) {
            Label2 = label2;
        }

        public Double getAmount() {
            return Amount;
        }

        public void setAmount(Double amount) {
            Amount = amount;
        }

        public String getAmountCCY() {
            return AmountCCY;
        }

        public void setAmountCCY(String amountCCY) {
            AmountCCY = amountCCY;
        }

        public Double getAmountUSD() {
            return AmountUSD;
        }

        public void setAmountUSD(Double amountUSD) {
            AmountUSD = amountUSD;
        }
    }
}
