import java.text.DecimalFormat;

public class InvoiceLine {
    public static final String LINES =
            "---------------------------------------------------------------------------";
    private static final String FORMAT_STRING = "| %9s | %10s | %30s | %13s | %8s | %13s |";
    public static final String HEADER = LINES + "\n" + String.format(FORMAT_STRING, "QTY", "Item #", "DESCRIPTION",
            "UNIT PRICE", "DISCOUNT", "LINE TOTAL") + "\n" + LINES;
    private int qty;
    private String item;
    private String description;
    private float unitPrice;
    private float discount;

    private String line = String.format("%5s %5s %25s %10s %5s %10s",
            "QTY", "Item #", "DESCRIPTION", "UNIT PRICE", "DISCOUNT", "LINE TOTAL");

    public InvoiceLine(int qty, String item, String description, float unitPrice, float discount) {
        this.qty = qty;
        this.item = item;
        this.description = description;
        this.unitPrice = unitPrice;
        this.discount = discount/100;
    }

    public String toString() {
        String s = String.format("| %20s | %10s | %30s | %13s | %8s | %13s |",
                customFormat("###,###,###", qty),
                item,
                description,
                customFormat("$#,###,###.00", unitPrice),
                customFormat("##.00%", discount),
                customFormat("$#,###,###.00", getTotalLine()));
        return s;
    }

    public float getTotalLine() {
        return qty * unitPrice * (1 - discount);    }

    public static String customFormat(String pattern, double value ){
        DecimalFormat myFormatter = new DecimalFormat(pattern);
        String output = myFormatter.format(value);
        return output;
    }



    


}
