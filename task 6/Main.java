public class Main {
    public static void main(String[] args) {
        Company company = new Company(5);
        Founder f = new Founder(company);
        f.start();
    }
}