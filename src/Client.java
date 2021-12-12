public class Client implements Observer {

    @Override
    public void update(int val) {

    }

    public static void main(String[] args) {

        Printer p=new Printer();
        p.context.setMethod(new OneSidedPrinting());
        p.print( 11);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

            e.printStackTrace();
        }
        p.print(2);
        p.cancel();

        p.context.setMethod(new DoubleSidedPrinting());
        p.print(3);

    }
}
