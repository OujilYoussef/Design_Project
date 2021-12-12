public class Client implements Observer {

    @Override
    public void update(int val) {

    }

    public static void main(String[] args) {

        Printer p=new Printer();
        p.context.setMethod(new OneSidedPrinting());
        p.print( 11);
        p.print(3);
        p.refill();
        p.context.setMethod(new DoubleSidedPrinting());
        p.print(2);

    }
}
