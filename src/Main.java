public class Main {
    public static void main(String[] args) throws Exception {
        CorrecteurOrthograph correcteurOrthograph = new CorrecteurOrthograph("C:\\LICENCE 3\\SS5\\Algorithmique 2\\OrthographCorrecteur-final\\src\\dico.txt");
        long begin = System.nanoTime();
        correcteurOrthograph.correctAll("C:\\LICENCE 3\\SS5\\Algorithmique 2\\OrthographCorrecteur-final\\src\\fautes.txt");
        long end = System.nanoTime();
        System.out.println("temps total à corrigé: " + (end - begin) + " nanoseconds = " + (end - begin)/1000000000.0 + " second");
    }
}