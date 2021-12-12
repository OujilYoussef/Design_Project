public class DoubleSidedPrinting implements IPrintingMethod{
    @Override
    public int print(int p) {
       return p/2+p%2;
    }
}
