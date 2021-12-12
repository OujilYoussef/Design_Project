public class EmptyState implements IPrinterState{

    @Override
    public int print(Printer p,int i) {
        System.out.println("Printer is Empty");
        return 0;
    }

    @Override
    public boolean cancel(Printer p) {
        System.out.println("Nothing to cancel i am empty");
        return false;
    }

    @Override
    public boolean refill(Printer p) {

        p.Papers=100;
        p.InkFill=100;
        System.out.println("Filled and ready");
        p.setState(new ReadyState()) ;
        return true;
    }


}
