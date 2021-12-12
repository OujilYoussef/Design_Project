public class PrintingState implements IPrinterState{
    public void DoPrint(Printer p, int i) {
        int counter;
        if( p.context.method.getClass()==DoubleSidedPrinting.class)
        {
            counter=2;

        }
        else
        {
            counter=1;
        }
        while(i>0)
        {

            try {
                if(p.InkFill <10 || p.Papers<1)
                {
                    System.out.println("Printer is empty please Refill");
                    p.setState(new EmptyState());
                    break;

                }
                System.out.println(i+ " pages left");
                Thread.sleep(500);
                i-=counter;
                p.InkFill-=10;
                p.Papers-=1;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
        if(p.InkFill>=10 && p.Papers>=1)
        {
            p.setState(new ReadyState());
        }
        else
        {
            p.setState(new EmptyState());
        }

    }

    @Override
    public int print(Printer p,int i) {
        System.out.println("already printing");
        return 0;
    }

    @Override
    public boolean cancel(Printer p) {
        System.out.println("Canceling...");
        if(p.InkFill>=10 && p.Papers>0)
        {
            p.setState(new ReadyState());
        }
        else
        {
            p.setState(new EmptyState());
        }

        return true;
    }

    @Override
    public boolean refill(Printer p) {
        System.out.println("can't refill while printing");
        return false;
    }
}
