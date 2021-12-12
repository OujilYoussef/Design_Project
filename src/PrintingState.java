public class PrintingState implements IPrinterState{
    Thread thread;
    public  PrintingState(Printer p, int i) {
        Runnable runnable =
          () -> {

                int counter;
                int j=i;
                if (p.context.method.getClass() == DoubleSidedPrinting.class) {
                    counter = 2;

                } else {
                    counter = 1;
                }
                while (j > 0) {

                    try {
                        if (p.InkFill < 10 || p.Papers < 1) {
                            System.out.println("Printer is empty please Refill");
                            break;

                        }
                        System.out.println(j + " pages left");
                        Thread.sleep(500);

                            j -= counter;



                        p.InkFill -= 10;
                        p.Papers -= 1;

                    } catch (InterruptedException e) {
                        break;
                    }


                }
                if (p.InkFill >= 10 && p.Papers >= 1) {
                    p.setState(new ReadyState());

                } else {
                    p.setState(new EmptyState());
                }
                if(j==0)
                {
                    System.out.println("Done");
                }

            };

        thread=new Thread(runnable);
        thread.start();
    }

    @Override
    public int print(Printer p,int i) {
        System.out.println("already printing");
        return 0;
    }

    @Override
    public boolean cancel(Printer p) {
        System.out.println("Canceling...");
        thread.interrupt();
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
