public class Printer {
    public int InkFill;
    public int Papers;
    private IPrinterState state;
    public PrinterContext context;

    public void setState(IPrinterState state) {
        this.state = state;
    }

    public IPrinterState getState() {
        return state;
    }

    public Printer() {
        state=new ReadyState();
        context=new PrinterContext();
        InkFill=100;
        Papers=100;
    }
    public int print(int n)
    {
      return this.state.print(this,n);
    }
    public void cancel()
    {
        this.state.cancel(this);
    }
    public void refill()
    {
        this.state.refill(this);
    }
}
