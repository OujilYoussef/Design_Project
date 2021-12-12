public class ReadyState implements IPrinterState {
    @Override
    public int print(Printer p,int n) {
        PrintingState current=new PrintingState(p,n);
        p.setState(current);
        return 0;

    }

    @Override
    public boolean cancel(Printer p) {
        System.out.println("Nothing to cancel I'am ready");
        return false;
    }

    @Override
    public boolean refill(Printer p) {
        System.out.println("Getting my full fill");
        p.Papers=100;
        p.InkFill=100;
        return true;
    }
}
