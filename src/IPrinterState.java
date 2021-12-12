public interface IPrinterState {
     int print(Printer p,int i);
     boolean cancel(Printer p);
     boolean refill(Printer p);

}
