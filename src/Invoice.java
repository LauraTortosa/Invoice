import java.util.Date;
import java.util.SplittableRandom;

public class Invoice {
    private static final double GST = 0.21;
    public static final int MAX_INVOICE_LINES = 10;
    private static final String LINES =
            "-------------------------------------------------------------------";
    private int invoiceNum;
    private Date date;
    private String to;
    private String shipTo;
    private String person;
    private String method;
    private String terms;
    private Date delivery;
    private String paymentTerms;
    private Date dueDate;
    private float totalDiscount;
    private InvoiceLine[] invoiceLines;

    public Invoice (int invoiceNum, Date date, String to, String shipTo, String person,
                    String method, String terms, Date delivery, String paymentTerms,
                    Date dueDate, float totalDiscount) {
        this.invoiceNum = invoiceNum;
        this.date = date;
        this.to = to;
        this.shipTo = shipTo;
        this.person = person;
        this.method = method;
        this.terms = terms;
        this.delivery = delivery;
        this.paymentTerms = paymentTerms;
        this.dueDate = dueDate;
        this.totalDiscount = totalDiscount;
        invoiceLines = new InvoiceLine[MAX_INVOICE_LINES];
        for (int i = 0; i < MAX_INVOICE_LINES; i++) {
            invoiceLines[i] = null;
        }
    }

    public void addInvoiceLine(InvoiceLine line) {
        int i = 0;
        while (i < invoiceLines.length && invoiceLines[i] != null) {
            i++;
        }
        if (i < invoiceLines.length) {
            invoiceLines[i] = line;
        }
    }
    public void print() {
        System.out.println(" Invoice number: " + invoiceNum);
        System.out.format("%50s %tb %td %ty %n", "DATE: ", date, date, date);
        System.out.println(" TO: " + to);
        System.out.println(" SHIP TO: " + shipTo);
        System.out.format("");
        System.out.println(LINES);
        System.out.format("%20s %20s %20s %10s %20s %10s %n",
                "SALES PERSON", "SHIPPING METHOD",
                "SHIPPING TERMS", "DELIVERY DATE",
                "PAYMENT TERMS", "DUE DATE");
        System.out.println(LINES);
        System.out.format("| %20s | %20s | %20s | %15tD | %20s | %10tD |%n",
                person, method, terms, delivery,
                paymentTerms, dueDate);
        System.out.println(LINES);
        System.out.println();
        int i = 0;
        System.out.println(InvoiceLine.HEADER);
        while (i < invoiceLines.length && invoiceLines[i] != null) {
            System.out.println(invoiceLines[i]);
            i++;
        }
        System.out.println(InvoiceLine.LINES);
        printFooter();
    }

    private void printFooter() {
        double totalLines = 0;
        int i = 0;
        while ((i<invoiceLines.length) && (invoiceLines[i]!=null)) {
            totalLines = totalLines + invoiceLines[i].getTotalLine();
            i++;
        }
        double totalDiscount = 0;
        System.out.format("%73s | %8s | %13s |%n",
                "TOTAL DISCOUNT",
                InvoiceLine.customFormat("##.00%", totalDiscount) ,
                InvoiceLine.customFormat("$#,###,###.00",
                        totalLines * totalDiscount));
        System.out.format("%102s%n", "----------------------------");
        System.out.format("%84s | %13s |%n",
                "SUBTOTAL",
                InvoiceLine.customFormat("$#,###,###.00",
                        totalLines * (1 - totalDiscount)));
        System.out.format("%102s%n", "-----------------");
        System.out.format("%84s | %13s |%n",
                "GST",
                InvoiceLine.customFormat("$#,###,###.00",
                        totalLines * (1 - totalDiscount) * GST));
        System.out.format("%102s%n", "-----------------");
        System.out.format("%84s | %13s |%n",
                "TOTAL",
                InvoiceLine.customFormat("$#,###,###.00",
                        totalLines * (1 - totalDiscount) +
                                totalLines * (1 - totalDiscount) * GST));
        System.out.format("%102s%n", "-----------------");
    }
}
