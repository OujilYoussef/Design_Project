public class Client implements Observer {



    @Override
    public void update(IPrinterState state) {
        System.out.println(state.getClass().getName());
    }

    public static void main(String[] args) {
       Printer p=Printer.getPrinter();
        p.print(2);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

            e.printStackTrace();
        }

        p.cancel();

        p.context.setMethod(new DoubleSidedPrinting());
        p.print(3);

    }
}
