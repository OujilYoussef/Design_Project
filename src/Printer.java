public class Printer extends Observable{
    
	public int InkFill;
    public int Papers;
    private IPrinterState state;
    public PrinterContext context;
    private static Printer printer;

    public synchronized static Printer getPrinter()
    {
        if(printer==null)
        {
            printer=new Printer();
        }
            return printer;
    }
    public void setState(IPrinterState state) {
        notifyObservers(state);
        this.state = state;
    }

    public IPrinterState getState() {
        return state;
    }

    private Printer() {
        state=new ReadyState();
        context=new PrinterContext();
        InkFill=100;
        Papers=100;
    }
    public int print(int n)
    {

      return this.state.print(this,n);
    }
    public boolean cancel()

    {
        return this.state.cancel(this);
    }
    public boolean refill()

    {

        return this.state.refill(this);
    }
}
