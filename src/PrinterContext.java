public class PrinterContext {
    IPrintingMethod method;

    public int print(int i)
    {
        return  method.print(i);
    }
    public PrinterContext() {
        this.method=new OneSidedPrinting();
    }

    public void setMethod(IPrintingMethod method) {
        this.method = method;
    }
}
